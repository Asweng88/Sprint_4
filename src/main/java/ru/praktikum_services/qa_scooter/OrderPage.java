package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrderPage {

    private static WebDriver webDriver;

    public OrderPage(WebDriver webDriver) {
        this.webDriver=webDriver;
    }

    // поле Имя
    private static By inputName = By.xpath("//input[@placeholder=\"* Имя\"]");
    // поле Фамилия
    private static By inputLastname = By.xpath("//input[@placeholder=\"* Фамилия\"]");
    // поле Адрес
    private static By inputAddress = By.xpath("//input[@placeholder=\"* Адрес: куда привезти заказ\"]");
    // поле телефон
    private static By inputPhoneNumber = By.xpath("//input[@placeholder=\"* Телефон: на него позвонит курьер\"]");
    // поле станция метро
    private static By inputMetroStation = By.xpath("//input[@placeholder=\"* Станция метро\"]");
    // кнопки списка станций
    private static By metroStationList = By.xpath("//li[@class=\"select-search__row\"]/button");
    // кнопка Далее
    private static By buttonNext = By.xpath("//button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");
    // поле когда привезти самокат
    private static By inputWhenBring= By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\"]");
    // поле срок Аренды
    private static By inputRentalPeriod = By.xpath("//*[@class=\"Dropdown-control\"]");
    // меню срок Аренды
    private static By rentalPeriodList = By.xpath("//*[@class=\"Dropdown-menu\"]/*");
    // кнопка заказать
    private static By buttonOrder= By.xpath("//button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");
    // кнопка подтверждения заказа
    private static By buttonConfirmationOrder= By.xpath("//button[text()=\"Да\"]");

    // заголовок статуса заказа
    private static By headerStatusOrder = By.xpath("//*[@class=\"Order_ModalHeader__3FDaJ\"]/*");




    public void clickInputMetroStation() {
        webDriver.findElement(inputMetroStation).click();
    }

    public static By getHeaderStatusOrder() {
        return headerStatusOrder;
    }

    public void fillInputName(String name) {
        webDriver.findElement(inputName).sendKeys(name);
    }

    public void fillInputLastname(String lastname) {
        webDriver.findElement(inputLastname).sendKeys(lastname);
    }

    public void fillInputAddress(String address) {
        webDriver.findElement(inputAddress).sendKeys(address);
    }

    public void fillInputPhoneNumber(String phoneNumber) {
        webDriver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    public void fillInputWhenBring(String date) {
        webDriver.findElement(inputWhenBring).sendKeys(date);
    }

    public void clickInputRentalPeriod() {
        webDriver.findElement(inputRentalPeriod).click();
    }

    public void clickButtonNext() {
        webDriver.findElement(buttonNext).click();
    }

    public void clickButtonOrder() {
        webDriver.findElement(buttonOrder).click();
    }

    public void clickButtonConfirmationOrder() {
        webDriver.findElement(buttonConfirmationOrder).click();
    }

    public void selectMetroStationList(String metroStation) {
        int p = 0;
        List<WebElement> station = webDriver.findElements(metroStationList);

        for (int i=0; i < station.size(); i++ ) {
            if (station.get(i).getText().equals(metroStation)){
                p=i;
                break;
            }
        }
        ExpectedConditions.elementToBeClickable(station.get(p));
        station.get(p).click();
    }

    public void selectRentalPeriodList(String rentalPeriod) {
        int p = 0;
        List<WebElement> station = webDriver.findElements(rentalPeriodList);

        for (int i=0; i < station.size(); i++ ) {
            if (station.get(i).getText().equals(rentalPeriod)){
                p=i;
                break;
            }
        }
        ExpectedConditions.elementToBeClickable(station.get(p));
        station.get(p).click();
    }

}
