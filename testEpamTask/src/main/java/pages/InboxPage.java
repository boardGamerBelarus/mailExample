package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class InboxPage extends BasePage {
	WebDriver driver;

	public InboxPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean checkEmailIsPresentBySenderAdress(String adress) {
		try {
			driver.findElement(By.xpath("//span[@email=\"" + adress + "\"]"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean removeDialogBySenderAdress(String adress) {
		try {

			WebElement checkbox = driver
					.findElement(By.xpath("//tr[td/div[span[@email=\"" + adress + "\"]]]/td/div[@role=\"checkbox\"]"));
			checkbox.click();

			// WebElement linkHideNotifocations =
			// driver.findElement(By.id("link_enable_notifications_hide"));
			// linkHideNotifocations.click();

			waitForElementPresentByXpath(driver, "//*[@id=':5']/div/div[1]/div[1]/div/div/div[2]/div[3]");
			//									
			WebElement buttonDelete = driver
					.findElement(By.xpath("//*[@id=':5']/div/div[1]/div[1]/div/div/div[2]/div[3]"));

			buttonDelete.click();

			waitForElementPresentByXpath(driver, "//span[contains(text(), 'The conversation has been moved to the Trash')]");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
