
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jdbcCore.DBWorker;
import mailApiCore.CreateMessage;
import pages.InboxPage;
import pages.LoginPage;
import pages.SentPage;
import seleniumCore.TestBase;
import xmlCore.Account;
import xmlCore.XMLRunner;

public class GmailTest extends TestBase {
	LoginPage loginPage;

	@DataProvider(name = "mailCreditalsProvider")
	public Object[][] createStuctureData() {
		
		ArrayList<Account> listOfAccounts = XMLRunner.getDataAsList();
		String firstEmailFrom = listOfAccounts.get(0).email;
		String firstPasswordFrom = listOfAccounts.get(0).password;
		
		return new Object[][] { { new String[] { "SOME LOGIN", "SOME PASSWORD" },
				new String[] { firstEmailFrom, firstPasswordFrom }, new String("some Title"),
				new String("text message") },

		};
	}

	// //td[contains(text(), 'barkouartur@gmail.com')]


	@Test(priority = 1, dataProvider = "mailCreditalsProvider")
	public void sendEmail(String to[], String from[], String title, String messageText) {
		CreateMessage createMessage = new CreateMessage(to[0], from, title, messageText);
		createMessage.sendMessage();
	}

	@Test(priority = 2, groups = "seleniumTests", dataProvider = "mailCreditalsProvider")
	public void checkEmailIsSent(String to[], String from[], String title, String messageText) {
		// login into acc1 check email is sent
		System.out.println("checkEmailIsSent method is running");
		loginPage = new LoginPage(driver);
		loginPage.login(from[0], from[1]);
		loginPage.openSentMails();
		SentPage sentPage = new SentPage(driver);
		Assert.assertTrue(sentPage.checkEmailIsPresentByRecipientAdress(to[0]));
		Assert.assertTrue(sentPage.removeDialogByRecipientAdress(to[0]));
		sentPage.logOut();
		sentPage.deleteAllCookiesAndRefresh();
	}

	@Test(enabled = false, priority = 3, groups = "seleniumTests", dataProvider = "mailCreditalsProvider")
	public void checkEmailIsInInbox(String to[], String from[], String title, String messageText) {
		// login into acc2 check email is in inbox
		loginPage = new LoginPage(driver);
		loginPage.login(to[0], to[1]);
		InboxPage inboxPage = new InboxPage(driver);
		Assert.assertTrue(inboxPage.checkEmailIsPresentBySenderAdress(from[0]));
		Assert.assertTrue(inboxPage.removeDialogBySenderAdress(from[0]));
	}
}
