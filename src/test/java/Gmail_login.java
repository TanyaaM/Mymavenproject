import org.testng.annotations.Test;

import com.Base.BaseTest;

public class Gmail_login extends BaseTest {
		@Test
		public void TestGmailLogin()
		{
			try{
				OpenBrowser();
				navigate("URL");
				Thread.sleep(5000);
				
				type("Login_Email_xpath","tanya123mahajan@gmail.com");
				
				click("Login_Next_xpath");
				wait(5);
				type("Login_Password_xpath","");
				click("Login_Next_xpath");
				wait(5);
				click("Login_SignIN_xpath");
				wait(10);
				if(isElementPresent("Login_Compose_xpath"))
				{
					reportPass("Pass");
					
				}
				else
				{
					reportFail("Fail");
				}
				
				
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
			
		}
		

	}


