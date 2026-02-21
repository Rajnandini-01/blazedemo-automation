package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://blazedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureScreenshot(driver, result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}