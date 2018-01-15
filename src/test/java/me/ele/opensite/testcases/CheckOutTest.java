package me.ele.opensite.testcases;

import me.ele.opensite.pages.Login;
import me.ele.opensite.pages.home.SearchPoi;
import me.ele.opensite.pages.home.SearchRestaurant;
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
    private Locator SearchPoi;
    private Locator SearchRestaurant;
    private Locator RestaurantDetail;
    private Locator Login;
    private Locator checkOutLocator;

    public CheckOutTest() {
        super();

    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        SearchRestaurant = new Locator(driver, "SearchRestaurant");
        SearchPoi = new Locator(driver, "SearchPoi");
        RestaurantDetail = new Locator(driver, "RestaurantDetail");
        Login = new Locator(driver, "Login");
        checkOutLocator = new Locator(driver, "CheckOut");

    }


    @Test(dataProvider = "providerMethod")
    public void testCheckOut(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));

        SearchPoi searchPoi = new SearchPoi(SearchPoi);
        searchPoi.testPoi(param);

        SearchRestaurant searchRestaurant = new SearchRestaurant(SearchRestaurant);
        searchRestaurant.testSearch(param);

        RestaurantDetail restaurantDetail = new RestaurantDetail(RestaurantDetail);
        restaurantDetail.addFood(param);
        restaurantDetail.checkout(param);

        Login login = new Login(Login);
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
