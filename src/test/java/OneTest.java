import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class OneTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000

    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("FirstName");
        $("#lastName").setValue("lastName");
        $("#userEmail").setValue("name@egmail.com");
        actions().moveToElement($("#gender-radio-1")).click().build().perform();
        $("#userNumber").setValue("1234567890");
        actions().moveToElement($("#dateOfBirthInput")).click().build().perform();
        $(".react-datepicker__month-select").$(byText("November")).click();
        $(".react-datepicker__year-select").$(byText("1993")).click();
        $(".react-datepicker__day.react-datepicker__day--004").click();
        $("#subjectsInput").setValue("E").sendKeys(Keys.ENTER);
        actions().moveToElement($("#hobbies-checkbox-1")).click().build().perform();
        $("#currentAddress").setValue("Another street 1");
        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#state").click();
        $("#react-select-3-input").setValue("N").sendKeys(Keys.ENTER);
        $("#city").click();
        $("#react-select-4-input").setValue("D").sendKeys(Keys.ENTER);
        $("#submit").click();
        $(".table-responsive").shouldHave(text("FirstName"));
        $(".table-responsive").shouldHave(text("lastName"));
        $(".table-responsive").shouldHave(text("name@egmail.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $(".table-responsive").shouldHave(text("04 November,1993"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("image.jpg"));
        $(".table-responsive").shouldHave(text("Another street 1"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

    }
}
