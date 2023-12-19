package rf1.webshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rf1.webshop.dao.CartDAO;
import rf1.webshop.dao.CustomerDAO;
import rf1.webshop.dao.UserDAO;
import rf1.webshop.model.CartModel;
import rf1.webshop.model.CustomerModel;
import rf1.webshop.model.UserModel;
import rf1.webshop.util.PasswordHash;

@Controller
public class RegisterController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CartDAO cartDAO;

    @GetMapping(path = "/register")
    public String getRegisterForm(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            System.err.println("getRegisterForm called");
            return "registeruser";
        }
        else if (session.getAttribute("userAuthenticated") == null || session.getAttribute("userAuthenticated").equals("false")) {
            System.err.println("getLoginForm called");
            return "registeruser";
        }
        else if (session.getAttribute("userAuthenticated").equals("true")) {
            System.err.println("YOU ARE LOGGED IN, REDIRECTING TO INDEX");
            return "redirect:/";
        } else {
            System.err.println("YOU ARE LOGGED IN, REDIRECTING TO INDEX");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute UserModel userModel,
                               @RequestParam("vezeteknev") String lastName,
                               @RequestParam("keresztnev") String firstName,
                               @RequestParam("felhasznalonev") String username,
                               @RequestParam("jelszo") String password1,
                               @RequestParam("jelszo2") String password2,
                               RedirectAttributes redirectAttributes) {
        System.out.println("register req: " + userModel);
        if("".equals(lastName) || "".equals(firstName) || "".equals(username) || "".equals(password1) || "".equals(password2)){
            System.out.println("A");
            redirectAttributes.addAttribute("err" , "Some fields are missing");
        }
        if(userDAO.getUserByUserName(username)!=null){
            System.out.println("B");
            redirectAttributes.addAttribute("err1", "This username is used");
            return "redirect:/register";
        }
        if(password1!=null && password1.equals(password2) && password1.length()>=8){
            UserModel user = new UserModel(firstName,lastName,username, PasswordHash.getHashedPassword(password1));
            userDAO.createUser(user);
            int cartId=0;
            do{
                cartId++;
            }while(cartDAO.getCartByCartId(cartId)!=null);
            CartModel cart=new CartModel(cartId);
            cartDAO.createNewCart(cart);
            customerDAO.createCustomer(new CustomerModel(user.getUsername(),cart.getCartId()));
            System.out.println("C");
            redirectAttributes.addAttribute("success", "The registracion was successful now you can login!");
            return "redirect:/login";
        }
        if(!password1.equals(password2)){
            redirectAttributes.addAttribute("err2", "Passwords do not match");
            return "redirect:/register";
        }

        return "redirect:/register";
    }
}