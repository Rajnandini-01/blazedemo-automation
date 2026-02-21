package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {

    WebDriver driver;

    // Locators
    By name = By.id("inputName");
    By address = By.id("address");
    By city = By.id("city");
    By state = By.id("state");
    By zipCode = By.id("zipCode");
    By creditCardNumber = By.id("creditCardNumber");
    By purchaseButton = By.xpath("//input[@value='Purchase Flight']");

    // Constructor
    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterPassengerDetails(String passengerName, String addr, String cityName,
                                      String stateName, String zip, String cardNumber) {

        driver.findElement(name).sendKeys(passengerName);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(city).sendKeys(cityName);
        driver.findElement(state).sendKeys(stateName);
        driver.findElement(zipCode).sendKeys(zip);
        driver.findElement(creditCardNumber).sendKeys(cardNumber);
    }

    public void clickPurchaseFlight() {
        driver.findElement(purchaseButton).click();
    }
}