//LINEAR APPROACH
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.Base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testlogin extends BaseTest{
	
	@Test
	public void LoginTest()
	{
		try{
			test=rep.startTest("LoginTest");
			test.log(LogStatus.INFO, "Starting the testcase LoginTest");
			
			if(GmailLogin("tanya123mahajan@gmail.com","12345")){
			test.log(LogStatus.INFO, "Gmail login is successfull");
			}else{
				test.log(LogStatus.FAIL, "Gmail login is unsuccessfull");
			}
			
			
		}catch(Exception e){
			reportFail(e.getMessage());
			CaptureScreen();
		}}
	
		public void ComposeTest()
		{
			try{
				test=rep.startTest("ComposeTest");
				test.log(LogStatus.INFO, "Starting the testcase LoginTest");
				
				if(GmailLogin("tanya123mahajan@gmail.com","12345")){
				test.log(LogStatus.INFO, "Gmail login is successfull");
				}else{
					test.log(LogStatus.FAIL, "Gmail login is unsuccessfull");
				}
				//write down compose functionality
				
			}catch(Exception e){
				reportFail(e.getMessage());
				CaptureScreen();
			}
		
	}
	@AfterMethod 
	public void aftMethod(){
		
		rep.endTest(test);
		rep.flush();
		//driver.close();
		
		
	}


}

