package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactDeletionTests extends TestBase {
    @Test
    public void canDeleteContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "firstname", "lastname", "email", ""));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canDeleteAllContactsAtOnce ()
    {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "firstname", "lastname", "email", ""));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}

