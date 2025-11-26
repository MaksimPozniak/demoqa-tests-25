package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @Test
    void fillPracticeFormTest() {
        // Настройка браузера
        Configuration.browser = "chrome";

        // Открытие страницы с формой
        open("https://demoqa.com/automation-practice-form");

        // Заполнение личных данных
        $("#firstName").setValue("Alex");           // Имя
        $("#lastName").setValue("Egorov");          // Фамилия
        $("#userEmail").setValue("alex@egorov.com"); // Email
        $(byText("Male")).click();                  // Выбор пола
        $("#userNumber").setValue("1234567890");    // Номер телефона

        // Выбор даты рождения
        $("#dateOfBirthInput").click();                            // Открытие календаря
        $(".react-datepicker__month-select").selectOption("January"); // Месяц
        $(".react-datepicker__year-select").selectOption("1990");    // Год
        $(".react-datepicker__day--001").click();                   // День (1 января)

        // Выбор предметов
        $("#subjectsInput").setValue("Math").pressEnter()    // Математика
                .setValue("Physics").pressEnter(); // Физика

        // Выбор хобби
        $(byText("Sports")).click();   // Спорт
        $(byText("Reading")).click();  // Чтение
        $(byText("Music")).click();    // Музыка

        // Заполнение адреса и выбор местоположения
        $("#currentAddress").setValue("123 Main Street, Moscow, Russia"); // Адрес
        $("#state").click(); $(byText("NCR")).click();  // Штат
        $("#city").click(); $(byText("Delhi")).click(); // Город

        // Отправка формы
        $("#submit").click();

        // Проверка результатов в модальном окне
        $(".table-responsive").shouldHave(
                text("Alex Egorov"),                      // ФИО
                text("alex@egorov.com"),                  // Email
                text("Male"),                             // Пол
                text("1234567890"),                       // Телефон
                text("01 January,1990"),                  // Дата рождения
                text("Maths, Physics"),                   // Предметы
                text("Sports, Reading, Music"),           // Хобби
                text("123 Main Street, Moscow, Russia"),  // Адрес
                text("NCR Delhi")                         // Штат и город
        );

        // Модальное окно закрывать не нужно - тест завершится автоматически
    }
}