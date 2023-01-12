package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemActions extends UIInteractions {
	@FindBy(xpath = "//button[@type='submit' and span='Add']")
	private WebElement addButton;
	@FindBy(name = "amount")
	private WebElement amountSelect;

	@Step("Add items to shopping cart")
	public void addToShoppingCart(String amount) {
		if (amount.equals("1"))
			amount = String.format("%02d", Integer.valueOf(amount));

		amountSelect.sendKeys(amount);
		addButton.click();
	}
}
