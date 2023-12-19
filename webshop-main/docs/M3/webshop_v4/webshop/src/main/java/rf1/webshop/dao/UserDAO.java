package rf1.webshop.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import rf1.webshop.model.UserModel;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /**
     * Létrehoz egy felhasználót az adatbázisban
     */
    public void createUser(UserModel userModel){
        String sql = "INSERT INTO felhasznalo(felhasznalonev, jelszo, vezeteknev, keresztnev, telepules, utca, hazszam) VALUES (?, ?, ?, ?, ?, ?, ?)";
        //getJdbcTemplate().update(sql, new Object[] {
        //    userModel.getUsername(),userModel.getPassword(),userModel.getFirstName(),userModel.getLastName(),userModel.isPermission(),userModel.getSettlement(),userModel.getStreet(),userModel.getHouseNumber()
        //});
        getJdbcTemplate().update(sql, new Object[] {
                userModel.getUsername(),userModel.getPassword(),userModel.getFirstName(),userModel.getLastName(),"NONE","NONE",0
        });
    }

    /**
     * Létrehoz egy felhasználót az adatbázisban
     */
    public void createUser(UserModel userModel, boolean full){
        if(!full){
            createUser(userModel);
        }
        String sql = "INSERT INTO felhasznalo(felhasznalonev, jelszo, vezeteknev, keresztnev, jogosultsag, telepules, utca, hazszam) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[] {
            userModel.getUsername(),
            userModel.getPassword(),
            userModel.getLastName(),
            userModel.getFirstName(),
            userModel.isPermission()?1:0,
            userModel.getSettlement(),
            userModel.getStreet(),
            userModel.getHouseNumber()
        });
    }

    /**
     * Lekérdezi az összes felhasználót az adatbázisban
     * @return egy lista, ami tárolja az összes létező felhasználót
     */
    public List<UserModel> getListUsers(){
        String sql = "SELECT * FROM felhasznalo";
        List < Map < String, Object >> rows = getJdbcTemplate().queryForList(sql);
        if(rows.isEmpty()){
            return null;
        }
        List<UserModel> users=new ArrayList<>();
        for (Map< String, Object > row: rows) {
            UserModel user=new UserModel(
                    (String)row.get("keresztnev"),
                    (String)row.get("vezeteknev"),
                    (String)row.get("felhasznalonev"),
                    (String)row.get("jelszo"),
                    ((int)row.get("jogosultsag"))!=0,
                    (String)row.get("utca"),
                    (String)row.get("telepules"),
                    (int)row.get("hazszam")
            );
            users.add(user);
        }
        return users;
    }

    /**
     * Lekérdezést végez a felhasználónév alapján
     * @param userName megadott felhasználónév
     * @return egy felhasználó, ha nem létezik akkor null
     */
    public UserModel getUserByUserName(String userName){
        String sql = "SELECT * FROM felhasznalo WHERE felhasznalonev=?";
        List < Map < String, Object >> rows = getJdbcTemplate().queryForList(sql, userName);
        if(rows.isEmpty()){
            return null;
        }
        for (Map< String, Object > row: rows) {
            return new UserModel(
                    (String)row.get("keresztnev"),
                    (String)row.get("vezeteknev"),
                    (String)row.get("felhasznalonev"),
                    (String)row.get("jelszo"),
                    ((int)row.get("jogosultsag"))!=0,
                    (String)row.get("utca"),
                    (String)row.get("telepules"),
                    (int)row.get("hazszam")
            );
        }
        return null;
    }

    /**
     * Frissiti a felhasznalo megadott adatait, kivéve a jelszót, felhasználónevet
     * @param userName frissitendő felhasználó
     * @param user frissített adatokkal feltöltött modell
     */
    public void updateUser(String userName, UserModel user){
        if(user==null){
            return;
        }
        String sql="UPDATE felhasznalo SET vezeteknev=?, keresztnev=?, telepules=?, utca=?, hazszam=? WHERE felhasznalonev=?";
        getJdbcTemplate().update(sql,new Object[]{
                user.getLastName(),
                user.getFirstName(),
                user.getSettlement(),
                user.getStreet(),
                user.getHouseNumber(),
                userName
        });
    }

    public void changePwd(String pwd, UserModel user){
        if (user == null) {
            return;
        }
        String sql="UPDATE felhasznalo SET jelszo=? WHERE felhasznalonev=?";
        getJdbcTemplate().update(sql, new Object[]{
                pwd,
                user.getUsername()
        });
    }

    /**
     * Kitöröl egy felhasználót felhasználónév alapján
     * @param userName felhasználónév
     */
    public void deleteUser(String userName){
        String sql="DELETE FROM felhasznalo WHERE felhasznalonev=?";
        getJdbcTemplate().update(sql,new Object[]{
                userName
        });
    }

    /**
     * Megváltoztatja a megadott felhasználó jogosultságát
     * @param userName felhasználó
     * @param permission, ha true akkor 1(admin) lesz beállítva egyébként 0(user)
     */
    public void setPermission(String userName, boolean permission){
        String sql="UPDATE felhasznalo SET jogosultsag=? WHERE felhasznalonev=?";
        getJdbcTemplate().update(sql,new Object[]{
                permission?1:0,
                userName
        });
    }
}
