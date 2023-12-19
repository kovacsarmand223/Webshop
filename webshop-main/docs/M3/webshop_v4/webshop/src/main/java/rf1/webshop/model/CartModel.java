package rf1.webshop.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Date;

public class CartModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="kosarid")
    private int cartId;

    @Column(name="modositasdatuma")
    private Date updateDate;

    public CartModel(int cartId, Date updateDate) {
        this.cartId = cartId;
        this.updateDate = updateDate;
    }

    public CartModel(int cartId) {
        this.cartId = cartId;
        this.updateDate=null;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
