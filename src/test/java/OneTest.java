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
import static com.codeborne.selenide.Selenide.*;


public class OneTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
        String filePath = "https://img.freepik.com/premium-photo/cute-smart-cat-programmer-working-night-laptop-illustration-red-pet-glasses-typing-computer-keyboard-generative-ai_305419-3350.jpg";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("FirstName");
        $("#lastName").setValue("lastName");
        $("#userEmail").setValue("name@egmail.com");
        actions().moveToElement($("#gender-radio-1")).click().build().perform();
        $("#userNumber").setValue("1234567890");
        actions().moveToElement($("#dateOfBirthInput")).click().build().perform();
        Select monthSelect = new Select($(".react-datepicker__month-select"));
        monthSelect.selectByValue("10");
        Select yearSelect = new Select($(".react-datepicker__year-select"));
        yearSelect.selectByValue("1993");
        SelenideElement dayElement = $x("//div[text()='4' and contains(@class, 'react-datepicker__day')]");
        dayElement.click();
        $("#subjectsInput").setValue("E").sendKeys(Keys.ENTER);
        actions().moveToElement($("#hobbies-checkbox-1")).click().build().perform();
        $("#currentAddress").setValue("Another street 1");
        String url = "https://img.freepik.com/premium-photo/cute-smart-cat-programmer-working-night-laptop-illustration-red-pet-glasses-typing-computer-keyboard-generative-ai_305419-3350.jpg";//Эта строка устанавливает строковую переменную url в URL изображения.
        File file = new File(url);//Эта строка создает новый объект File с URL в качестве пути. Однако, это не будет работать как ожидается, потому что конструктор File ожидает локальный путь файла, а не URL.
        if (!file.exists()) { //Эта строка проверяет, существует ли объект File. Поскольку объект File был создан с URL, он не будет существовать как локальный файл, поэтому код внутри блока if будет выполнен.
            file = new File("image.jpg");//Эта строка создает новый объект File с именем "image.jpg" в текущей рабочей директории.
            try (InputStream in = new URL(url).openStream()) { //Эта строка открывает соединение с URL и создает InputStream для чтения данных изображения.
                Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);//Эта строка копирует данные изображения из InputStream в локальный файл "image.jpg" с помощью метода Files.copy().
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        $("#uploadPicture").uploadFile(file);//Эта строка загружает локальный файл "image.jpg" в форму с помощью метода uploadFile() из Selenide.
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
