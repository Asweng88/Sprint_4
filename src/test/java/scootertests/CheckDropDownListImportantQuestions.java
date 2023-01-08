package scootertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum_services.qa_scooter.MainPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class CheckDropDownListImportantQuestions {

    private WebDriver driver;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

     @Test
    public void checkOpenDropDownList () {

        List<WebElement> question = driver.findElements(MainPage.getDropDownQuestion());
        List<WebElement> answer = driver.findElements(MainPage.getDropDownAnswer());

        String[] answerText = {
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области."
        };

        for (int i=0; i < question.size(); i++ ) {
            // скролинг до элемента

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question.get(i));

            // проверка кликабильности
            ExpectedConditions.elementToBeClickable(question.get(i));


            // проверка что элемент не раскрыт
            ExpectedConditions.attributeToBe(question.get(i), "aria-expanded", "false");

            // нажатие на элемент списка «Вопросы о важном»
            question.get(i).click();

            // проверка что элемент раскрыт
            ExpectedConditions.attributeToBe(question.get(i), "aria-expanded", "true");

            // проверка содержания текста ответа на вопрос
            int finalI = i;
            new WebDriverWait(driver, 10).until(driver -> (answer.get(finalI).getText() != null
                    && !answer.get(finalI).getText().isEmpty()
            ));
            assertTrue(answer.get(i).getText().equals(answerText[i]));
        }
    }


    @After
    public void tearDown() {
        driver.quit(); // Закрыть браузер
    }
}
