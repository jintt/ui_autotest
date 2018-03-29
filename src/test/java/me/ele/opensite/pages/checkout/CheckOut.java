package me.ele.opensite.pages.checkout;

import me.ele.opensite.util.Locator;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2017/7/14.
 */
public class CheckOut extends TestBase {


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
