package rf1.webshop.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import rf1.webshop.model.ItemModel;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ItemDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /**
     * Létrehoz egy terméket az adatbázisban
     * @param itemModel termék információkat tartalmazó model
     */
    public void createItem(ItemModel itemModel){
        String sql = "INSERT INTO termek(nev, gyarto, leiras, ar, elerhetoe, szarmazasiorszag, osztalyzas) VALUES (?,?,?,?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                itemModel.getName(),
                itemModel.getManufacturer(),
                itemModel.getDescription(),
                itemModel.getPrice(),
                itemModel.isAvailable()?1:0,
                itemModel.getCountry(),
                itemModel.getRating()
        });
    }

    /**
     * Létrehoz egy terméket az adatbázisban, de kihagyható, ami az adatbázisban kihagyhatóak
     * @param itemModel termék információkat tartalmazó model, kihagyható adatokkal
     * @param missingData true, ha hiányos adatokkal akarjuk léthezni a terméket
     */
    public void createItem(ItemModel itemModel, boolean missingData){
        if(!missingData){
            createItem(itemModel);
            return;
        }
        String sql = "INSERT INTO termek(nev, ar, elerhetoe, osztalyzas) VALUES (?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                itemModel.getName(),
                itemModel.getPrice(),
                itemModel.isAvailable()?1:0,
                itemModel.getRating()
        });
    }


    /**
     * Kilistázza az adatbázisba feltöltött termékeket
     * @return termékek listája
     */
    public List<ItemModel> getListItems(){
        String sql = "SELECT * FROM termek";
        List < Map < String, Object >> rows = getJdbcTemplate().queryForList(sql);
        if(rows.isEmpty()){
            return null;
        }
        List<ItemModel> items=new ArrayList<>();
        for (Map< String, Object > row: rows) {
            ItemModel item=new ItemModel(
                    (int)row.get("termekazonosito"),
                    (String)row.get("nev"),
                    (String)row.get("gyarto"),
                    (String)row.get("leiras"),
                    (int)row.get("ar"),
                    ((int)row.get("elerhetoe"))!=0,
                    (String)row.get("szarmazasiorszag"),
                    (int)row.get("osztalyzas")
            );
            items.add(item);
        }
        return items;
    }

    /**
     * Egyetlen termék lekérdezése termékazonosító alapján
     * @param itemId termék azonosítója
     * @return adott termék, ha megtalálható, egyébként null
     */
    public ItemModel getItemByItemId(String itemId){
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

    /**
     * Frissit egy termeket termekazonosito alapján
     * @param termekazonosito adott termék
     * @param item termék információi
     */
    public void updateItem(int termekazonosito, ItemModel item){
        if(item==null){
            return;
        }
        String sql="UPDATE termek SET nev=?,gyarto=?,leiras=?,ar=?,elerhetoe=?,szarmazasiorszag=?,osztalyzas=? WHERE termekazonosito=?";
        getJdbcTemplate().update(sql,new Object[]{
                item.getName(),
                item.getManufacturer(),
                item.getDescription(),
                item.getPrice(),
                item.isAvailable()?1:0,
                item.getCountry(),
                item.getRating(),
                termekazonosito
        });
    }

    /**
     * Kitöröl egy adott terméket
     * @param termekazonosito adott termék azonosítója
     */
    public void deleteItem(int termekazonosito){
        String sql="DELETE FROM termek WHERE termekazonosito=?";
        getJdbcTemplate().update(sql,new Object[]{
                termekazonosito
        });
    }
}
