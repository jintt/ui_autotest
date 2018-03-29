package me.ele.opensite.testcases;

import me.ele.opensite.pages.home.HomePage;
import me.ele.opensite.pages.restaurant.RestaurantDetail;
import me.ele.opensite.util.Locator;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by jintingting on 2018/1/19.
 */
public class RestaurantDetailTest extends TestBase {

    //private WebDriver driver;
    private Locator homePageLocator;
    private Locator RestaurantDetail;

    public RestaurantDetailTest() {
        super();

    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        homePageLocator = new Locator(driver, "HomePage");
        RestaurantDetail = new Locator(driver, "RestaurantDetail");


    }


    @Test(dataProvider = "providerMethod")
    public void testAddFood(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));

        HomePage homePage = new HomePage(homePageLocator);
        homePage.testPoi(param);
        homePage.testSearch(param);

        RestaurantDetail restaurantDetail = new RestaurantDetail(RestaurantDetail);
        restaurantDetail.addFood(param);
        restaurantDetail.addSpecFood(param);

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
