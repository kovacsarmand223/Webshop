package rf1.webshop.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import rf1.webshop.model.CustomerModel;

import javax.sql.DataSource;

@Repository
public class CustomerDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /**
     * Létrehoz egy vásárlót felhasználónév és kosárId összekötésével
     * @param customerModel vásárlómodell, felhasználónév és kosáridvel
     */
    public void createCustomer(CustomerModel customerModel){
        String sql = "INSERT INTO vasarlo(felhasznalonev,kosarid) VALUES (?,?)";
        getJdbcTemplate().update(sql, new Object[] {
            customerModel.getUsername(),
            customerModel.getCartId()
        });
    }
}
