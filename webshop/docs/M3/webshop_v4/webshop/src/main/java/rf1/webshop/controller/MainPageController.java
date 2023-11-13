package rf1.webshop.controller;

import org.h2.engine.Mode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rf1.webshop.dao.ItemDAO;
import rf1.webshop.dao.UserDAO;
import rf1.webshop.model.ItemModel;
import rf1.webshop.model.UserModel;
import rf1.webshop.service.WebshopService;
import rf1.webshop.util.PasswordHash;
import java.security.Principal;
import java.util.ArrayList;
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

