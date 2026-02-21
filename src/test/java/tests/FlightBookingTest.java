package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.ReservePage;
import pages.PurchasePage;
import pages.ConfirmationPage;

public class FlightBookingTest extends BaseTest {

    // DataProvider - Multiple user data
    @DataProvider(name = "bookingData")
    public Object[][] getData() {
        return new Object[][] {
                {"Test User1", "Street 1", "Mumbai", "MH", "400001", "4111111111111111"},
                {"Test User2", "Street 2", "Delhi", "DL", "110001", "4222222222222"},
                {"Test User3", "Street 3", "Pune", "MH", "411001", "4333333333333"}
        };
    }

    // Data-driven test
    @Test(dataProvider = "bookingData")
    public void completeFlightBooking(String name, String address, String city,
                                      String state, String zip, String card) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Open Home Page for each iteration
        driver.get("https://blazedemo.com");

        // Home Page
        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("New York");
        home.clickFindFlights();

        wait.until(ExpectedConditions.urlContains("reserve"));

        // Reserve Page
        ReservePage reserve = new ReservePage(driver);
        reserve.chooseFirstFlight();

        wait.until(ExpectedConditions.urlContains("purchase"));

        // Purchase Page
        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails(name, address, city, state, zip, card);
        purchase.clickPurchaseFlight();

        wait.until(ExpectedConditions.urlContains("confirmation"));

        // Confirmation
        ConfirmationPage confirm = new ConfirmationPage(driver);
        String message = confirm.getConfirmationText();

        Assert.assertTrue(message.contains("Thank you"), "Booking failed");
        
        
    }
    @Test
    public void bookingWithBlankCreditCard() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://blazedemo.com");

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("New York");
        home.clickFindFlights();

        wait.until(ExpectedConditions.urlContains("reserve"));

        ReservePage reserve = new ReservePage(driver);
        reserve.chooseFirstFlight();

        wait.until(ExpectedConditions.urlContains("purchase"));

        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails(
                "Negative User",
                "Test Address",
                "Mumbai",
                "MH",
                "400001",
                ""   // Blank Credit Card
        );
        purchase.clickPurchaseFlight();

        // Validate confirmation still appears (BlazeDemo allows it)
        wait.until(ExpectedConditions.urlContains("confirmation"));

        ConfirmationPage confirm = new ConfirmationPage(driver);
        String message = confirm.getConfirmationText();

        Assert.assertTrue(message.contains("Thank you"));
    }
    @Test
    public void bookingWithInvalidCreditCardCharacters() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://blazedemo.com");

        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("New York");
        home.clickFindFlights();

        wait.until(ExpectedConditions.urlContains("reserve"));

        ReservePage reserve = new ReservePage(driver);
        reserve.chooseFirstFlight();

        wait.until(ExpectedConditions.urlContains("purchase"));

        PurchasePage purchase = new PurchasePage(driver);
        purchase.enterPassengerDetails(
                "Invalid Card User",
                "Street",
                "Delhi",
                "DL",
                "110001",
                "ABCDEF"   // Invalid characters
        );
        purchase.clickPurchaseFlight();

        wait.until(ExpectedConditions.urlContains("confirmation"));

        ConfirmationPage confirm = new ConfirmationPage(driver);
        String message = confirm.getConfirmationText();

        Assert.assertTrue(message.contains("Thank you"));
    }
}
