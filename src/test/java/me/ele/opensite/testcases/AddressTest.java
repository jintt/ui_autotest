package me.ele.opensite.testcases;

import me.ele.opensite.pages.home.SearchPoi;
import me.ele.opensite.pages.home.SearchRestaurant;
import me.ele.opensite.pages.mypage.MyPage;
import me.ele.opensite.util.Locator;
import me.ele.opensite.util.Log;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2018/1/31.
 */
public class AddressTest extends TestBase {
    private Locator myPageLocator;

    private MyPage myPage;

    public AddressTest() {
        super();

    }

    @BeforeClass
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();
        myPageLocator = new Locator(driver, "MyPage");
    }

    @Test(dataProvider = "providerMethod")
    public void testAddAddress(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));

        myPage = new MyPage(myPageLocator);

        myPage.elementClick("我的");           //进入我的页面
        myPage.login(param);                         //登陆

        myPage.elementClick("我的地址");
        myPage.elementClick("新增收货地址");

        myPage.addAddress(param);

        Assert.assertTrue(
                driver.findElement(By.xpath("//span[@class='username' and contains(text(),'" + param.get("name") + "')]")) != null);

    }

    @Test(dataProvider = "providerMethod")
    public void testModifyAddress(Map<String, String> param) throws Exception {

        //takeScreenShoot("testModifyAddress");

        myPage.elementClick("第一个地址");
        myPage.elementClick("公司");
        myPage.elementClick("确定");
        sleep(3000);

    }

    @Test(dataProvider = "providerMethod")
    public void testDeleteAddress(Map<String, String> param) throws Exception {
        takeScreenShoot("testModifyAddress");
        //MyPage myPage = new MyPage(myPageLocator);
        myPage.elementClick("第一个地址");
        myPage.elementClick("删除");

    }


    @org.testng.annotations.AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
