package com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.Base.BaseTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.util.ExtentManager;

public class TestcaseA extends BaseTest{

	

	@Test
	public void TestA()
	{
		test=rep.startTest("TestCaseA");
		test.log(LogStatus.INFO, "Starting the testcase");
		OpenBrowser();
		test.log(LogStatus.INFO,"Browser has opened");
		navigate("URL");
		CaptureScreen();

	}
	@AfterMethod
	public void Aftermethod()
	{
		rep.endTest(test);
		rep.flush();
	}
	@Test
	public void TestB()
	{
		test=rep.startTest("TestCaseB");
		test.log(LogStatus.INFO, "Starting the testcase");
		OpenBrowser();
		test.log(LogStatus.INFO,"Browser has opened");
		test.log(LogStatus.PASS,"Test Case has passed");
		navigate("URL");

	}
	@Test
	public void TestC()
	{
		test=rep.startTest("TestCaseC");
		test.log(LogStatus.INFO, "Starting the testcase");
		OpenBrowser();
		test.log(LogStatus.INFO,"Browser has opened");
		test.log(LogStatus.FAIL,"FAIL");
		navigate("URL");

	}@Test
	public void TestD()
	{
		test=rep.startTest("TestCaseD");
		test.log(LogStatus.INFO, "Starting the testcase");
		//OpenBrowser("Browser_Name");
		test.log(LogStatus.INFO,"Browser has opened");
		test.log(LogStatus.SKIP,"Test case skipped");
		navigate("URL");

	}
}
