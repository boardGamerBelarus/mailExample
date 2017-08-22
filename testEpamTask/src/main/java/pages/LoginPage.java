package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.pageHelpers.PageHelper;

public class LoginPage extends BasePage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Email")
	private WebElement inputEmail;

	@FindBy(xpath = "//input[@type=\"email\"]")
	private WebElement inputEmailNewStyle;

	@FindBy(xpath = "//input[@type=\"password\"]")
	private WebElement inputPasswordNewStyle;

	@FindBy(id = "next")
	private WebElement buttonNext;

	@FindBy(id = "Passwd")
	private WebElement inputPassword;

	@FindBy(id = "signIn")
	private WebElement buttonSignIn;

	@FindBy(id = "account-chooser-link")
	private WebElement linkChooseOtherAccount;

	@FindBy(id = "account-chooser-add-account")
	private WebElement linkAddAccount;

	//
	public InboxPage login(String login, String password) {
		
		try {
			inputEmail.sendKeys(login);
			buttonNext.click();
			inputPassword.sendKeys(password);
			buttonSignIn.click();
		} catch (Exception e) {
			inputEmailNewStyle.sendKeys(login);
			inputEmailNewStyle.sendKeys(Keys.ENTER);
			waitForElementIsClickable(driver, inputPasswordNewStyle);
			inputPasswordNewStyle.sendKeys(password);
			inputPasswordNewStyle.sendKeys(Keys.ENTER);
		}
		waitForTitleContains(driver, login);
		return new InboxPage(driver);
	}

}
