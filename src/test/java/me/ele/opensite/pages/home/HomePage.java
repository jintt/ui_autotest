package me.ele.opensite.pages.home;

import me.ele.opensite.util.Locator;
import me.ele.opensite.util.Log;
import me.ele.opensite.util.SeleniumDriver;
import me.ele.opensite.util.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by jintingting on 2018/1/30.
 */
public class HomePage extends TestBase {
    private Locator locator;

    public HomePage() {
        super();

    }

    public HomePage(Locator locator) {
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

    /***
     * 首页进入聚合页
     * @param entryName
     * @throws Exception
     */
    public void foodEntry(String entryName) throws Exception {

        locator.getElement(entryName).click();
        sleep(2000);
        takeScreenShoot("进入'" + entryName + "'聚合页");

    }

    /***
     * 进入首页推荐餐厅，shopName需要在locator中定义
     * @param shopName
     * @throws Exception
     */
    public void entryShop(String shopName) throws Exception {
        WebElement element = locator.getElement(shopName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        locator.getElement(shopName).click();
        sleep(2000);
        takeScreenShoot("进入'" + shopName + "'");

    }

    /***
     * 首页根据参数中keywords定位，选择搜索地址中第一条
     * @param param
     * @throws Exception
     */
    public void testPoi(Map<String, String> param) throws Exception {

      locator.getElement("poi_button").click();

        sleep(3000);

        locator.getElement("search").sendKeys(param.get("keywords"));
        sleep(2000);
        locator.getElement("search").sendKeys(Keys.ENTER);
        sleep(2000);

        try {
            takeScreenShoot("搜索定位：" + param.get("keywords") );
        } catch (Exception e) {
            e.printStackTrace();
        }


        locator.getElement("address").click();

        sleep(2000);
        takeScreenShoot("定位：" + param.get("keywords") );

        Log.logInfo("定位地址：" + param.get("keywords"));

    }

    /***
     * 首页搜索餐厅／美食，进入第一家店铺
     * @param param
     * @throws Exception
     */
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



}
