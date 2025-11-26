package tests;  // Указываем что класс находится в пакете tests

// ИМПОРТЫ - подключаем необходимые классы и методы
import com.codeborne.selenide.Configuration;  // Класс для настройки Selenide
import org.junit.jupiter.api.Test;            // Аннотация для标记 тестовых методов

// СТАТИЧЕСКИЕ ИМПОРТЫ - импортируем методы напрямую для удобства чтения
import static com.codeborne.selenide.Condition.*;      // Условия для проверок (visible, text и т.д.)
import static com.codeborne.selenide.Selectors.byText; // Поиск элементов по тексту
import static com.codeborne.selenide.Selenide.*;       // Основные методы Selenide (open, $, $$ и т.д.)

/**
 * Класс для тестирования формы Automation Practice Form на сайте DemoQA
 * Демонстрирует автоматизацию заполнения веб-формы и проверки результатов
 */
public class PracticeFormTest2 {

    /**
     * Основной тестовый метод - выполняется при запуске теста
     * Аннотация @Test указывает JUnit что это тестовый метод
     */
    @Test
    void fillPracticeFormTest() {

        // === НАСТРОЙКА БРАУЗЕРА ===
        Configuration.browser = "chrome";
        // Указываем какой браузер использовать для теста
        // Возможные значения: "chrome", "firefox", "edge", "safari"

        // ДОПОЛНИТЕЛЬНЫЕ НАСТРОЙКИ (рекомендуется добавить):
        // Configuration.browserSize = "1920x1080";  // Размер окна браузера
        // Configuration.timeout = 10000;            // Таймаут ожидания элементов (10 секунд)
        // Configuration.headless = false;           // Режим без графического интерфейса

        // === ЭТАП 1: ОТКРЫТИЕ СТРАНИЦЫ ===
        open("https://demoqa.com/automation-practice-form");
        // Метод open() загружает указанный URL в браузере
        // Selenide автоматически ждет полной загрузки страницы

        // === ЭТАП 2: ЗАПОЛНЕНИЕ ЛИЧНЫХ ДАННЫХ ===

        $("#firstName").setValue("Alex");
        // $("#firstName") - CSS селектор, ищет элемент с id="firstName"
        // $ - метод Selenide для поиска ОДНОГО элемента
        // setValue("Alex") - вводит текст "Alex" в поле ввода

        $("#lastName").setValue("Egorov");
        // Аналогично заполняем поле фамилии

        $("#userEmail").setValue("alex@egorov.com");
        // Заполняем поле email

        $(byText("Male")).click();
        // $(byText("Male")) - ищет элемент с ТОЧНЫМ текстом "Male"
        // byText() - специальный селектор для поиска по тексту
        // .click() - выполняет клик по найденному элементу

        $("#userNumber").setValue("1234567890");
        // Заполняем поле номера телефона
        // На реальном проекте лучше использовать случайные данные

        // === ЭТАП 3: ВЫБОР ДАТЫ РОЖДЕНИЯ ===

        $("#dateOfBirthInput").click();
        // Кликаем по полю даты чтобы открыть календарь-виджет

        $(".react-datepicker__month-select").selectOption("January");
        // $(".react-datepicker__month-select") - ищет элемент по классу
        // .selectOption("January") - выбирает опцию "January" из выпадающего списка

        $(".react-datepicker__year-select").selectOption("1990");
        // Аналогично выбираем год 1990

        $(".react-datepicker__day--001").click();
        // Выбираем 1 января (день с номером 001)
        // На реальном сайте может потребоваться:
        // $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)")

        // === ЭТАП 4: ВЫБОР ПРЕДМЕТОВ (SUBJECTS) ===

        $("#subjectsInput").setValue("Math").pressEnter();
        // Вводим "Math" в поле subjects и нажимаем Enter
        // На сайте DemoQA это автокомплит поле - Enter подтверждает выбор

        $("#subjectsInput").setValue("Physics").pressEnter();
        // Аналогично добавляем второй предмет

        // === ЭТАП 5: ВЫБОР ХОББИ (HOBBIES) ===

        $(byText("Sports")).click();
        // Ищем элемент с текстом "Sports" и кликаем по нему
        // Это чекбокс для выбора хобби

        $(byText("Reading")).click();
        $(byText("Music")).click();
        // Выбираем остальные хобби

        // ВАЖНО: На некоторых версиях сайта может потребоваться уточнение селектора:
        // $("#hobbiesWrapper").$(byText("Sports")).click();

        // === ЭТАП 6: ЗАПОЛНЕНИЕ АДРЕСА И ВЫБОР ЛОКАЦИИ ===

        $("#currentAddress").setValue("123 Main Street, Moscow, Russia");
        // Заполняем поле адреса многострочным текстом

        $("#state").click();
        // Кликаем по полю "State" чтобы открыть выпадающий список
        $(byText("NCR")).click();
        // Выбираем опцию "NCR" из появившегося списка

        $("#city").click();
        // Кликаем по полю "City"
        $(byText("Delhi")).click();
        // Выбираем "Delhi" из списка городов

        // === ЭТАП 7: ОТПРАВКА ФОРМЫ ===

        $("#submit").click();
        // Находим кнопку отправки формы по id и кликаем по ней
        // После клика форма отправляется и появляется модальное окно с результатами

        // === ЭТАП 8: ПРОВЕРКА РЕЗУЛЬТАТОВ ===

        $(".table-responsive").shouldHave(
                // Метод shouldHave() проверяет что элемент содержит ВСЕ указанные условия
                // В данном случае проверяем наличие всех введенных данных в таблице результатов

                text("Alex Egorov"),                      // Проверяем что ФИО отображается правильно
                text("alex@egorov.com"),                  // Проверяем email
                text("Male"),                             // Проверяем выбранный пол
                text("1234567890"),                       // Проверяем номер телефона
                text("01 January,1990"),                  // Проверяем дату рождения
                text("Maths, Physics"),                   // Проверяем выбранные предметы
                text("Sports, Reading, Music"),           // Проверяем хобби
                text("123 Main Street, Moscow, Russia"),  // Проверяем адрес
                text("NCR Delhi")                         // Проверяем штат и город
        );
        // Если какой-то из текстов отсутствует - тест упадет с ошибкой

        // Модальное окно закрывать не нужно - тест завершится автоматически
        // Браузер закроется автоматически после выполнения теста
    }
}