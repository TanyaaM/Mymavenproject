package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.util.ExtentManager;

public class BaseTest {
	
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;

	//Xlsx_Reader xls=new Xlsx_Reader("G:\\Java\\TestCaseData.xlsx");
	
	protected static WebDriver driver;
	
	public static int colSRowNum; //from where our testcase starts
	
	public String testCaseName; //we set this value in every testcase
	
	public static Xlsx_Reader xls; //we set this value in every class
	
	public static String GetPropValue(String PName)
	{
	String PValue=null;
	try{
		Properties prop=new Properties();
		String path=System.getProperty("user.dir");
		FileInputStream ft=new FileInputStream("C:\\Users\\sony\\maven\\MyMavenProject\\src\\test\\java\\Config.properties");
		prop.load(ft);//LINKING TO THE PROPERTY FILE
		PValue=prop.getProperty(PName);
	}catch(Exception e){
		System.out.println("some error"+e.getMessage());
	}
	return PValue;
	}

	public static String GetPathValue(String PName)
	{
	String PValue=null;
	try{
		Properties prop=new Properties();
		String path=System.getProperty("user.dir");
		FileInputStream ft=new FileInputStream("C:\\Users\\sony\\maven\\MyMavenProject\\src\\test\\java\\Object.properties");
		prop.load(ft);//LINKING TO THE PROPERTY FILE
		PValue=prop.getProperty(PName);
	}catch(Exception e){
		System.out.println("some error"+e.getMessage());
	}
	return PValue;
	}


/******************Browsername****************************/
	
	public static String BrowserName(String testName,Xlsx_Reader xls){
		String BName=null;  
		String sheet="TestCases";
		  int rows=xls.getRowCount(sheet);
		  for(int r=2;r<=rows;r++)
		  {
			  String tName=xls.getCellData(sheet, "TCID", r);
			  if(tName.equals(testName))
			  {
				  String Runmode=xls.getCellData(sheet, "Runmode", r);
				  if(Runmode.equals("Y"))
				  {
					  BName=xls.getCellData(sheet, "BrowserName", r);
				  }
					  
			  }
				  
		  }
		return BName;
	}

	
	/***********************OPEN BROWSER******************************
	 * author:
	 * @param btype
	 */
	public void OpenBrowser(){
		String btype= BrowserName(testCaseName,xls);
		
		if(btype.equals("Mozilla")){
		
			System.setProperty("webdriver.mozilla.driver","G:\\Selenium\\mozilladriver.exe");

			 driver=new FirefoxDriver();
			
		}else if(btype.equals("Chrome")){
		 
			System.setProperty("webdriver.chrome.driver","G:\\Selenium\\chromedriver.exe");

			driver=new ChromeDriver();
			
		}else if(btype.equals("IE")){
			 System.setProperty("webdriver.IE.driver","G:\\Selenium\\IEdriver.exe");

			driver=new InternetExplorerDriver();
	}
		
	}
/*----------------------------------------------------------------------------	*/

	/**********************NAVIGATE FUNCTION****************************/
	
	
	public static void navigate(String URL){
		driver.get(GetPropValue("URL"));
	}
	/*-------------------------------*/

/*************************close browser**********************/
	public static void closeBrowser(){
	 driver.close();
	}
	
/**************quit browser************************/
	
	public static void quitBrowser(){
		driver.quit();
	}

/****************************Reporting(capture screen)******************************************/

public void CaptureScreen()
{
 Date d=new Date();
 String filename=d.toString().replace(":","_").replace(" ","_")+".jpg";//creating filename
 
 String Path = System.getProperty("user.dir")+"\\ScreenShots\\"+filename;
 //System.out.println(Path);

 TakesScreenshot oScn = (TakesScreenshot) driver;
 File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
 File oDest = new File(Path);
 try {
     FileUtils.copyFile(oScnShot, oDest);
     } catch (IOException e) {System.out.println(e.getMessage());}
  
  test.log(LogStatus.INFO, "ScreenShot->"+ test.addScreenCapture(Path));//syntax for attaching screenshot with your report
}
/*********************Click ON OBJECT******************/

public void click(String xpathEle){
	//driver.findElement(By.xpath(GetPathValue(xpathEle))).click();
	getElement(xpathEle).click();
}
/*---------------------------------------------------------------*/

/*****************Typing on Object***************************/

public void type(String xpathEle,String data){
	//driver.findElement(By.xpath(GetPathValue(xpathEle))).sendKeys(data);
	getElement(xpathEle).sendKeys(data);
}
/*-----------------------------------------------------*/

/**
 * @throws InterruptedException *********wait******************************************************/
/*public void wait(int x) throws InterruptedException{
	int y=x+1000;
	
	Thread.sleep(y);
}*/

/***********************************************************************************/



public boolean isElementPresent(String LocatorKey){
	List<WebElement> elementList=null;
	if(LocatorKey.endsWith("_id"))
		elementList=driver.findElements(By.id(GetPathValue(LocatorKey)));
	else if(LocatorKey.endsWith("_name"))
		elementList=driver.findElements(By.name(GetPathValue(LocatorKey)));
	else if(LocatorKey.endsWith("_xpath"))
		elementList=driver.findElements(By.xpath(GetPathValue(LocatorKey)));
	else{
		Assert.fail("Locator not correct"+ LocatorKey);
	}
	 if(elementList.size()==0)
		 return false;
	 else
		 return true;
	
}



/******************Reporting*****************************/

public void reportPass(String Msg){
	test.log(LogStatus.PASS,Msg);
	
}
public void reportFail(String Msg){
	test.log(LogStatus.FAIL,Msg);
	CaptureScreen();
	Assert.fail(Msg);

}

public WebElement getElement(String LocatorKey){
    WebElement e=null;
	try{
		if(LocatorKey.endsWith("_id"))
		    e=	driver.findElement(By.id(GetPathValue(LocatorKey)));
		else if(LocatorKey.endsWith("_name"))
    		e=	driver.findElement(By.name(GetPathValue(LocatorKey)));
		else if(LocatorKey.endsWith("_xpath"))
			e=	driver.findElement(By.xpath(GetPathValue(LocatorKey)));
		else if(LocatorKey.endsWith("_css"))
			e=	driver.findElement(By.cssSelector(GetPathValue(LocatorKey)));
		else{
			reportFail("Locator not Correct -"+LocatorKey);
		}
			
	}catch(Exception ex){
		reportFail(ex.getMessage());
		ex.printStackTrace();
	}
    return e;
}
/**************************Validation Function*************************/
public void verifyTitle(String et){
try
{
String A_title=driver.getTitle();
String E_Title=GetPropValue(et);
//Assert.assertEquals(E_title, A_title);
if(A_title.equals(E_Title))
{
System.out.println("Title testcase pass");

}
else{
System.out.println("Title is not the same");
}

}catch(Exception e){
Assert.fail("Title is not the same");
}

}
public boolean verifyText(){
return false;
}

public void waitForPageToLoad(){
JavascriptExecutor js=(JavascriptExecutor)driver;
String state=(String)js.executeScript("return document.readyState");
while(!state.equals("complete")){
wait(2);
state=(String)js.executeScript("return document.readyState");

}
}
/************************** wait function**************************/
public void wait(int timeToWait){

try {
Thread.sleep(timeToWait*1000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

}
public void clickAndWait(String Locator,String Present){

int count=5;
for(int i=0;i<count;i++){
click(Locator);
wait(2);
if(isElementPresent(Present))
break;
}

}
/***************set result to excel********************/

public static void Result_to_Xls(Xlsx_Reader xls,String SheetName,String RowNum,String Result)
{
	int rNum=Integer.parseInt(RowNum);
	xls.setCellData("TestData", "Result", rNum,Result,colSRowNum);
}



/*-----------------------------------------------------------App function---------------------------*/

public boolean login_salesforce(String Uname,String Passwd){
try
{
wait(5);	
getElement("SalesForce_UserName_xpath").sendKeys(Uname);
wait(2);
getElement("SalesForce_Passwd_xpath").sendKeys(Passwd);
wait(2);
getElement("SalesForce_LogIn_xpath").click();
wait(10);
if(isElementPresent("SalesForce_LogIn_TourText"))
{
return true;
}
}catch(Exception e){
return false;
}
return false;
}



/***********************APPLICATION FUNCTION
 * @throws InterruptedException **********************************/

public boolean GmailLogin(String Email,String Password) throws InterruptedException
{
	OpenBrowser();
	test.log(LogStatus.INFO, "Browser opened");
	navigate("URL");
	test.log(LogStatus.INFO, "Application launched");
	type("Login_Email_xpath","tanya123mahajan@gmail.com");
	test.log(LogStatus.INFO, "Entered the emailid");
	click("Login_Next_xpath");
	test.log(LogStatus.INFO, "Click Next");
	Thread.sleep(5000);
	type("Login_Password_xpath","m@gicworld");
	test.log(LogStatus.INFO, "Entered the password");
	click("Login_SignIn_xpath");
	test.log(LogStatus.INFO,"signin");
	Thread.sleep(10000);
	if(isElementPresent("Login_Compose_xpath"))
	{
		return true;
	}
		return false;
		
}}