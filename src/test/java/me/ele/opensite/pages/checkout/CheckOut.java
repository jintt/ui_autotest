package me.ele.opensite.pages.checkout;

import me.ele.opensite.pages.home.SearchPoi;
import me.ele.opensite.pages.home.SearchRestaurant;
import me.ele.opensite.pages.restaurant.RestaurantDetail;
import me.ele.opensite.util.ExtentReportListener;
import me.ele.opensite.util.Locator;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import me.ele.opensite.pages.Login;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2017/7/14.
 */
public class CheckOut extends TestBase {

    //private WebDriver driver;
    private Locator SearchPoi;
    private Locator SearchRestaurant;
    private Locator RestaurantDetail;
    private Locator Login;

    private Locator locator;

    public CheckOut() {
        super();

    }

    public CheckOut(Locator locator) {
        super();
        this.locator = locator;
        this.driver = locator.getDriver();
    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        SearchRestaurant = new Locator(driver, "SearchRestaurant");
        SearchPoi = new Locator(driver, "SearchPoi");
        RestaurantDetail = new Locator(driver, "RestaurantDetail");
        Login = new Locator(driver, "Login");

    }


    public void submitOrder() throws Exception {
        locator.getElement("submitbtn").click();
        sleep(2000);
        takeScreenShoot("下单提交");
        Assert.assertTrue(locator.getElement("paybtn").isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
