package model;

public record ContactData(String firstname) {
    public ContactData() {
        this("");
    }

    public ContactData withName(String firstname) {
        return new ContactData(firstname);
    }
}
