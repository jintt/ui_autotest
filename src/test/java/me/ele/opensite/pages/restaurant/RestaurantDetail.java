package me.ele.opensite.pages.restaurant;

import me.ele.opensite.util.Locator;
import me.ele.opensite.util.Log;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2017/11/1.
 */
public class RestaurantDetail extends TestBase{
    private WebDriver driver;

    private Locator locator;

    public RestaurantDetail() {
        super();

    }

    public RestaurantDetail(Locator locator) {
        super();
        this.locator = locator;
    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        locator = new Locator(driver, this.getClass().getSimpleName());

    }


    @Test(dataProvider = "providerMethod")
    public void addFood(Map<String, String> param) throws InterruptedException {

        locator.getElement("add_food").click();
        sleep(2000);
        Log.logInfo("添加食物");
        try {
            takeScreenShoot("加购餐品");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkout(Map<String, String> param) throws InterruptedException {

        locator.getElement("checkout_button").click();
        sleep(2000);
        Log.logInfo("去下单");
        try {
            takeScreenShoot("去下单");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
