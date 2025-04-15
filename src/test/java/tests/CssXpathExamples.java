package tests;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExamples {

    void cssXpath() {

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("[data-testid=email]").setValue("1");
        $(by("data-testid", "email")).setValue("1");

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">

        $("[id=email]").setValue("1");
        $(by("id", "email")).setValue("1");
        $(byId("email")).setValue("1"); // сразу поиск по id (selenide)
        $("input#email").setValue("1"); // можно с input , можно без него. Результат будет одинаковый
        $x("//*[@id='email']").setValue("1");

        // <input type="email" class="inputtext login_form_input_box" name="email">

        $("[name=email]").setValue("1"); // можно было бы написать input[name=email]
        $(by("name", "email")).setValue("1");
        $(byName("email")).setValue("1");
        $x("//*[@name='email']").setValue("1");

        // <input type="email" class="inputtext login_form_input_box">

        $("[class=inputtext] [class=login_form_input_box]").setValue("1");
        $(".login_form_input_box").setValue("1");
        $(".inputtext.login_form_input_box").setValue("1");

        // <div class="inputtext">
        //      <input type="email" class="login_form_input_box">
        // </div>

        $(".inputtext .login_form_input_box").setValue("1"); // нужен пробел после inputtext, так как у нас два класса
        $("div.inputtext input.login_form_input_box").setValue("1");


        // <div>Hello, qa.guru!</div>
        $(byText("Hello, qa.guru!"));
        $(withText("llo, qa.gu"));
        $x("//*[contains(text(),'Hello, qa,guru!')]");

    }

}
