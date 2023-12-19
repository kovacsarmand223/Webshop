package rf1.webshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rf1.webshop.dao.ItemInCartDAO;
import rf1.webshop.model.CustomerModel;
import rf1.webshop.model.ItemModel;
import rf1.webshop.model.UserModel;

import java.util.List;

@Controller
public class ItemInCartController {

    @Autowired
    private ItemInCartDAO itemInCartDAO;

    @GetMapping(value = "/cart")
    public String listItemsInCart(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("loginusername");
        int cartId = itemInCartDAO.getLoggedUser(username).getCartId();
        System.out.println(username);
        System.out.println(cartId);
        System.out.println("listItemsInCart called");  // Add this line for debugging
        List<ItemModel> items = itemInCartDAO.getCartItems(cartId);
        CustomerModel user = itemInCartDAO.getLoggedUser(username);
        model.addAttribute("cartitem", items);
        model.addAttribute("user", user);

        return "cart";
    }

    @PostMapping(value = "/deleteItem/{id}")
    public String deleteItemFromCart(HttpServletRequest request, @PathVariable("id") int id) {
        itemInCartDAO.deleteItemByItemId(id);
        return "redirect:/cart";
    }

    @PostMapping(value = "/addcart/{id}")
    public String addItemToCart(HttpServletRequest request, @PathVariable("id") int id) {
        itemInCartDAO.addItemToCart(request, id);

        return "redirect:/";
    }

    @PostMapping(value = "/order")
    public String orderItems(HttpServletRequest request,
                             @RequestParam(name = "telepules", required = false) String state,
                             @RequestParam(name = "utca", required = false) String street,
                             @RequestParam(name = "hazszam", required = false) String houseNum,
                             RedirectAttributes redirectAttributes) {
        System.out.println("state: " + state);
        System.out.println("street: " + street);
        System.out.println("hs: " + houseNum);
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("loginusername");
        List<ItemModel> cartList = itemInCartDAO.getCartItems(itemInCartDAO.getLoggedUser(username).getCartId());

        if("".equals(state) || "".equals(street) || "".equals(houseNum)) {
            System.out.println(state);
            redirectAttributes.addAttribute("orderErr" , "Some fields are missing");
            return "redirect:/cart";
        }
        for(ItemModel item : cartList) {
            itemInCartDAO.setOrderInDatabase(item.getItemId(), username);
        }

        itemInCartDAO.deleteItemFromCart(itemInCartDAO.getLoggedUser(username).getCartId());
        itemInCartDAO.insertStateToUser(username, state, street, houseNum);

        return "redirect:/";
    }
}
