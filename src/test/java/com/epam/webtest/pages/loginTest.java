package com.epam.webtest.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class loginTest extends TestBase {

	private final String username = "testmail1414";
	private final String password = "1414testmail";

	LoginPage loginPage;

	@Parameters({ "path" })
	@BeforeClass
	public void testInit(String path) {

		// Load the page in the browser
		driver.get(baseUrl + path);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
	}
	@Test
	public void testLogin() throws InterruptedException {
		loginPage.setEmail(username);
		loginPage.setPassword(password);
		loginPage.clickSubmitButton();
		Assert.assertTrue(driver.getTitle().contains("¬ход€щие"));
		
	}
}
