package rf1.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rf1.webshop.dao.ItemInCartDAO;
import rf1.webshop.model.ItemModel;

import java.util.List;

@Controller
public class ItemInCartController {

    @Autowired
    private ItemInCartDAO itemInCartDAO;
    @GetMapping(path = "/cart")
    public String getCartPage(Model model) {
        System.out.println("getCartPage called");  // Add this line for debugging
        return "cart";
    }

    @GetMapping(value = "/index")
    public String listItemsInCart(Model model) {
        //ide kell a bejelentkezett felhasznalo kosarid-je!!!!
        System.out.println("listItemsInCart called");  // Add this line for debugging
        List<ItemModel> items = itemInCartDAO.getCartItems(0);
        model.addAttribute("items", items);

        return "cart";
    }
}
