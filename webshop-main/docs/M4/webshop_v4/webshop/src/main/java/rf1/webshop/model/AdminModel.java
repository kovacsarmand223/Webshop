package rf1.webshop.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Date;

public class AdminModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="felhasznalonev")
    private String username;

    @Column(name="modositasdatuma")
    private Date changeDate;

    @Column(name="modositasneve")
    private String changeName;

    public AdminModel(String username, Date changeDate, String changeName) {
        this.username = username;
        this.changeDate = changeDate;
        this.changeName = changeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }
}
