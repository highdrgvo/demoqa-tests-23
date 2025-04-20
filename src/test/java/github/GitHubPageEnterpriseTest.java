package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GitHubPageEnterpriseTest {

    @Test

    void gitHubCheckPageEnterpriseTest() {

        open("https://github.com/");
        Configuration.browserSize="1920x1080";

        $(byText("Solutions")).hover();
        $(byText("Enterprises")).click();

        $("#hero-section-brand-heading").shouldBe(visible).shouldHave(Condition.text("The AI-powered developer platform"));

    }
}