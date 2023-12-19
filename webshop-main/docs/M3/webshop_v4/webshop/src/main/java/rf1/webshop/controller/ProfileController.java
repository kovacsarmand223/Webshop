package rf1.webshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rf1.webshop.dao.UserDAO;
import rf1.webshop.model.ItemModel;
import rf1.webshop.model.UserModel;
import rf1.webshop.util.PasswordHash;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/profile")
    public String getPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UserModel actualUser = userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
        model.addAttribute("user", actualUser);
        //String vezeteknev = actualUser.getLastName();
        //String keresztnev = actualUser.getFirstName();
        //String telepules = actualUser.getSettlement();
        //String utca = actualUser.getStreet();
        //int hazszam = actualUser.getHouseNumber();
        if (session == null) {
            System.err.println("profile called");
            System.err.println("YOU ARE NOT LOGGED IN, REDIRECTING TO LOGIN");
            return "loginuser";
        } else if (session.getAttribute("userAuthenticated") == null || session.getAttribute("userAuthenticated").equals("false")) {
            System.err.println("profile called");
            System.err.println("YOU ARE NOT LOGGED IN, REDIRECTING TO LOGIN");
            return "loginuser";
        } else if (session.getAttribute("userAuthenticated").equals("true")) {
            return "profile";
        } else {
            return "profile";
        }
    }

    @PostMapping(value = "/profile/updateUser")
    public String updateUser(
            @RequestParam("updateFirstname") String firstname,
            @RequestParam("updateLastname") String lastname,
            @RequestParam("updateState") String state,
            @RequestParam("updateStreet") String street,
            @RequestParam("updateHouseNumber") String hnumber,
            HttpSession session) {
        try{
            if(userDAO.getUserByUserName((String)session.getAttribute("loginusername")) == null){
                return "redirect:/profile";
            }
            String actualUser = (String) session.getAttribute("loginusername");
            UserModel user = new UserModel(firstname, lastname, street, state, Integer.parseInt(hnumber));
            userDAO.updateUser(actualUser,user);
            return "redirect:/profile";
        } catch (Exception e){
            System.err.println("updateUser error: "+e);
            return "redirect:/profile";
        }
    }

    @PostMapping(value = "/profile")
    public String pwdAlter(
            @RequestParam("currentPassword") String currPwd,
            @RequestParam("newPassword1") String password1,
            @RequestParam("newPassword2") String password2,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        UserModel userModel = userDAO.getUserByUserName((String) session.getAttribute("loginusername"));
        if (userModel != null && PasswordHash.isSamePassword(currPwd, userModel.getPassword())) {
            if(!password1.isEmpty() && !password2.isEmpty()){
                if(password1.equals(password2)){
                    if(password1.length() > 7){
                        System.err.println("Sikerult a modositas!");
                        userDAO.changePwd(PasswordHash.getHashedPassword(password1), userModel);
                        redirectAttributes.addAttribute("pwdChangeSuccess", "The password was changed!");
                    }else{
                        System.err.println("Nem Sikerult a modositas!0");
                        redirectAttributes.addAttribute("pwdChangeFailed0", "The password should be at least 8 characters long!");
                        return "redirect:/profile";
                    }
                }else{
                    System.err.println("Nem Sikerult a modositas!1");
                    redirectAttributes.addAttribute("pwdChangeFailed", "The new passwords do not match!");
                    return "redirect:/profile";
                }
            }else{
                System.err.println("Nem Sikerult a modositas!2");
                redirectAttributes.addAttribute("pwdChangeFailed2", "The new password field(s) is empty!");
                return "redirect:/profile";
            }
        }else{
            System.err.println("Nem Sikerult a modositas!3");
            redirectAttributes.addAttribute("pwdChangeFailed3", "Invalid password!");
            return "redirect:/profile";
        }
        return "redirect:/profile";
    }

    @PostMapping("/profile/deleteaccount")
    public String deleteAccount(HttpSession httpSession){
        String loginUsername=(String)httpSession.getAttribute("loginusername");
        userDAO.deleteUser(loginUsername);
        return "redirect:/logout";
    }
}

