package rf1.webshop.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Date;

public class CustomerModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="felhasznalonev")
    private String username;

    @Column(name="kosarid")
    private int cartId;

    @Column(name="mikor")
    private Date dateOfPurchase;

    @Column(name="felhasznalonev")
    private Date lastLogin;

    public CustomerModel(String username, int cartId, Date dateOfPurchase, Date lastLogin) {
        this.username = username;
        this.cartId = cartId;
        this.dateOfPurchase = dateOfPurchase;
        this.lastLogin = lastLogin;
    }

    public CustomerModel(String username, int cartId) {
        this.username = username;
        this.cartId = cartId;
        this.dateOfPurchase = null;
        this.lastLogin = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
