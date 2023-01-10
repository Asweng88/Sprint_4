package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {

    private WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver=webDriver;
    }

    // массив вопросов
    private static By dropDownQuestion = By.xpath("//*[@role=\"button\"]");
    // массив ответов
    private static By dropDownAnswer = By.xpath("//*[@role=\"region\"]/p");

    // верхняя кнопка заказать самокат
    private static By upperButtonOrder = By.xpath("//button[@class=\"Button_Button__ra12g\"]");
    // нижняя кнопка заказать самокат
    private static By lowerButtonOrder = By.xpath("//*[@class=\"Home_ThirdPart__LSTEE\"]/*/*/button");


    public static By getDropDownQuestion() {
        return dropDownQuestion;
    }

    public static By getDropDownAnswer() {
        return dropDownAnswer;
    }

    public MainPage open() {
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    public By getButtonOrder(String buttonName){
        switch (buttonName){
            case "upperButtonOrder":
                return upperButtonOrder;
            default:
                return lowerButtonOrder;
        }
    }

    public MainPage scrollButtonOrder(String buttonName){
        WebElement buttons;
        switch (buttonName){
            case "upperButtonOrder":
                buttons = webDriver.findElement(upperButtonOrder);
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",buttons);
                ExpectedConditions.elementToBeClickable(buttons);
                return this;
            default:
                buttons = webDriver.findElement(lowerButtonOrder);
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",buttons);
                ExpectedConditions.elementToBeClickable(buttons);
                return this;
        }
    }

    public void clickButtonOrder(String buttonName){
        switch (buttonName){
            case "upperButtonOrder":
               webDriver.findElement(upperButtonOrder).click();
               break;
            default:
                webDriver.findElement(lowerButtonOrder).click();
        }
    }

}
