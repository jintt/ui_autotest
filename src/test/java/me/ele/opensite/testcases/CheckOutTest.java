package me.ele.opensite.testcases;

import me.ele.opensite.pages.Login;
import me.ele.opensite.pages.home.HomePage;
import me.ele.opensite.pages.restaurant.RestaurantDetail;
import me.ele.opensite.util.Locator;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import me.ele.opensite.pages.checkout.*;

import java.util.Map;

/**
 * Created by jintingting on 2017/7/14.
 */
public class CheckOutTest extends TestBase {

    //private WebDriver driver;
    private Locator homePageLocator;
    private Locator restaurantDetailLocator;
    private Locator loginLocator;
    private Locator checkOutLocator;

    public CheckOutTest() {
        super();

    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        homePageLocator = new Locator(driver, "HomePage");
        restaurantDetailLocator = new Locator(driver, "RestaurantDetail");
        loginLocator = new Locator(driver, "Login");
        checkOutLocator = new Locator(driver, "CheckOut");

    }


    @Test(dataProvider = "providerMethod")
    public void testCheckOut(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));

        HomePage homePage = new HomePage(homePageLocator);
        homePage.testPoi(param);
        homePage.testSearch(param);

        RestaurantDetail restaurantDetail = new RestaurantDetail(restaurantDetailLocator);
        restaurantDetail.addFood(param);
        restaurantDetail.checkout(param);

        Login login = new Login(loginLocator);
        login.loginWithPwd(param);

        CheckOut checkOut = new CheckOut(checkOutLocator);
        checkOut.submitOrder();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
