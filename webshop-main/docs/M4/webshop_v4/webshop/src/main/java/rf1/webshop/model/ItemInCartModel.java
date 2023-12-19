package rf1.webshop.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
public class ItemInCartModel implements Serializable {

    @Column(name="kosarid")
    private int cartCartId;

    @Column(name="termekazonosito")
    private int cartItemId;

    public ItemInCartModel(int cartCartId, int cartItemId) {
        this.cartCartId = cartCartId;
        this.cartItemId = cartItemId;
    }

    public void setCartCartId(int cartCartId) {
        this.cartCartId = cartCartId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }
}
