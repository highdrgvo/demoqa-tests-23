package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.*; // нужен, чтобы в начале строки перед open / $ и т д не писать Selenide.
import static com.codeborne.selenide.WebDriverConditions.url;

public class SelenideRepositorySearchTestCase {

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {


        // открыть главную страницу
        open("https://github.com/");

        // ввести в поле поиска selenide и нажать enter
        $(".search-input").click(); // так пишется класс
        $("#query-builder-test").setValue("selenide").pressEnter();

        // кликнуть на первый репозиторий из списка найденных
        $$(".prc-Link-Link-85e08").findBy(text("selenide/selenide")).click(); // Находит все элементы с классом .prc-Link-Link-85e08

        // проверка: заголовок selenide/selenide
        webdriver().shouldHave(url("https://github.com/selenide/selenide")); // чтобы отдельно элементы не проверять


        sleep(5000);


        // ARRANGE - подготовить (не всегда нужен)
        // ACT - действие
        // ASSERT - проверка (не всегда нужен)
        // Это три действия которые должны быть при написании теста (Не всегда нужны arrange и assert, по смыслу надо смотреть, когда то или иное нужно)
        // .classname, #id1, [], a[href=abc].red


    }

}

// прежде чем автоматизировать, надо пройти все руками, чтобы убедиться, что все работает.