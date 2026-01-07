package pages.components;  // ← ВНИМАНИЕ: другой package!

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);

        String daySelector = day.length() == 1 ?
                ".react-datepicker__day--00" + day :
                ".react-datepicker__day--0" + day;

        $(daySelector + ":not(.react-datepicker__day--outside-month)").click();
    }
}