package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends PageObject {

	@FindBy(xpath = "//button[@type='submit' and span='Add']")
	private WebElement addButton;

	@FindBy(name = "amount")
	private WebElement amountSelect;

	public ItemPage(WebDriver driver) {
		super(driver);
	}

	public void addItemToShoppingCart(String amount){
		if (amount.equals("1"))
			amount = String.format("%02d", Integer.valueOf(amount));

		amountSelect.sendKeys(amount);
		addButton.click();
	}
}
