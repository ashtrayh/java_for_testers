package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("Ivanov", "Ivanovich", "Ivanov", "Tverskaya, 36", "ivanov@gmail.com"));
    }

    @Test
    public void canCreateContactWithEmptyFields() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFirstNameOnly() {
        app.contacts().createContact(new ContactData().withFirstName("Petr"));
    }

    @Test
    public void canCreateContactWithAddressOnly() {
        app.contacts().createContact(new ContactData().withAddress("Prospect Lenina, 86"));
    }

    @Test
    public void canCreateContactWithEmailOnly() {
        app.contacts().createContact(new ContactData().withEmail("sidorov@gmail.com"));
    }

    @Test
    public void canCreateContactWithMiddleAndLastNameOnly() {
        app.contacts().createContact(new ContactData().withMiddleAndLastName("Petrovich", "Petrov"));
    }

}