package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    WebDriver driver;

    // Locators
    By departureDropdown = By.name("fromPort");
    By destinationDropdown = By.name("toPort");
    By findFlightsButton = By.xpath("//input[@value='Find Flights']");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void selectDepartureCity(String city) {
        Select select = new Select(driver.findElement(departureDropdown));
        select.selectByVisibleText(city);
    }

    public void selectDestinationCity(String city) {
        Select select = new Select(driver.findElement(destinationDropdown));
        select.selectByVisibleText(city);
    }

    public void clickFindFlights() {
        driver.findElement(findFlightsButton).click();
    }
}