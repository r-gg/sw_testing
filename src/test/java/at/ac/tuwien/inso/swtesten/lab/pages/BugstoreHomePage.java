package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class BugstoreHomePage extends PageObject {

	@FindBy(id = "loginButton")
	private WebElement loginButton;

	@FindBy(xpath = "//li[@aria-label='Settings']")
	private WebElement settingsMenu;

	@FindBy(xpath = "//a[contains(text(),'Account')]")
	private WebElement settingsAccountOption;

	@FindBy(xpath = "//a[contains(text(),'Payment Method')]")
	private WebElement settingsPaymentMethodOption;

	public BugstoreHomePage(WebDriver driver) {
		super(driver);
	}

	public LoginPage clickLogin(){
		loginButton.click();
		return initPage(LoginPage.class);
	}

	public AccountInfoPage navigateToAccountInfo(){
		Actions actions = new Actions(driver);
		actions.moveToElement(settingsMenu).perform();
		settingsAccountOption.click();
		return initPage(AccountInfoPage.class);
	}

	public PaymentMethodsPage navigateToPaymentMethods() {
		Actions actions = new Actions(driver);
		actions.moveToElement(settingsMenu).perform();
		settingsPaymentMethodOption.click();
		return initPage(PaymentMethodsPage.class);
	}
}
