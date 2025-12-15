package testCases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	WebDriver driver;
	
	
	
	@Test(groups={"Regression","Master"})
	public void cerify_account_registration() 
	{
		//logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicked on MyAccount Link");
		
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
		
		//logger.info("Providing customer details..");
		regPage.setFirstName(randomeString().toUpperCase());
		regPage.setLastName(randomeString().toUpperCase());
		regPage.setEmail(randomeString()+"@gmail.com"); //randomly generated Email id
		regPage.setTelephone("23123123");
		
		String password = randomeAlphaNumeric();
		
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		//logger.info("Validating expected message..");
		String confmsg = regPage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test failed..");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		Assert.assertEquals(confmsg, "Your Account Has Been Created");
		}
		
		catch(Exception e) {			
			Assert.fail();
		}
		
		logger.info("**** Finishing TC001_AccountRegistrationTest ****");
		
	}
	
	

}
