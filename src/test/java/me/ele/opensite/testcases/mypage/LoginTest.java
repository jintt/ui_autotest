package me.ele.opensite.testcases.mypage;

import me.ele.opensite.pages.Login;
import me.ele.opensite.pages.home.HomePage;
import me.ele.opensite.pages.mypage.MyPage;
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
 * Created by jintingting on 2018/3/28.
 */
public class LoginTest extends TestBase {

    private Locator myPageLocator;
    private MyPage myPage;

    private Locator loginLocator;

    public LoginTest() {
        super();

    }

    @BeforeMethod
    public void setUp() {
        SeleniumDriver sd = new SeleniumDriver();
        driver = sd.getDriver();

        myPageLocator = new Locator(driver, "MyPage");
        myPage = new MyPage(myPageLocator);

        loginLocator = new Locator(driver, "Login");

    }


    @Test(dataProvider = "providerMethod")
    public void testPasswordLogin(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));
        myPage.elementClick("我的");
        myPage.login(param);

        Assert.assertTrue(myPageLocator.getElement("登陆") == null);
        Assert.assertTrue(loginLocator.getElement("login_button") == null);

    }

    @Test(dataProvider = "providerMethod")
    public void testWrongPasswordLogin(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));
        myPage.elementClick("我的");
        myPage.login(param);

        //密码错误，仍停留在登陆页面
        Assert.assertTrue(loginLocator.getElement("login_button") != null);

    }

    @Test(dataProvider = "providerMethod")
    public void testShowPassword(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));
        myPage.elementClick("我的");
        myPage.elementClick("登陆");

        loginLocator.getElement("loginpwd").click();
        loginLocator.getElement("loginpwd_switch").click();

        //点击密码可见后，控件中...展示成abc
        Assert.assertTrue(
                driver.findElement(By.xpath("//div[contains(text(),'abc')]")) != null);


    }

    @Test(dataProvider = "providerMethod")
    public void testLogout(Map<String, String> param) throws Exception {
        driver.navigate().to(param.get("url"));
        myPage.elementClick("我的");
        myPage.login(param);

        myPage.logout(param);

        //登出后，到我的页面，展示登陆／注册按钮
        Assert.assertTrue(myPageLocator.getElement("登陆") != null);

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
