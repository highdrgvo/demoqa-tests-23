package github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BestContributorToSelenideTest {

    @Test
    void andreiSolncevShouldBeTheFirstContributor() {

        Configuration.browserSize = "1920x1080"; // можно менять по требованию

        // открыть главную страницу
        open("https://github.com/selenide/selenide");

        // Навести мышку на первого человека
        $("div.Layout-sidebar").$(byText("Contributors")).closest("h2").sibling(0).$$("li").first().hover();   // div.Layout0sidebar - это мы обращаемся к классу в диве.
        // closest("h2") - поднимаемся наверх до первого h2, который удовлетворяет условию
        // sibling - ближайший к h2 класс ul (index = 0) , ищем li (все li с помощью $$), и нам нужен первый элемент (first()), hover() - навести мышку

        // Убедиться, что там Андрей Солнцев
        // если поп-ап открывается после наведения на него мыши и быстро исчезает, если убрать курсор, то можно перейти на вкладку Sources, нажать fn + F8 и затем идти в elements и искать нужный нам символ.

        $$(".Popover").findBy(visible).shouldHave(text("Andrei Solntsev"));


        sleep(5000);


    }

}
