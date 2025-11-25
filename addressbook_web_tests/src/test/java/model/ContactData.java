package model;

public record ContactData(String firstname, String middlename, String lastname, String address, String email) {
    public ContactData() {
        this("","","","","");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.address, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.address, email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstname, this.middlename, this.lastname, address, this.email);
    }

    public ContactData withMiddleAndLastName(String middlename, String lastname) {
        return new ContactData(this.firstname, middlename, lastname, this.address, this.email);
    }
}
