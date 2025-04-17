package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class Snippets {

    void browser_command_examples() {
        open("https://google.com");
        open("/customer/orders");     // -Dselenide.baseUrl=http://123.23.23.1
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("", "user", "password"));

        Selenide.back(); // перейти назад
        Selenide.refresh(); // обновить страницу

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear();"); // no Selenide command for this yet. Очистить sessionStorage

        Selenide.confirm(); // в диалогом окне (алерте) можно применять, где надо да или нет (dismiss) указать
        Selenide.dismiss(); //

        Selenide.closeWindow(); // close active tab
        Selenide.closeWebDriver(); // close browser completely

        Selenide.switchTo().frame("new"); // https://the-internet.herokuapp.com/frames Frame - это как отдельно-независимое DOM дерево. Чтобы искать в фрейме для начала в него надо перейти с помощью команды. Без команды ничего не получится.
        Selenide.switchTo().defaultContent(); // Если хотим поискать что-то на сайте снова, то нам надо к исходному дереву вернуться (короче, выйти из фрейма)

        Selenide.switchTo().window("The Internet"); //  для перехода между окнами

        var cookie = new Cookie("foo", "bar"); // определяем куки
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); // вебдрайвер Селениум добавляет куки (как сделаю, надо страницу обновлять)


    }

    void selectors_examples() {
        $("div").click(); // Поиск элементов
        element("div").click(); // Для языка котлин подходит ($ является зарезирвированным элементом в котлин)

        $("div", 2).click(); // Помогает найти 3 div, если нужно

        $x("//h1/div").click(); // через xpath искать что то
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click(); // для поиска по тексту. Тут поиск по всему тексту
        $(withText("ull tex")).click(); // Тут часть текста ищет

        $(byTagAndText("div", "full text")); // можно искать по тегу с текстом (полный текст)
        $(withTagAndText("div", "ull text")); // часть текста

        $("[data-testid=results-list]").parent(); // cssSelector только можно двигаться вниз по дереву. Родительский элемент
        $("").sibling(1); // это поиск вниз по дереву (счет с 0 начинается)
        $("").preceding(1); // это поиск вверх
        $("").closest("div"); // Мы ищем предка, который имеет класс div
        $("").ancestor("div"); // the same as closest $("image").ancestor("div") - найдем ближайший к image div.
        $("div:last-child"); // поиск последнего ребенка (в данном случае последний div)

        $("div").$("h1").find(byText("abc")).click(); // находим div, внутри него h1, внутри h1 текст abc

        $$(".prc-Link-Link-85e08").findBy(Condition.text("selenide/selenide")).click(); // $$ - Находит все элементы с классом "prc-Link-Link-85e08" // Из найденной коллекции выбирает первый элемент, содержащий точный текст "selenide/selenide" ;
        // возвращает первый подходящий элемент (или ошибку). Condition.text() используется для проверки текста элемента. Condition.text() - ближе к Selenide, find(byText - ближе к Selenium

        // very optional
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click(); // такой поиск по id уже делал

        $(byId("mytext")).click();
        $("#mytext").click(); // такой поиск по id уже делал

        $(byClassName("red")).click();
        $(".red").click();
    }

    void actions_examples() {
        $("").click();
        $("").doubleClick();
        $("").contextClick(); // правый клик мышли м

        $("").hover(); // подвести мышку , но не нажимать

        $("").setValue("text"); // записываем текст в поле для ввода
        $("").append("text"); // добавит текст в конец
        $("").clear(); // очистить поле
        $("").setValue(""); // очистить поле (аналог clear)

        $("div").sendKeys("c"); // hotkeys. Симулирование нажатия клавиш в определенном элементе
        actions().sendKeys("c").perform(); //hotkey . Симулирование нажатия просто на странице.
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F, комбинации клавиш. Chord - комбинация.
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // Можно на корневой элемент посылать.

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();


        // complex actions with keybord and mouse, example
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform(); // Начинать с action и обязательно закончить perform. Че тут написано: перейти к элементу div,
        // потом зажать кнопку и не отпускать, x/y Offset - это насколько по оси x/y двинуть мышь, release отпустить клавишу действия, perform выполнить все действия.

        // old html actions don't work with many modern frameworks
        $("").selectOption("dropdown_option"); // если дропдаун скрыт до момента клика на него, то сначала кликаем на элемент, а потом выбираем что нужно из дропдауна.
        $("").selectRadio("radio_options");

    }

    void assertions_examples() {
        $("").shouldBe(visible); // и shouldHave, should - работают одинаково
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);


        //longer timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30)); // бывает, что нужно подождать какой то элемент

    }

    void conditions_examples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc")); // будет смотреть, что в каком то тексте есть abc
        $("").shouldHave(exactText("abc")); // будет смотреть, что весь текст содержит только abc

        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$")); // regex позволяет сделать сложные проверки

        $("").shouldHave(cssClass("red")); // проверяет, содержит ли элемент данный класс
        $("").shouldHave(cssValue("font-size", "12")); // для проверки стиля и размера текста

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked); // for checkboxes. В данном случае должен быть флажок в чекбоксе установлен.

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        $("").should(exist); // Он проверяет наличие в DOM, но не его видимость. Нужен для проверки полей невидимых, что они существуют.

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled); // Только проверяет есть ли атрибут disabled или нет. Если его нет (в devTools --> elements), то надо у FE спросить как класс называется.
        $("").shouldBe(enabled);
    }

    void collections_examples() {

        $$("div"); // does nothing! // ищет все дивы.

        $$x("//div"); // by XPath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1)); // фильтровать коллекцию, из дивов выбираем только те, где есть текст 123
        $$("div").excludeWith(text("123")).shouldHave(size(1)); // противоположность filterBy

        $$("div").first().click(); // взять первый элемент среди всех дивов.
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0)
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); //  finds first, where text 123 exists.

        // assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); // the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); // проверяет кол-во текстов. Если будет еще и Zetta, то тест упадет. Но если будет кол-во 3 и в Alfa указано 333, то проверка пройдет успешно.
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); // проверяет и кол-во и текст. Все должно быть четко.

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa")); // игнорирует расположение элементов, может быть в любом порядке.
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        $$("").shouldHave(itemWithText("Gamma")); // когда нужно убедиться, что в тексте именно этот текст есть.

        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));


    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");  // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')"); //
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }
}
