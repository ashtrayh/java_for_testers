package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class ContactModificationTests extends TestBase {
    @Test
    void canModiFyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "lastname", "email", "", "", "", "", "", "", "", ""));
            app.contacts().reloadHomePage();
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canAddContactInGroup() {
        {
            if (app.hbm().getContactCount() == 0) {
                app.hbm().createContact(new ContactData("", "firstname", "lastname", "email", "", "", "", "", "", "", "", ""));
                app.contacts().reloadHomePage();
            }
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
                app.contacts().reloadHomePage();
            }
            var group = app.hbm().getGroupList().get(0);
            int groupId = group.getId();
            var contactIds = app.hbm().getContactIdList();
            var oldRelated = app.hbm().getContactsInGroup(group);
            boolean contactAdded = false;
            for (int contactId : contactIds) {
                if (!app.jdbc().isContactInGroup(contactId, groupId)) {
                    var contact = new ContactData().withId(String.valueOf(contactId));
                    app.contacts().addContactInGroup(contact, group);
                    contactAdded = true;
                    break;
                }
            }
            if (!contactAdded) {
                app.hbm().createContact(new ContactData("", "firstname", "lastname", "email", "", "", "", "", "", "", "", ""));
                var allContacts = app.hbm().getContactList();
                var contact = allContacts.get(allContacts.size() - 1);
                app.contacts().reloadHomePage();
                app.contacts().addContactInGroup(contact, group);
            }
            var newRelated = app.hbm().getContactsInGroup(group);
            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        }
    }

    @Test
    void canRemoveContactFromGroup() {
        {
            if (app.hbm().getContactCount() == 0) {
                app.hbm().createContact(new ContactData("", "firstname", "lastname", "email", "", "", "", "", "", "", "", ""));
                app.contacts().reloadHomePage();
            }
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
                app.contacts().reloadHomePage();
            }
            var contact = app.hbm().getContactList().get(0);
            var group = app.hbm().getGroupList().get(0);
            var oldRelated = app.hbm().getContactsInGroup(group);
            if (!oldRelated.isEmpty()) {
                contact = oldRelated.get(0);
                app.contacts().removeContactFromGroup(contact, group);
            } else {
                app.contacts().addContactInGroup(contact, group);
                oldRelated = app.hbm().getContactsInGroup(group);
                app.contacts().removeContactFromGroup(contact, group);
            }
            var newRelated = app.hbm().getContactsInGroup(group);
            Comparator<ContactData> compareByIdAndFirstname = (o1, o2) -> {
                int compareId = Integer.compare(
                        Integer.parseInt(o1.id()),
                        Integer.parseInt(o2.id())
                );
                if (compareId != 0) {
                    return compareId;
                }
                return o1.firstname().compareTo(o2.firstname());
            };
            newRelated.sort(compareByIdAndFirstname);
            ContactData finalContact = contact;
            var expectedList = oldRelated.stream().
                    filter(c -> !c.id().equals(finalContact.id())).
                    collect(Collectors.toCollection(ArrayList::new));
            expectedList.sort(compareByIdAndFirstname);
            Assertions.assertEquals(newRelated, expectedList);
        }
    }
}
