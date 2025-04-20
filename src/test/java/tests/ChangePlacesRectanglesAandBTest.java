package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ChangePlacesRectanglesAandBTest {

    @Test

    void changePlacesRectanglesAandBTest() {

        Configuration.browserSize="1920x1080";

        open("https://the-internet.herokuapp.com/drag_and_drop");

        actions().clickAndHold(getFocusedElement().findElement(By.cssSelector("#column-a"))).moveToElement(getFocusedElement().findElement(By.cssSelector("#column-b"))).release().perform();
        $("#column-a").shouldHave(Condition.text("B"));

    }
}
