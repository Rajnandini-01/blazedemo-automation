package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReservePage {

    WebDriver driver;

    // Locator - First "Choose This Flight" button
    By chooseFlightButton = By.xpath("(//input[@value='Choose This Flight'])[1]");

    // Constructor
    public ReservePage(WebDriver driver) {
        this.driver = driver;
    }

    // Action
    public void chooseFirstFlight() {
        driver.findElement(chooseFlightButton).click();
    }
}