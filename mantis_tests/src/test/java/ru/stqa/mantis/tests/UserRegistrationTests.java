package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        //создать емейл (JamesHelper)
        //заполнить форму создания и отправить (браузер)
        //ждём почту (MailHelper)
        //извлечь ссылку
        //проходим по ссылке и завершаем регистрацию (браузер)
        //проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }
}
