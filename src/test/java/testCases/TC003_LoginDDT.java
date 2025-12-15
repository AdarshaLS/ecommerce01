package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is invalid -- login success - test pass - logout
 * Data is invalid -- login failed - test fail
 * 
 * Data is invalid - login success - test fail - logout
 * Data is invalid -- login failed - test pass
 */

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups={"DataDriven","Master"})
	public void verify_loginDDT(String email,String pwd,String expResult)
	{
		logger.info("*** starting TC_003_LoginDDT ***");
		try
		{
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		if(expResult.equalsIgnoreCase("Valid")) 
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
				
			}			
		}
		
		if(expResult.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*** Finished TC_003_LoginDDT ***");
		
		}

	}
	

