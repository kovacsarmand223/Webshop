package rf1.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rf1.webshop.dao.ItemDAO;
import rf1.webshop.dao.UserDAO;
import rf1.webshop.model.ItemModel;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ItemDAO itemDAO;



    @GetMapping(value = "/")
    public String listItems(Model model){
        System.out.println("listitem called");
        List<ItemModel> itemList = itemDAO.getListItems();
        model.addAttribute("items", itemList);

        return "index";
    }

}

