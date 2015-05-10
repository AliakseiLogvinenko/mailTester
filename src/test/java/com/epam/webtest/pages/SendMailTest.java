package com.epam.webtest.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SendMailTest extends TestBase {

	private final String username = "testmail1414";
	private final String password = "1414testmail";
	private final String emailAddress = "G_I_Z_M_O@inbox.ru";
	private final String emailSubject = "MytestSubject";
	private final String emailBody = "The quick brown fox jumps over the lazy dog";

	InboxPage inboxPage;
	LoginPage loginPage;

	@Parameters({ "path" })
	@BeforeClass
	public void testInit(String path) {

		// Load the page in the browser
		driver.get(baseUrl + path);
		loginPage = PageFactory.initElements(driver, LoginPage.class);

	}

	@Test
	public void sendingMailTest() throws InterruptedException {
		//starting log-in sequence
		loginPage.setEmail(username);
		loginPage.setPassword(password);
		loginPage.unsetMemoCheckbox();
		loginPage.clickSubmitButton();
		Assert.assertTrue(driver.getTitle().contains("Входящие"));
		
		//ending log-in, starting mail sequence
		inboxPage = PageFactory.initElements(loginPage.getWebDriver(),
				InboxPage.class);
		WebDriverWait wait = new WebDriverWait(driver, 60);

		inboxPage.clickNewMailButton();		
		inboxPage.clearAddressField();
		inboxPage.setAddressField(emailAddress);
		inboxPage.clearSubjectField();
		inboxPage.setSubjectField(emailSubject);
		inboxPage.setMailBody(emailBody);
		inboxPage.clicksaveDraftButton();
		
		wait.until(ExpectedConditions.elementToBeClickable(By
				.linkText("Черновики")));
		inboxPage.clickDraftListButton();
		//draft saving check
		assertTrue(inboxPage.checkEmailPresent());

		inboxPage.clickEmail();
		//save validation
		assertEquals(emailSubject, inboxPage.getSubjectValue());
		assertEquals(emailAddress, inboxPage.getAddressPannelValue());
		assertEquals(emailBody+"\n\n\n--\nasdasd asdasd", inboxPage.getMailBody());
		
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@title='Отправить']")));

		inboxPage.clickSendMailButton();
		inboxPage.webDriver.get(inboxPage.webDriver.getCurrentUrl());

		inboxPage.clickDraftListButton();
		//draft deleting check
		assertFalse(inboxPage.checkEmailPresent());

		inboxPage.clickMailSendListButton();
		//sent mail saving check
		assertTrue(inboxPage.checkEmailPresent());
		//after-test cleaning
		inboxPage.clickMailSelection();
		inboxPage.clickRemoveMailButton();
		loginPage.clickLogoutButton();
	}
}
