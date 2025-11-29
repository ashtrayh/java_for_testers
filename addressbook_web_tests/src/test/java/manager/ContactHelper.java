package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        openHomePage();
        selectContact();
        removeSelectedContacts();
        returnToHomePage();
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("add"))) {
            click(By.linkText("home"));
        }
    }

    private void initContactCreation() {
        if (!manager.isElementPresent(By.name("photo"))) {
            click(By.linkText("add new"));
        }
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void deleteAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
        returnToHomePage();
    }

    private void selectAllContacts() {
        click(By.cssSelector(String.format("input[id='MassCB']")));
    }

}
