package me.ele.opensite.util;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * Created by jintingting on 2017/11/15.
 */
public class ScreenShot {

    public WebDriver driver;

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    private void takeScreenshot(String screenPath) {
        try {
            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenPath));
        } catch (IOException e) {
            System.out.println("Screen shot error: " + screenPath);
        }
    }

    public void takeScreenshot() {
        String screenName = String.valueOf(new Date().getTime()) + ".jpg";
        File dir = new File("pages-output/snapshot");
        if (!dir.exists())
            dir.mkdirs();
        String screenPath = dir.getAbsolutePath() + "/" + screenName;
        this.takeScreenshot(screenPath);
    }


}
