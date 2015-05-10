package com.epam.webtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {
	@FindBy(id = "mailbox__login")
	private WebElement emailField;

	@FindBy(id = "mailbox__password")
	private WebElement passField;

	@FindBy(id = "mailbox__auth__remember__checkbox")
	private WebElement memoCheckbox;

	@FindBy(id = "mailbox__auth__button")
	private WebElement submitButton;

	@FindBy(id = "PH_logoutLink")
	private WebElement logoutButton;

	public LoginPage(WebDriver webDriver) {
		super(webDriver);
	}

	public LoginPage setEmail(String email) {
		emailField.sendKeys(email);
		return this;
	}

	public LoginPage setPassword(String password) {
		passField.sendKeys(password);
		return this;
	}

	public LoginPage unsetMemoCheckbox() {
		if (memoCheckbox.isSelected()) {
			memoCheckbox.click();
		}
		return this;
	}

	public void clickSubmitButton() {
		submitButton.click();
	}

	public void clickLogoutButton() {
		logoutButton.click();
	}
}
