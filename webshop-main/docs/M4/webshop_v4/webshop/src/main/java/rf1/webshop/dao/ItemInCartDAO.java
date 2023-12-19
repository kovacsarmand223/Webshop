package rf1.webshop.dao;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import rf1.webshop.model.CustomerModel;
import rf1.webshop.model.ItemInCartModel;
import rf1.webshop.model.ItemModel;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ItemInCartDAO extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<ItemInCartModel> getCartItemIds(int cartId){
        String sql = "SELECT * FROM belekerul WHERE kosarid = ?";
        assert getJdbcTemplate() != null;
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql, cartId);
        if(rows.isEmpty()){
            return null;
        }
        List<ItemInCartModel> items=new ArrayList<>();
        for (Map< String, Object > row: rows) {
            ItemInCartModel item = new ItemInCartModel(
                    (int)row.get("kosarid"),
                    (int)row.get("termekazonosito")
            );

            items.add(item);
        }
        return items;
    }

    public ItemModel getItemByItemId(int itemId){
        String sql = "SELECT * FROM termek WHERE termekazonosito=?";
        List < Map < String, Object >> rows = getJdbcTemplate().queryForList(sql, itemId);
        if(rows.isEmpty()){
            return null;
        }
        for (Map< String, Object > row: rows) {
            return new ItemModel(
                    (int)row.get("termekazonosito"),
                    (String)row.get("nev"),
                    (String)row.get("gyarto"),
                    (String)row.get("leiras"),
                    (int)row.get("ar"),
                    ((int)row.get("elerhetoe"))!=0,
                    (String)row.get("szarmazasiorszag"),
                    (int)row.get("osztalyzas")
            );
        }
        return null;
    }

    public List<ItemModel> getCartItems(int cartId) {
        List<ItemInCartModel> itemInCartModels = getCartItemIds(cartId);
        if(itemInCartModels == null) {
            itemInCartModels = new ArrayList<>();
        }
        List<ItemModel> cartItems = new ArrayList<ItemModel>();
            for (ItemInCartModel item : itemInCartModels) {
                ItemModel it = getItemByItemId(item.getCartItemId());
                cartItems.add(it);
            }


        return cartItems;
    }

    public CustomerModel getLoggedUser(String username) {
        String sql = "SELECT * FROM vasarlo WHERE felhasznalonev = ?";
        assert getJdbcTemplate() != null;
        List < Map < String, Object >> rows = getJdbcTemplate().queryForList(sql, username);
        if(rows.isEmpty()){
            return null;
        }
        for (Map< String, Object > row: rows) {
            return new CustomerModel(
                    (String)row.get("felhasznalonev"),
                    (int)row.get("kosarid"),
                    (Date)row.get("mikor"),
                    (Date)row.get("utolsobejelentkezes")
            );
        }
        return null;
    }

    public void addItemToCart(HttpServletRequest request, int itemId) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("loginusername");
        System.out.println(username);
        System.out.println(getLoggedUser(username).getCartId());
        String sql = "INSERT INTO belekerul (kosarid, termekazonosito) VALUES (?, ?)";
        assert getJdbcTemplate() != null;

        getJdbcTemplate().update(sql, new Object[] {
                getLoggedUser(username).getCartId(), itemId
        });
    }

    public void setOrderInDatabase(int itemId, String username) {
        String sql = "INSERT INTO megrendel (felhasznalonev, termekid) VALUES (?, ?)";
        assert getJdbcTemplate() != null;

        getJdbcTemplate().update(sql, new Object[] {
                username, itemId
        });
    }

    public void deleteItemByItemId(int itemId) {
        String sql = "DELETE FROM belekerul WHERE termekazonosito=" + itemId;
        getJdbcTemplate().update(sql);
    }

    public void deleteItemFromCart(int cartId) {
        String sql = "DELETE FROM belekerul WHERE kosarid=" + cartId;
        getJdbcTemplate().update(sql);
    }

    public void insertStateToUser(String username, String state, String street, String houseNum) {
        String sql = "UPDATE felhasznalo SET telepules = ?, utca = ?, hazszam = ? WHERE felhasznalonev = ?";
        assert getJdbcTemplate() != null;
        System.out.println(state);
        System.out.println(street);
        System.out.println(houseNum);
        System.out.println(username);

        getJdbcTemplate().update(sql, new Object[] {
                state, street, Integer.valueOf(houseNum), username
        });
    }
}
