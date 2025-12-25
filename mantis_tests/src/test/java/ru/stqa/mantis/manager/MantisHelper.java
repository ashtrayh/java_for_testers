package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.UserInfo;

public class MantisHelper extends HelperBase {

    public MantisHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createUser(UserInfo user) {
        openUsersPage();
        initUserCreation();
        fillRegistrationForm(user);
        submitUserCreation();
    }

    public void finishRegistration(String username, String password, String password2) {
        fillSecondRegistrationForm(username, password, password2);
        confirmRegistration();
    }

    private void confirmRegistration() {
        click(By.cssSelector("button[type='submit']"));
    }

    private void fillSecondRegistrationForm(String username, String password, String password2) {
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password2);
    }


    private void submitUserCreation() {
        click(By.cssSelector("input[type='submit']"));
    }

    private void fillRegistrationForm(UserInfo user) {
        type(By.name("username"), user.username());
        type(By.name("realname"), user.realname());
        type(By.name("email"), user.email());
    }

    private void initUserCreation() {
        click(By.linkText("Create New Account"));
    }

    private void openUsersPage() {
        if (!isElementPresent(By.linkText("Create New Account"))) {
            click(By.cssSelector(String.format("a[href^='/mantisbt-2.26.4/manage_overview_page.php']")));
            click(By.linkText("Users"));
        }
    }
}
    

