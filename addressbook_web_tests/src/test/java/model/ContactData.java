package model;

public record ContactData(String id,
                          String firstname,
                          String lastname,
                          String email,
                          String photo,
                          String home,
                          String mobile,
                          String work,
                          String secondary,
                          String address,
                          String email2,
                          String email3) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, email, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, photo, this.home, this.mobile, this.work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withHomePhone(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, this.photo, home, this.mobile, this.work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withMobilePhone(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, this.photo, this.home, mobile, this.work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withWorkPhone(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, this.photo, this.home, this.mobile, work, this.secondary, this.address, this.email2, this.email3);
    }

    public ContactData withSecondaryPhone(String secondary) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, this.photo, this.home, this.mobile, this.work, secondary, this.address, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, address, this.email2, this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.lastname, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.email2, email3);
    }
}
