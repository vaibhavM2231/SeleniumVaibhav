package com.assignment.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.assignment.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class ReportUtils extends TestBase implements ITestListener{

	static String userDir = System.getProperty("user.dir");
	ExtentReports extentReport ;
	public static ExtentTest logger;
	public static String parentFolder=userDir+"\\CurrentTestResults\\";
	public static String scrReport=userDir+"\\CurrentTestResults\\"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date());
	public static String destReport=userDir+"\\ArchivedTestResults";

	public void onStart(ITestContext context) {

		moveOldResults();

		extentReport = new ExtentReports(scrReport+ "//reports.html");

	}

	public void onTestStart(ITestResult result) {
		logger = extentReport.startTest(result.getMethod().getMethodName());
		logger.log(LogStatus.INFO, result.getMethod().getMethodName() + "Test is started");

	}
	public void onTestSkipped(ITestResult result) {

		System.out.println("on test skipped");
		logger.log(LogStatus.SKIP, result.getMethod().getMethodName() + "Test	 is skipped");
	}

	public void onFinish(ITestContext context) {
		extentReport.endTest(logger);
		extentReport.flush();
	}

	public void onTestSuccess(ITestResult result) {
		logger.log(LogStatus.PASS, result.getMethod().getMethodName() + "Test Case is passed");
	}

	public void onTestFailure(ITestResult result) {

		logger.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
		TestBase.takeScreenshot(scrReport+"//"+result.getMethod().getMethodName() + ".png");
		TestBase.file = logger.addScreenCapture(scrReport+"//"+result.getMethod().getMethodName() + ".png");
		logger.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", TestBase.file);

	}


	public static void moveOldResults() {
		String [] list =new File(parentFolder).list();

		for(int i=0;i<list.length;i++) {

			try {
				FileUtils.moveDirectory(new File(parentFolder+"\\"+list[i]), new File(destReport+"\\"+list[i]));
			} catch (FileExistsException e) {

				try {
					FileUtils.moveDirectory(new File(parentFolder+"\\"+list[i]), new File(destReport+"\\"+list[i]+"_1"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
