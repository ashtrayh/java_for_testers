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
    public String address;
    public String home;
    public String mobile;
    public String work;
    public String fax = "default";
    public String email2;
    public String email3;
    public String homepage = "default";
    public String phone2;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String email, String home, String mobile, String work, String secondary, String address, String email2, String email3) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.phone2 = secondary;
        this.address = address;
        this.email2 = email2;
        this.email3 = email3;
    }
}
