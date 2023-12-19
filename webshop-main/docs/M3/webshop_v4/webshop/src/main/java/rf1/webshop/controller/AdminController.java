package rf1.webshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rf1.webshop.dao.*;
import rf1.webshop.model.CartModel;
import rf1.webshop.model.CustomerModel;
import rf1.webshop.model.ItemModel;
import rf1.webshop.model.UserModel;
import rf1.webshop.util.PasswordHash;

import java.sql.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private AdminDAO adminDAO;

    private PasswordHash passwordHash;

    @GetMapping("/admin")
    public String listUserAndItems(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        UserModel actualUser=userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
        if(actualUser==null || !actualUser.isPermission()){
            redirectAttributes.addAttribute("erradmin", "YOU HAVE NO PERMISSION TO ACCESS THIS PAGE!");
            return "redirect:/";
        }
        /*if(!actualUser.isPermission()){
            return "redirect:/login";
        }*/
        model.addAttribute("actualUser",actualUser);
        List<UserModel> userList = userDAO.getListUsers();
        model.addAttribute("users", userList);
        UserModel selectedUser=userList==null || userList.isEmpty()?new UserModel("","","","",false,"","",0):userList.get(0);
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("selectedUserRole",selectedUser.isPermission()?"vasarlo":"admin");
        List<ItemModel> itemList = itemDAO.getListItems();
        model.addAttribute("items",itemList);
        ItemModel selectedItem=itemList==null || itemList.isEmpty()?new ItemModel(0,"","","",0,false,"",0):itemList.get(0);
        model.addAttribute("selectedItem", selectedItem);
        model.addAttribute("selectedItemAvailable",selectedItem.isAvailable()?"igen":"nem");
        return "admin";
    }

    @PostMapping(value = "/admin/deleteuser/{username}")
    public String deleteUser(@PathVariable("username") String username,
                             HttpSession session
    ) {
        try{
            UserModel actualUser=userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
            userDAO.deleteUser(username);
            adminDAO.createAdminEntry(actualUser.getUsername(), Date.valueOf(java.time.LocalDate.now()),"delete ("+username+") user");
            return "redirect:/admin";
        }catch (Exception e){
            System.err.println("deleteUser error: "+e);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/selectuser/{username}")
    public String selectUser(@PathVariable("username") String username, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            listUserAndItems(model,session, redirectAttributes);
            UserModel user = userDAO.getUserByUserName(username);
            model.addAttribute("selectedUser",user);
            model.addAttribute("selectedUserRole",user.isPermission()?"admin":"vasarlo");
            return "admin";
        }catch (Exception e){
            System.err.println("selectUser error: "+e);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/adduser")
    public String addUser(@ModelAttribute UserModel userModel,
                               @RequestParam("vezeteknev") String lastName,
                               @RequestParam("keresztnev") String firstName,
                               @RequestParam("felhasznalonev") String username,
                               @RequestParam("jelszo1") String password1,
                               @RequestParam("jelszo2") String password2,
                               @RequestParam("jogosultsag") String permission,
                               @RequestParam("telepules") String settlement,
                               @RequestParam("utca") String street,
                               @RequestParam("hazszam") String houseNumber,
                            HttpSession session
    ) {
        try {
            if("".equals(lastName) || "".equals(firstName) || "".equals(username) || "".equals(password1) || "".equals(password2)){
                return "redirect:/admin";

            }
            if(userDAO.getUserByUserName(username)!=null){
                return "redirect:/admin";
            }
            if(password1!=null && password1.equals(password2) && password1.length()>=8){
                UserModel user = new UserModel(firstName,lastName,username, PasswordHash.getHashedPassword(password1),"admin".equals(permission),street,settlement,Integer.parseInt(houseNumber));
                userDAO.createUser(user,true);
                int cartId=0;
                do{
                    cartId++;
                }while(cartDAO.getCartByCartId(cartId)!=null);
                CartModel cart=new CartModel(cartId);
                cartDAO.createNewCart(cart);
                customerDAO.createCustomer(new CustomerModel(user.getUsername(),cart.getCartId()));
                UserModel actualUser=userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
                adminDAO.createAdminEntry(actualUser.getUsername(), Date.valueOf(java.time.LocalDate.now()),"add ("+username+") user");
                return "redirect:/admin";
            }
            return "redirect:/admin";
        }catch (Exception e){
            System.err.println("addUser error: "+e);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/updateuser")
    public String updateUser(
                          @RequestParam("vezeteknev") String lastName,
                          @RequestParam("keresztnev") String firstName,
                          @RequestParam("felhasznalonev") String username,
                          @RequestParam("jogosultsag") String permission,
                          @RequestParam("telepules") String settlement,
                          @RequestParam("utca") String street,
                          @RequestParam("hazszam") String houseNumber,
                          HttpSession session
                          ) {
        try{
            if(userDAO.getUserByUserName(username)==null){
                return "redirect:/admin";
            }
            UserModel user=new UserModel(firstName,lastName,street,settlement,Integer.parseInt(houseNumber));
            userDAO.updateUser(username,user);
            userDAO.setPermission(username,"admin".equals(permission));
            UserModel actualUser=userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
            adminDAO.createAdminEntry(actualUser.getUsername(), Date.valueOf(java.time.LocalDate.now()),"update ("+username+") user");
            return "redirect:/admin";
        }catch (Exception e){
            System.err.println("updateUser error: "+e);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/deleteitem/{itemId}")
    public String deleteItem(@PathVariable("itemId") String itemId, HttpSession session) {
        try {
            itemDAO.deleteItem(Integer.parseInt(itemId));
            UserModel actualUser=userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
            adminDAO.createAdminEntry(actualUser.getUsername(), Date.valueOf(java.time.LocalDate.now()),"delete ("+itemId+") item");
            return "redirect:/admin";
        }catch (Exception e){
            System.err.println("deleteItem error: "+e);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/selectitem/{itemId}")
    public String selectItem(@PathVariable("itemId") String itemId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            listUserAndItems(model,session, redirectAttributes);
            ItemModel item = itemDAO.getItemByItemId(itemId);
            model.addAttribute("selectedItem",item);
            return "admin";
        }catch (Exception e){
            System.err.println("selectItem eror: "+e);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/additem")
    public String addItem(
                              @RequestParam("nev") String name,
                              @RequestParam("gyarto") String manufacturer,
                              @RequestParam("leiras") String description,
                              @RequestParam("ar") String price,
                              @RequestParam("elerheto") String available,
                              @RequestParam("szarmazasiorszag") String country,
                              @RequestParam("osztalyzas") String rating,
                              HttpSession session){
        try {
            ItemModel item=new ItemModel(name,manufacturer,description,Integer.parseInt(price),"igen".equals(available),country,Integer.parseInt(rating));
            itemDAO.createItem(item);
            UserModel actualUser=userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
            adminDAO.createAdminEntry(actualUser.getUsername(), Date.valueOf(java.time.LocalDate.now()),"add ("+name+") item");
            return "redirect:/admin";
        }catch (Exception e){
            System.err.println("addItem error: "+e);
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/updateitem")
    public String updateItem(
            @RequestParam("termekazonosito") String itemId,
            @RequestParam("nev") String name,
            @RequestParam("gyarto") String manufacturer,
            @RequestParam("leiras") String description,
            @RequestParam("ar") String price,
            @RequestParam("elerheto") String available,
            @RequestParam("szarmazasiorszag") String country,
            @RequestParam("osztalyzas") String rating,
            HttpSession session){
        try {
            ItemModel item=new ItemModel(Integer.parseInt(itemId),name,manufacturer,description,Integer.parseInt(price),"igen".equals(available),country,Integer.parseInt(rating));
            if(itemDAO.getItemByItemId(itemId)==null){
                return "redirect:/admin";
            }
            itemDAO.updateItem(item.getItemId(),item);
            UserModel actualUser=userDAO.getUserByUserName((String)session.getAttribute("loginusername"));
            adminDAO.createAdminEntry(actualUser.getUsername(), Date.valueOf(java.time.LocalDate.now()),"update ("+itemId+") item");
            return "redirect:/admin";
        }catch (Exception e){
            System.err.println("updateItem error: "+e);
            return "redirect:/admin";
        }
    }
}
