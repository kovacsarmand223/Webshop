package rf1.webshop.model;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ItemModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="termekazonosito")
    private int itemId;

    @Column(name="nev")
    private String name;

    @Column(name="gyarto")
    private String manufacturer;

    @Column(name="leiras")
    private String description;

    @Column(name="ar")
    private int price;

    @Column(name="elerhetoe")
    private boolean available;

    @Column(name="szarmazasiorszag")
    private String country;

    @Column(name="osztalyzas")
    private int rating;

    public ItemModel(int itemId, String name, String manufacturer, String description, int price, boolean available, String country, int rating) {
        this.itemId = itemId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.price = price;
        this.available = available;
        this.country = country;
        this.rating = rating;
    }

    public ItemModel(int itemId, String name, int price, boolean available, int rating) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.available = available;
        this.rating = rating;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
