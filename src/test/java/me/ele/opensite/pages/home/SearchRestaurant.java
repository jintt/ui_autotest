package me.ele.opensite.pages.home;

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
 * Created by jintingting on 2017/7/14.
 */
public class SearchRestaurant  extends TestBase {

    private Locator locator;

    public SearchRestaurant() {
        super();

    }

    public SearchRestaurant(Locator locator) {
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
    public void testSearch(Map<String, String> param) throws Exception {

        locator.getElement("search-wrapper").click();

        locator.getElement("search-section").sendKeys(param.get("shopName"));

        locator.getElement("search-button").click();
        sleep(2000);
        takeScreenShoot("搜索店铺：" + param.get("shopName"));

        locator.getElement("shopitem").click();
        Log.logInfo("搜索进入店铺：" + param.get("shopName"));

        sleep(2000);

        takeScreenShoot("进入" + param.get("shopName") + "店铺");

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
