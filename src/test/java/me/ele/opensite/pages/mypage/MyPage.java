package me.ele.opensite.pages.mypage;

import me.ele.opensite.pages.Login;
import me.ele.opensite.util.Locator;
import me.ele.opensite.util.TestBase;
import org.openqa.selenium.By;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2018/1/31.
 */
public class MyPage  extends TestBase {

    private Locator locator;
    private Locator loginLocator;

    Login login;

    public MyPage() {
        super();

    }

    public MyPage(Locator locator) {
        super();
        this.locator = locator;
        this.driver = locator.getDriver();

        loginLocator = new Locator(driver, "Login");
        login = new Login(loginLocator);
    }

    public void elementClick(String name) throws Exception {
        locator.getElement(name).click();
        sleep(2000);
        takeScreenShoot("点击'" + name + "'");

    }

    public void login(Map<String, String> param) throws Exception {
        if(locator.getElement("登陆") != null) {
            locator.getElement("登陆").click();
            takeScreenShoot("进入登陆页");
            login.loginWithPwd(param);
        }
        else {

        }

    }

    public void logout(Map<String, String> param) throws Exception {
        if(locator.getElement("登陆用户") != null) {
            locator.getElement("登陆用户").click();
            sleep(2000);
            takeScreenShoot("登陆用户");
        }
        login.logout(param);
    }

    public void addAddress(Map<String, String> param) throws Exception {
        locator.getElement("姓名").sendKeys(param.get("name") );
        sleep(2000);
        locator.getElement("手机号").sendKeys("15500001111");
        locator.getElement("位置").click();
        locator.getElement("输入位置").sendKeys(param.get("address"));
        locator.getElement("搜索").click();
        sleep(2000);
        locator.getElement("选择地址").click();
        sleep(2000);
        locator.getElement("学校").click();
        takeScreenShoot("增加地址信息页");

        locator.getElement("确定").click();
        sleep(2000);
        takeScreenShoot("地址添加结束");

    }


}
