package me.ele.opensite.pages;

import me.ele.opensite.util.Locator;
import me.ele.opensite.util.Log;
import me.ele.opensite.util.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2018/1/15.
 */
public class Login  extends TestBase {
    private Locator locator;

    public Login() {
        super();

    }

    public Login(Locator locator) {
        super();
        this.locator = locator;
        this.driver = locator.getDriver();
    }

    @Test(dataProvider = "providerMethod")
    public void loginWithPwd(Map<String, String> param) throws Exception {

        locator.getElement("loginpwd").click();
        locator.getElement("loginpwd_switch").click();
        locator.getElement("loginpwd_username").click();
        locator.getElement("loginpwd_username").sendKeys(param.get("username"));

        sleep(2000);
        locator.getElement("loginpwd_pwd").sendKeys(param.get("password"));
        Log.logInfo("密码登陆");
        takeScreenShoot("用户名密码输入");

        locator.getElement("login_button").click();
        sleep(2000);
        takeScreenShoot("密码登陆");
    }

    public void logout(Map<String, String> param) throws Exception {

        locator.getElement("退出登陆").click();
        takeScreenShoot("退出登陆");

    }

}
