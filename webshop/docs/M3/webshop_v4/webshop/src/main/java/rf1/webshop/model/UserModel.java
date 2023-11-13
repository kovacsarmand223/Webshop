package rf1.webshop.model;

import jakarta.persistence.Column;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * UserModel
 * Felhasználók adatbázisban tárolt modelljének osztály reprezentációja
 */
public class UserModel implements UserDetails{
    private static final long serialVersionUID = 1L;

    /**
     * Felhasznaló keresztneve
     */
    @Column(name="keresztnev")
    private String firstName;

    /**
     * Felhasznaló vezetékneve
     */
    @Column(name="vezeteknev")
    private String lastName;

    /**
     * Felhasználó felhasználóneve
     */
    @Column(name="felhasznalonev")
    private String userName;

    /**
     * Felhasználó jelszava
     */
    @Column(name="jelszo")
    private String password;

    /**
     * Felhasználó jogosultsága: 0 felhasználó, 1 admin
     */
    @Column(name="jogosultsag")
    private boolean permission=false;

    /**
     * Felhasználó utcája
     */
    @Column(name="utca")
    private String street;

    /**
     * Felhasználó települése
     */
    @Column(name="telepules")
    private String settlement;

    /**
     * Felhasználó házszáma
     */
    @Column(name="hazszam")
    private int houseNumber;

    private boolean isAuthenticated;

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String userName, String password, boolean permission, String street, String settlement, int houseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.permission=permission;
        this.street = street;
        this.settlement = settlement;
        this.houseNumber = houseNumber;
    }

    public UserModel(String firstName, String lastName, String userName, String password, boolean permission) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.permission = permission;
        this.isAuthenticated = false;
    }

    public UserModel(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public UserModel(String firstName, String lastName, String street, String settlement, int houseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.settlement = settlement;
        this.houseNumber = houseNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set< GrantedAuthority > authorities = new HashSet< GrantedAuthority >();
        authorities.add(new SimpleGrantedAuthority(permission?"user":"admin"));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String permissionString(){
        return permission?"admin":"vásárló";
    }
}
