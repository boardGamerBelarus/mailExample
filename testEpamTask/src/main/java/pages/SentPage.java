package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentPage extends BasePage {
	WebDriver driver;
	JavascriptExecutor javascriptExecutor;
	public SentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//td[contains(text(), 'No sent messages!')]")
	private WebElement textNoSentMessages;

	@FindBy(css = "tr#\\:2b")
	private WebElement trSentEmail;

	public boolean checkEmailIsPresentByRecipientAdress(String adress) {

		try {
			driver.findElement(By.xpath("//span[@email=\"" + adress + "\"]"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean removeDialogByRecipientAdress(String adress) {
		try {
		
			WebElement checkbox = driver
					.findElement(By.xpath("//tr[td/div[span[@email=\"" + adress + "\"]]]/td/div[@role=\"checkbox\"]"));
			checkbox.click();

			//WebElement linkHideNotifocations = driver.findElement(By.id("link_enable_notifications_hide"));
			//linkHideNotifocations.click();
			
			waitForElementPresentByXpath(driver, "//*[@id=':5']/div[2]/div[1]/div[1]/div/div/div[2]/div[3]");
			WebElement buttonDelete = driver.findElement(By.xpath("//*[@id=':5']/div[2]/div[1]/div[1]/div/div/div[2]/div[3]"));

			buttonDelete.click();

			waitForElementPresentByXpath(driver, "//button[@name=\"ok\"]");
			WebElement buttonOkOnConfirmPopUp = driver.findElement(By.xpath("//button[@name=\"ok\"]"));

			try{
			buttonOkOnConfirmPopUp.click();
			}catch (Exception e) {
				System.out.println("Exception in removeDialogByRecipientAdress method ");
			}
			
			
			
			// $('button[name="ok"]');
			waitForElementPresentByXpath(driver, "//td[contains(text(), 'No sent messages!')]");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// //tr[td/div[span[@email="epamtask2@gmail.com"]]]/td/div[@role="checkbox"]

}
