package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

    }

    @Test
    void fillPracticeFormTest() {
        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        // Заполнение формы студента

        $("#firstName").setValue("Ser");
        $("[id=lastName]").setValue("Ple"); /* вместо id=userName можно написать #name. Работает для id и  классов */
        $(by("id", "userEmail")).setValue("hello@ggg.com");
        $$("label").findBy(text("Male")).click();
        $("#userNumber").setValue("9993332211");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1999");
        $$("div.react-datepicker__day").findBy(text("1")).click();

        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("PNG_transparency_demonstration_1.png");
        // $("#uploadPicture").uploadFile(new File("src/test/resources/PNG_transparency_demonstration_1.png"));
        $("#currentAddress").setValue("City centre 1");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        // Сравнение внесенных данных с итоговой формой

        $("[class=table-responsive]").$(byText("Student Name")).parent().shouldHave(text("Ser Ple")); // или сокращенно ".table-responsive" (обращение к классу)
        $("[class=table-responsive]").$(byText("Student Email")).parent().shouldHave(text("hello@ggg.com"));
        $("[class=table-responsive]").$(byText("Gender")).parent().shouldHave(text("Male"));
        $("[class=table-responsive]").$(byText("Mobile")).parent().shouldHave(text("9993332211"));
        $("[class=table-responsive]").$(byText("Date of Birth")).parent().shouldHave(text("01 May,1999"));
        $("[class=table-responsive]").$(byText("Subjects")).parent().shouldHave(text("Chemistry"));
        $("[class=table-responsive]").$(byText("Hobbies")).parent().shouldHave(text("Sports"));
        $("[class=table-responsive]").$(byText("Picture")).parent().shouldHave(text("PNG_transparency_demonstration_1.png"));
        $("[class=table-responsive]").$(byText("Address")).parent().shouldHave(text("City centre 1"));
        $("[class=table-responsive]").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));


    }
}