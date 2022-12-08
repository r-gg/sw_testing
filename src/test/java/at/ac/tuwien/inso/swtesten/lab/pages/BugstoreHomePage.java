package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.junit.jupiter.api.Assertions;
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

	@FindBy(xpath = "//a[contains(text(),'Shoppingcart')]")
	private WebElement settingsShoppingCartOption;

	@FindBy(id = "searchString")
	private WebElement search;

	@FindBy(id = "submitSearchButton")
	private WebElement searchButton;

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

	public ShoppingCartPage navigateToShoppingCart() {
		Actions actions = new Actions(driver);
		actions.moveToElement(settingsMenu).perform();
		settingsShoppingCartOption.click();
		return initPage(ShoppingCartPage.class);
	}

	public ItemPage navigateToItem(String item){
		String itemPath = "//img[@title='" + item + "']/../..";
		WebElement e = driver.findElement(By.xpath(itemPath));
		e.click();

		return initPage(ItemPage.class);
	}

	public BugstoreHomePage searchForItem(String item){
		search.sendKeys(item);
		searchButton.click();

		return this;
	}

	public void assertItemsFound(String amount){
		String itemPath = "//a/div/img";
		Assertions.assertEquals(Integer.parseInt(amount), driver.findElements(By.xpath(itemPath)).size());
	}

	public void assertItemDisplayed(String title, String description){
		String itemTitlePath = "//a/h5[contains(text(),'" + title + "')]";
		String itemDescriptionPath = "//a/h5[contains(text(),'" + title + "')]/../div[contains(text(), '" + description + "')]";

		Assertions.assertNotNull(driver.findElement(By.xpath(itemTitlePath)));
		Assertions.assertNotNull(driver.findElement(By.xpath(itemDescriptionPath)));
	}
}
