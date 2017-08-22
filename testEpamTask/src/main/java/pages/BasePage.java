package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Sent Mail")
	private WebElement linkSentMail;

	@FindBy(xpath = "//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")
	private WebElement buttonAccount;

	@FindBy(xpath = "//*[@id='gb_71']")
	private WebElement buttonExit;

	public void openSentMails() {
		linkSentMail.click();
		waitForTitleContains(driver, "Sent Mail");
	}

	public void logOut() {
		waitForElementIsClickable(driver, buttonAccount);
		buttonAccount.click();
		buttonExit.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			System.out.println("Alert is not found, BasePage.class logOut method");
		}
		
		try {
			waitForElementPresentById(driver, "Passwd");
		} catch (Exception e) {
			waitForElementPresentByXpath(driver, "//input[@type=\"password\"]");
		}

	}
	public void deleteAllCookiesAndRefresh(){
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	// --------------------------------------------------
	// "What For Something" methods

	public void waitForTitleContains(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void waitForElementPresentById(WebDriver driver, String selector) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(selector)));
	}

	public void waitForElementPresentByCss(WebDriver driver, String selector) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
	}

	public void waitForElementPresentByXpath(WebDriver driver, String selector) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
	}

	public void waitForElementIsClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
