package tests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll() {

        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 20000;
        Configuration.holdBrowserOpen = true;


        Configuration.headless = false; // Сначала запустим в видимом режиме
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        System.out.println("Запуск теста с Firefox...");

        open("https://demoqa.com/text-box");
        System.out.println("Страница загружена");

        $("#userName").setValue("Alex");
        $("#userEmail").setValue("alex@egorov.com");
        $("#currentAddress").setValue("Some street 1");
        $("#permanentAddress").setValue("Another street 1");
        $("#submit").click();

        $("#output").shouldHave(
                text("Alex"),
                text("alex@egorov.com"),
                text("Some street 1"),
                text("Another street 1")
        );

        System.out.println("Тест успешно завершен!");
    }
}