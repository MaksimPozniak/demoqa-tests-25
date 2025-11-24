package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 40000;
        Configuration.headless = false;
    }

    @Test
    void fillAutomationPracticeForm() {
        // Открываем страницу practice form
        open("https://demoqa.com/automation-practice-form");
        System.out.println("Открыта страница Automation Practice Form");

        // === ЗАПОЛНЯЕМ ФОРМУ ===

        // 1. Основная информация
        $("#firstName").setValue("Алексей");
        $("#lastName").setValue("Егоров");
        $("#userEmail").setValue("alexey@example.com");

        // 2. Выбираем пол
        $("#genterWrapper").$(byText("Male")).click();

        // 3. Номер телефона
        $("#userNumber").setValue("1234567890");

        // 4. Дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--015:not(.react-datepicker__day--outside-month)").click();

        // 5. Предметы (Subjects)
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();

        // 6. Хобби (Hobbies)
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        // 7. Пропускаем загрузку файла (если нет файла)
        // $("#uploadPicture").uploadFromClasspath("test.jpg");

        // 8. Текущий адрес
        $("#currentAddress").setValue("Москва, ул. Тверская, д. 1");

        // 9. Штат и город
        $("#state").click();
        $(byText("NCR")).click();

        $("#city").click();
        $(byText("Delhi")).click();

        // === ОТПРАВЛЯЕМ ФОРМУ ===
        $("#submit").click();

        // === ПРОВЕРЯЕМ РЕЗУЛЬТАТЫ ===
        $(".modal-content").shouldBe(visible);

        // Проверяем все заполненные данные в таблице
        $(".table-responsive").shouldHave(
                text("Алексей Егоров"),
                text("alexey@example.com"),
                text("Male"),
                text("1234567890"),
                text("15 July,1990"),
                text("Maths, Physics"),
                text("Sports, Reading, Music"),
                text("Москва, ул. Тверская, д. 1"),
                text("NCR Delhi")
        );

        // Закрываем модальное окно
        $("#closeLargeModal").click();

        System.out.println("✅ Automation Practice Form тест успешно завершен!");
    }
}