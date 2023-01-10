package scootertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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

@RunWith(Parameterized.class)
public class CheckDropDownListImportantQuestions {

    //Добавь необходимые поля
    private final int questionNumber;
    private final String answerText;


    public CheckDropDownListImportantQuestions(int questionNumber, String answerText) {
        this.questionNumber = questionNumber;
        this.answerText = answerText;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderScooter() {
        //Сгенерируй тестовые данные
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

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

            // скролинг до элемента

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question.get(questionNumber));

            // проверка кликабильности
            ExpectedConditions.elementToBeClickable(question.get(questionNumber));


            // проверка что элемент не раскрыт
            ExpectedConditions.attributeToBe(question.get(questionNumber), "aria-expanded", "false");

            // нажатие на элемент списка «Вопросы о важном»
            question.get(questionNumber).click();

            // проверка что элемент раскрыт
            ExpectedConditions.attributeToBe(question.get(questionNumber), "aria-expanded", "true");

            // проверка содержания текста ответа на вопрос
            new WebDriverWait(driver, 10).until(driver -> (answer.get(questionNumber).getText() != null
                    && !answer.get(questionNumber).getText().isEmpty()
            ));
            assertTrue(answer.get(questionNumber).getText().equals(answerText));
        }



    @After
    public void tearDown() {
        driver.quit(); // Закрыть браузер
    }
}
