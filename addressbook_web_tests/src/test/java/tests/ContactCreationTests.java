package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "contact name")) {
            for (var middlename : List.of("", "middlename")) {
                for (var lastname : List.of("", "lastname")) {
                    for (var address : List.of("", "address")) {
                        for (var email : List.of("", "email"))
                            result.add(new ContactData(firstname, middlename, lastname, address, email));
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(randomString(i), randomString(i), randomString(i), randomString(i), randomString(i)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}