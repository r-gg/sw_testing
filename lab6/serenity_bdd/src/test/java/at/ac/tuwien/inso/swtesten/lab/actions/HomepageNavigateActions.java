package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomepageNavigateActions extends UIInteractions {
	@FindBy(xpath = "//input[@type='email']")
	private WebElement usernameEmailInputField;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordInputField;
	@FindBy(xpath = "//li[@aria-label='Settings']")
	private WebElement settingsMenu;
	@FindBy(xpath = "//a[contains(text(),'Account')]")
	private WebElement settingsAccountOption;
	@FindBy(xpath = "//a[contains(text(),'Payment Method')]")
	private WebElement settingsPaymentMethodOption;
	@FindBy(xpath = "//a[contains(text(),'Shoppingcart')]")
	private WebElement settingsShoppingCartOption;
	@FindBy(id = "loginButton")
	private WebElement loginButton;
	@FindBy(id = "loginSubmit")
	private WebElement loginSubmit;
	@FindBy(name = "group")
	private WebElement groupSelectorField;
	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	@Step("Login")
	public void login() {
		loginButton.click();

		usernameEmailInputField.clear();
		usernameEmailInputField.sendKeys("user15.a@mail.com");

		passwordInputField.clear();
		passwordInputField.sendKeys("T2022-x274");

		loginSubmit.click();
	}

	@Step("Navigate to homepage")
	public void toHomepage() {
		openUrl("https://peso.inso.tuwien.ac.at/st/v1/");
		new Select(groupSelectorField).selectByValue("grp15");
		submitButton.click();
	}

	@Step("Navigate to account info")
	public void toAccountInfo() {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(settingsMenu).perform();
		settingsAccountOption.click();
	}

	@Step("Navigate to payment methods")
	public void toPaymentMethods() {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(settingsMenu).perform();
		settingsPaymentMethodOption.click();
	}

	@Step("Navigate to shopping cart")
	public void toShoppingCart() {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(settingsMenu).perform();
		settingsShoppingCartOption.click();
	}

	@Step("Navigate to item")
	public void toItem(String item) {
		String itemPath = "//img[@title='" + item + "']/../..";
		getDriver().findElement(By.xpath(itemPath)).click();
	}
}
