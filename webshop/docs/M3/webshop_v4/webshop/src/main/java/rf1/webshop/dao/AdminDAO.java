package rf1.webshop.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;

@Repository
public class AdminDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void createAdminEntry(String username, Date changeDate, String changeName){
        String sql = "INSERT INTO admin(felhasznalonev, modositasdatuma, modositasneve) VALUES (?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                username,
                changeDate,
                changeName
        });
    }
}
