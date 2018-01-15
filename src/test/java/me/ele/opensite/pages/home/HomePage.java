package me.ele.opensite.pages.home;

import me.ele.opensite.util.Locator;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2018/1/30.
 */
public class HomePage extends TestBase {
    private Locator locator;

    public HomePage() {
        super();

    }

    public HomePage(Locator locator) {
        super();
        this.locator = locator;
        this.driver = locator.getDriver();

    }


    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        locator = new Locator(driver, this.getClass().getSimpleName());

    }

    public void foodEntry(String entryName) throws Exception {

        locator.getElement(entryName).click();
        sleep(2000);
        takeScreenShoot("进入'" + entryName + "'聚合页");

    }

    public void entryShop(String shopName) throws Exception {
        WebElement element = locator.getElement(shopName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        locator.getElement(shopName).click();
        sleep(2000);
        takeScreenShoot("进入'" + shopName + "'");

    }


}
