package scootertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum_services.qa_scooter.MainPage;
import ru.praktikum_services.qa_scooter.OrderPage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class orderScooter {

    //Добавь необходимые поля
    private final String button;
    private final String name;
    private final String lastname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String rentalPeriod;

    public orderScooter(String button, String name, String lastname, String address, String metroStation, String phoneNumber, String rentalPeriod) {
        this.button = button;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.rentalPeriod = rentalPeriod;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderScooter() {
        //Сгенерируй тестовые данные
        return new Object[][] {
                {"upperButtonOrder", "Василий", "Васильев", "Москва", "Студенческая", "89999000000", "сутки"},
                {"lowerButtonOrder", "Иван", "Иванов", "Ростов", "Беляево", "89999000001", "двое суток"},
        };
    }

    private WebDriver driver;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void testOrderScooter () {

        // определение текущей даты
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

        new MainPage(driver)
                .open()
                .scrollButtonOrder(button)
                .clickButtonOrder(button);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillInputName(name);
        orderPage.fillInputLastname(lastname);
        orderPage.fillInputAddress(address);
        orderPage.fillInputPhoneNumber(phoneNumber);
        orderPage.clickInputMetroStation();
        orderPage.selectMetroStationList(metroStation);
        orderPage.clickButtonNext();
        orderPage.clickInputRentalPeriod();
        orderPage.selectRentalPeriodList(rentalPeriod);
        orderPage.fillInputWhenBring(formatForDateNow.format(dateNow));
        orderPage.clickButtonOrder();
        orderPage.clickButtonConfirmationOrder();

        assertEquals("Заказ оформлен", driver.findElement(OrderPage.getHeaderStatusOrder()).getText());
    }


    @After
    public void tearDown() {
        driver.quit(); // Закрыть браузер
    }
}
