package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "contact name")) {
//                for (var lastname : List.of("", "lastname")) {
//                        for (var email : List.of("", "email")) {
//                            for (var photo : List.of("", "src/test/resources/images/avatar.png")) {
//                                result.add(new ContactData().
//                                        withFirstName(firstname).
//                                        withLastName(lastname).
//                                        withEmail(email).
//                                        withPhoto(photo));
//                            }
//                 }
//                }
//            }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withEmail("").withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}