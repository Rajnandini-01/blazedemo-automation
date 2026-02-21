package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String timeStamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String filePath = System.getProperty("user.dir") 
                + "/screenshots/" + testName + "_" + timeStamp + ".png";

        try {
            FileUtils.copyFile(source, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}