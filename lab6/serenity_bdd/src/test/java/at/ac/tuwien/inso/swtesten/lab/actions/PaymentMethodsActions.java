package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PaymentMethodsActions extends UIInteractions {
	@FindBy(xpath = "//button[@aria-controls='addNewPaymentMethodModal']")
	private WebElement addPaymentMethod;
	@FindBy(xpath = "//a[@id='addCreditCard-label']")
	private WebElement addCreditCard;
	@FindBy(xpath = "//div[@id='addCreditCard']//input[@name='account']")
	private WebElement cardNumberInputField;
	@FindBy(xpath = "//div[@id='addCreditCard']//input[@name='owner']")
	private WebElement ownerInputField;
	@FindBy(xpath = "//div[@id='addCreditCard']//button//span[text()='Save']")
	private WebElement save;

	@Step("Create a new payment method")
	public void newPaymentMethod() {
		addPaymentMethod.click();
	}

	@Step("Create a new credit card")
	public void newCreditCard() {
		addCreditCard.click();
	}

	@Step("Fill credit card information")
	public void createCreditCard(String cardNumber, String owner, String month, String year) {
		cardNumberInputField.sendKeys(cardNumber);
		ownerInputField.sendKeys(owner);

		new Select(getDriver().findElement(By.xpath("//div[@id='addCreditCard']//select[@name='month']"))).selectByValue(Integer.valueOf(month).toString());
		new Select(getDriver().findElement(By.xpath("//div[@id='addCreditCard']//select[@name='year']"))).selectByValue(year);

		save.click();
	}
}
