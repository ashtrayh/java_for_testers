package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserInfo;
import ru.stqa.mantis.model.UserInfoForApi;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(5);
        var email = String.format(username + "@localhost");
        try {
            app.jamesApi().addUser(email, "password");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (!app.session().isLoggedIn()) {
            app.session().login("administrator", "root");
        }
        var user = new UserInfo().withUsername(username).withRealname(username).withEmail(email);
        app.mantis().createUser(user);
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        if (url != null) {
            app.driver().get(url);
        } else {
            throw new RuntimeException("Ссылка не найдена");
        }
        app.mantis().finishRegistration(username, "password", "password");
        app.http().login(username, "password");
        app.http().isLoggedIn();
    }

    @Test
    void canRegisterUserWithRestApi() {
        var username = CommonFunctions.randomString(5);
        var email = String.format(username + "@localhost");
        try {
            app.jamesApi().addUser(email, "password");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var user = new UserInfoForApi().withUsername(username).withEmail(email);
        app.rest().createUser(user);
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        if (url != null) {
            app.driver().get(url);
        } else {
            throw new RuntimeException("Ссылка не найдена");
        }
        app.mantis().finishRegistration(username, "password", "password");
        app.http().login(username, "password");
        app.http().isLoggedIn();
    }
}
