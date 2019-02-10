//provides data to our testcase using hashtables
package com.Base;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class ExcelUtiltemp extends BaseTest{
	
	@Test(dataProvider="getDataTestC")
	public void testC(Hashtable<String,String> data)
	{
		test=rep.startTest("TestC");
		testCaseName="TestC";
		test.log(LogStatus.INFO, "Starting the testcaseC");
		System.out.println(data.get("Runmode")+"------"+data.get("col1")+"------"+data.get("col2"));
		if(!ExcelUtil.isRunnable("TestC",xls)||data.get("Runmode").equals("N")){
			
			Result_to_Xls(xls,"TestData",data.get("RowNum"),"Skip");
			test.log(LogStatus.SKIP, "Skipping the testcases as runmode is N");
			throw new SkipException("Skipping the testcases");
		}
		OpenBrowser();
		System.out.println("TestA has run");
		Result_to_Xls(xls,"TestData",data.get("RowNum"),"Pass");
	}
	
	@Test(dataProvider="getDataTestA")
	public void testA(Hashtable<String,String> data)
	{
		test=rep.startTest("TestA");
		test.log(LogStatus.INFO, "Starting the testcaseA");
		System.out.println(data.get("Runmode")+"-----"+data.get("username")+"-----"+data.get("password"));
		if(data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
	}

	@DataProvider
	public Object[][] getDataTestC() {
		
		Xlsx_Reader xls=new Xlsx_Reader("G:\\Java\\TestCaseData.xlsx");
		return ExcelUtil.getTestData(xls,"TestC");
	}
	@DataProvider
	public Object[][] getDataTestA() {
		
		return ExcelUtil.getTestData(xls,"TestA");
		
	}
	@AfterMethod
	public void Aftermethod()
	{
		rep.endTest(test);
		rep.flush();
		if(driver!=null){
			driver.quit();
		}
	}
	
	
}