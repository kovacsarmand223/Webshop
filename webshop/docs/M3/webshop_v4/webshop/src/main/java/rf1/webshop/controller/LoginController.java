package rf1.webshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rf1.webshop.dao.UserDAO;
import rf1.webshop.model.UserModel;
import rf1.webshop.util.PasswordHash;

@Controller
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/login")
    public String getLoginForm(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.err.println("getLoginForm called");
            return "loginuser";
        } else if (session.getAttribute("userAuthenticated") == null || session.getAttribute("userAuthenticated").equals("false")) {
            System.err.println("getLoginForm called");
            return "loginuser";
        } else if (session.getAttribute("userAuthenticated").equals("true")) {
            System.err.println("YOU ARE LOGGED IN, REDIRECTING TO INDEX");
            return "redirect:/";
        } else {
            System.err.println("YOU ARE LOGGED IN, REDIRECTING TO INDEX");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/login")
    public String loginUser(@RequestParam("felhasznalo") String username,
                            @RequestParam("jelszo") String password,
                            RedirectAttributes redirectAttributes,
                            Model model,
                            HttpSession session) {

        UserModel userModel = userDAO.getUserByUserName(username);

        if (userModel != null && PasswordHash.isSamePassword(password, userModel.getPassword())) {
            try {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
                SecurityContextHolder.getContext().setAuthentication(auth);
                userModel.setAuthenticated(true);
                session.setAttribute("isAdmin", userModel.isPermission());
                session.setAttribute("userAuthenticated", userModel.isAuthenticated());
                redirectAttributes.addAttribute("isAuthenticated", session.getAttribute("userAuthenticated"));
                redirectAttributes.addAttribute("loginsuccess", "You've logged in successfully!");
                session.setAttribute("loginusername", auth.getPrincipal());
                System.out.println("Is user authenticated: " + session.getAttribute("userAuthenticated"));
                return "redirect:/";
            } catch (Exception e) {

                redirectAttributes.addAttribute("loginfailed1", e);
                return "redirect:/login";
            }
        } else {

            redirectAttributes.addAttribute("loginfailed2", "Invalid username or password");
            return "redirect:/login";
        }
    }
}
