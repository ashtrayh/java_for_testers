package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void canDeleteContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().removeContact();
    }

    @Test
    void canDeleteAllContactsAtOnce ()
    {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("firstname", "middlename","lastname", "address", "email"));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}

