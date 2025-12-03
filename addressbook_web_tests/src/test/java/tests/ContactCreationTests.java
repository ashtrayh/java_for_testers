package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareByIdAndEmail = (o1, o2) -> {
            int compareId = Integer.compare(
                    Integer.parseInt(o1.id()),
                    Integer.parseInt(o2.id())
            );
            if (compareId != 0) {
                return compareId;
            }
            return o1.email().compareTo(o2.email());
        };
        newRelated.sort(compareByIdAndEmail);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withId(newRelated.get(newRelated.size() - 1).id()).withPhoto(""));
        expectedList.sort(compareByIdAndEmail);
        Assertions.assertEquals(newRelated, expectedList);
    }
}