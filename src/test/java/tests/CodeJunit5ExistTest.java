package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CodeJunit5ExistTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void checkCodeJunit5OnPageSoftAssertionsTest() {


        // Открыть страницу Selenide в Github
        open("/selenide/selenide");

        // Перейти в раздел Wiki проекта
        $("#wiki-tab").click();

        // В списке страниц (Pages) есть страница SoftAssertions
        $("div.markdown-body").$("ul").$$("li").shouldHave(itemWithText("Soft assertions"));

        // Открыть страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body a[href$='SoftAssertions']").click(); // a[href$='SoftAssertions'] - внутри него ищет ссылку (<a>), у которой атрибут href заканчивается на 'SoftAssertions'

        $("#wiki-body").$(".markdown-body").$$(".markdown-heading").shouldHave(itemWithText("3. Using JUnit5 extend test class:"));

        $("#wiki-body").$(".markdown-body").$(byText("@ExtendWith({SoftAssertsExtension.class})\n" + // .markdown-body - один, поэтому находится текст. Если бы было несколько, то нужен $$
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

    }
}
