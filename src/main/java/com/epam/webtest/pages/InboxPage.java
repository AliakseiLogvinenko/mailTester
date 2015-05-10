package com.epam.webtest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends Page {

	@FindBy(xpath = "//div[@title='Отправить']")
	private WebElement sendMailButton;

	@FindBy(linkText = "Отправленные")
	private WebElement mailSentListButton;

	@FindBy(linkText = "Черновики")
	private WebElement draftListButton;

	@FindBy(css = "span.b-toolbar__btn__text.b-toolbar__btn__text_pad")
	private WebElement newMailButton;

	@FindBy(css = "textarea.js-input.compose__labels__input")
	private WebElement addressField;

	@FindBy(name = "Subject")
	private WebElement subjectField;

	@FindBy(xpath = "//div[@id='compose__header__content']/div[2]/div[2]/div/span[3]/span")
	private WebElement addressPannel;

	@FindBy(xpath = "//div[@data-name='saveDraft']")
	private WebElement saveButton;

	@FindBy(css = "div.b-datalist__item__panel")
	private WebElement email;

	@FindBy(css = "div.js-item-cbx.b-datalist__item__cbx > div.js-cbx.cbx")
	private WebElement mailSelection;

	@FindBy(xpath = "//iframe[@title='{#aria.rich_text_area}']")
	private WebElement mailBody;

	public InboxPage(WebDriver webDriver) {
		super(webDriver);
	}

	public InboxPage setAddressField(String address) {
		addressField.sendKeys(address);
		return this;
	}

	public String getAddressPannelValue() {
		return addressPannel.getText();
	}

	public InboxPage setMailBody(String content) {
		this.getWebDriver().switchTo().frame(mailBody);
		WebElement element = this.getWebDriver().findElement(
				By.cssSelector("body"));
		element.click();
		element.sendKeys(content);
		this.getWebDriver().switchTo().defaultContent();
		return this;
	}

	public String getMailBody() {
		this.getWebDriver().switchTo().frame(mailBody);
		WebElement element = this.getWebDriver().findElement(
				By.cssSelector("body"));
		element.click();
		String content = element.getText();
		this.getWebDriver().switchTo().defaultContent();
		return content;
	}

	public InboxPage setSubjectField(String subject) {
		subjectField.sendKeys(subject);
		return this;
	}

	public void clearAddressField() {
		addressField.clear();
	}

	public void clearSubjectField() {
		subjectField.clear();
	}

	public String getSubjectValue() {
		return subjectField.getAttribute("value");
	}

	public void clickNewMailButton() {
		newMailButton.click();
	}

	public void clickSendMailButton() {
		sendMailButton.click();
	}

	public void clickRemoveMailButton() {

		List<WebElement> removeButtons = webDriver.findElements(By
				.xpath("//div[@data-shortcut='del|command+backspace']"));

		for (int j = 0; j < removeButtons.size(); j++) {
			if (removeButtons.get(j).isDisplayed())
				removeButtons.get(j).click();
		}
	}

	public void clickMailSendListButton() {
		mailSentListButton.click();
	}

	public void clickDraftListButton() {
		draftListButton.click();
	}

	public void clicksaveDraftButton() {
		saveButton.click();
	}

	public boolean checkEmailPresent() {
		try {
			email.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clickEmail() {
		email.click();
	}

	public void clickMailSelection() {
		mailSelection.click();
	}
}
