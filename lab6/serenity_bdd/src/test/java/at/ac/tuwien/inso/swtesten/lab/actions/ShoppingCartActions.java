package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartActions extends UIInteractions {
	@FindBy(xpath = "//button[span='Checkout']")
	private WebElement submit;

	public void submitOrder(){
		submit.click();
	}
}
