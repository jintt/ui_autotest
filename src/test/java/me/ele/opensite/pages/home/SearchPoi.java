package me.ele.opensite.pages.home;

import me.ele.opensite.util.Locator;
import me.ele.opensite.util.Log;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2017/5/18.
 */
public class SearchPoi extends TestBase {

    //private WebDriver driver;

    private Locator locator;
    private Locator SearchRestaurant;

    public SearchPoi() {
        super();

    }

    public SearchPoi(Locator locator) {
        super();
        this.locator = locator;
        this.driver = locator.getDriver();

    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        locator = new Locator(driver, this.getClass().getSimpleName());

        SearchRestaurant = new Locator(driver, "SearchRestaurant");

        System.out.println("testPoi");
    }


    public void testPoi(Map<String, String> param) throws Exception {
//        driver.navigate().to(param.get("baseurl"));
//        driver.navigate().to(param.get("url"));

        locator.getElement("poi_button").click();

        sleep(2000);

		locator.getElement("search").sendKeys(param.get("keywords"));
        sleep(2000);
        locator.getElement("search").sendKeys(Keys.ENTER);
        sleep(2000);

        try {
            takeScreenShoot("定位：" + param.get("keywords") );
        } catch (Exception e) {
            e.printStackTrace();
        }


        locator.getElement("address").click();

        sleep(2000);

        assert locator.getElement("poi").getText().equals(param.get("keywords"));

        Log.logInfo("定位地址：" + param.get("keywords"));

        takeScreenShoot("定位" + param.get("keywords"));

    }

    @AfterMethod
    public void tearDown() {
        System.out.println("tear down...");
        driver.close();
        driver.quit();
    }


}
