package manager.hbm;

import jakarta.persistence.*;


@Entity
@Table(name="addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String email;
    public String middlename = "default";
    public String nickname = "default";
    public String company = "default";
    public String title = "default";
    public String address  = "default";
    public String home = "default";
    public String mobile = "default";
    public String work = "default";
    public String fax = "default";
    public String email2 = "default";
    public String email3 = "default";
    public String homepage = "default";

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
