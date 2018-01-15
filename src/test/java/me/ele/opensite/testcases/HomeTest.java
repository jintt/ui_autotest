package me.ele.opensite.testcases;

import me.ele.opensite.pages.Login;
import me.ele.opensite.pages.checkout.CheckOut;
import me.ele.opensite.pages.home.HomePage;
import me.ele.opensite.pages.home.SearchPoi;
import me.ele.opensite.pages.home.SearchRestaurant;
import me.ele.opensite.pages.restaurant.RestaurantDetail;
import me.ele.opensite.util.Locator;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2018/1/19.
 */
public class HomeTest extends TestBase {

    //private WebDriver driver;
    private Locator SearchPoi;
    private Locator SearchRestaurant;
    private Locator homePageLocator;
    private Locator Login;
    private Locator checkOutLocator;

    public HomeTest() {
        super();

    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        SearchRestaurant = new Locator(driver, "SearchRestaurant");
        SearchPoi = new Locator(driver, "SearchPoi");
        homePageLocator = new Locator(driver, "HomePage");
        Login = new Locator(driver, "Login");
        checkOutLocator = new Locator(driver, "Login");

    }


    @Test(dataProvider = "providerMethod")
    public void testSearchPoi(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));
        sleep(2000);
        SearchPoi searchPoi = new SearchPoi(SearchPoi);
        searchPoi.testPoi(param);

        SearchRestaurant searchRestaurant = new SearchRestaurant(SearchRestaurant);
        searchRestaurant.testSearch(param);

    }

    @Test(dataProvider = "providerMethod")
    public void testFoodEntry(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));
        sleep(2000);
        takeScreenShoot("进入首页");

        HomePage homePage = new HomePage(homePageLocator);
        homePage.foodEntry(param.get("entryname"));

        if (homePageLocator.findElement(By.xpath("//h1[contains(text(),'" + param.get("entryname") + "')]")) != null) {
            Assert.assertTrue(true);
            Assert.assertTrue(
                    driver.findElement(By.xpath("//ul/li[1]")) != null);

        }
        else if (homePageLocator.findElement(By.xpath("//div[contains(text(),'" + param.get("entryname") + "')]")) != null) {
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }

       /* Assert.assertTrue(
                driver.findElement(By.xpath("//h1[contains(text(),'" + param.get("entryname") + "')]")) != null);
        */

    }

    @Test(dataProvider = "providerMethod")
    public void testShopList(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));
        sleep(2000);
        takeScreenShoot("进入首页");
        HomePage homePage = new HomePage(homePageLocator);
        homePage.entryShop("第一家店铺");

        Assert.assertTrue(
                driver.findElement(By.xpath("//dl[@role='menu']/dd[1]")) != null);

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
