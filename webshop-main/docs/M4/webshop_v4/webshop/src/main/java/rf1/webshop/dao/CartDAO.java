package rf1.webshop.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import rf1.webshop.model.CartModel;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class CartDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /**
     * Létrehoz egy új kosarat az adatbázisban
     * Módosítás dátuma alapértelmezett értéket kap
     * @param cartModel egy kosár modell
     */
    public void createNewCart(CartModel cartModel){
        String sql = "INSERT INTO kosar(kosarid) VALUES (?)";
        getJdbcTemplate().update(sql, new Object[] {
            cartModel.getCartId()
        });
    }

    /**
     * Visszaadja a keresett kosarat egy kosarat kosarid alapján
     * @param kosarid kosár azonosítója
     * @return egy kosármodell
     */
    public CartModel getCartByCartId(int kosarid){
        String sql = "SELECT kosarid FROM kosar WHERE kosarid=?";
        List < Map < String, Object >> rows = getJdbcTemplate().queryForList(sql, kosarid);
        if(rows.isEmpty()){
            return null;
        }
        for (Map< String, Object > row: rows) {
            return new CartModel(
                    (int)row.get("kosarid")
            );
        }
        return null;
    }
}
