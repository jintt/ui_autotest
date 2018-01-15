package me.ele.opensite.pages.restaurant;

import me.ele.opensite.util.*;
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

    private Locator locator;

    public RestaurantDetail() {
        super();

    }

    public RestaurantDetail(Locator locator) {
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


    @Test(dataProvider = "providerMethod")
    public void addFood(Map<String, String> param) throws InterruptedException {

        if(locator.getElement("add_food_first") != null) {
            locator.getElement("add_food_first").click();
        }
        else {
            locator.getElement("add_food").click();
        }
        sleep(2000);
        Log.logInfo("添加食物");

        try {
            takeScreenShoot("加购餐品");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addSpecFood(Map<String, String> param) throws Exception {

        locator.getElement("add_food_spec").click();
        takeScreenShoot("选择多规格餐品");
        locator.getElement("add_food_spec_ok").click();

        sleep(2000);
        Log.logInfo("添加多规格餐品");

        takeScreenShoot("添加多规格餐品");

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
