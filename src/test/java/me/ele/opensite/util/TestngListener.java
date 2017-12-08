package me.ele.opensite.util;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by jintingting on 2017/11/17.
 */

public class TestngListener extends TestListenerAdapter {
//   private Logger logger = Logger.getLogger(TestngListener.class);
//    protected ExtentReports extent;
//    protected ExtentTest test;

    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        Log.logInfo("【" + tr.getName() + " Start】");
        //    extent=InitDriverCase.getextent();
        //    test= extent.startTest(tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        Log.logInfo("【" + tr.getName() + " Failure】");
        try {
            takeScreenShot(tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //    test.log(LogStatus.INFO,"TakesScreenshot ",test.addScreenCapture("../img/"+tr.getName()+".png"));
        //    test.log(LogStatus.FAIL, tr.getThrowable());
        //    extent.endTest(test);

    }

    public void takeScreenShot(ITestResult tr) throws Exception {
        TestBase baseTestcase=(TestBase)tr.getInstance();
        baseTestcase.takeScreenShoot(tr.getName());

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        try {
            takeScreenShot(tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.logInfo("【" + tr.getName() + " Skipped】");
        //    test.log(LogStatus.SKIP, "SKIP");
        //    extent.endTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        try {
            takeScreenShot(tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.logInfo("【" + tr.getName() + " Success】");
        //    test.log(LogStatus.PASS, "Pass");
        //    extent.endTest(test);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }
}

