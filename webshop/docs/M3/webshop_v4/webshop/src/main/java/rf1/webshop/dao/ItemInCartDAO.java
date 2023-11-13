package rf1.webshop.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import rf1.webshop.model.ItemInCartModel;
import rf1.webshop.model.ItemModel;

import javax.sql.DataSource;
import java.util.ArrayList;
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
        List<ItemModel> cartItems = new ArrayList<ItemModel>();
        for (ItemInCartModel item : itemInCartModels) {
            ItemModel it = getItemByItemId(item.getCartItemId());
            cartItems.add(it);
        }

        return cartItems;
    }
}
