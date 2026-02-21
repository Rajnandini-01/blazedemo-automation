package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    // TC01 - Verify Home Page Loads
    @Test
    public void verifyHomePageLoads() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("blazedemo"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("blazedemo"), "Home page did not load properly");
    }

    // TC02 - Search Flight Boston → New York
    @Test
    public void searchFlight() {

        HomePage homePage = new HomePage(driver);

        homePage.selectDepartureCity("Boston");
        homePage.selectDestinationCity("New York");
        homePage.clickFindFlights();

        // Wait until Reserve page loads
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("reserve.php"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("reserve.php"), "Did not navigate to Reserve page");
    }
}