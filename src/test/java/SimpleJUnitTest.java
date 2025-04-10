import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleJUnitTest {

    @Test
    void firstTest () {
        System.out.println("###    firstTest()");
        Assertions.assertTrue(14 > 3);
    }

}
