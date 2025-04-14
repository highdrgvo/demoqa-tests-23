package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";

    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Alex");
        $("#userEmail").setValue("alex@alex.com"); /* вместо id=userName можно написать #name. Работает для id и  классов */
        $("#currentAddress").setValue("Street 1");
        $("#permanentAddress").setValue("Street 2");
        $("#submit").click();

        $("#output #name").shouldHave(text("Alex"));
        $("#output").$("#email").shouldHave(text("alex@alex.com"));
        $("#output #currentAddress").shouldHave(text("Street 1"));
        $("#output").$("#permanentAddress").shouldHave(text("Street 2"));

    }
    }