package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.ItemPage;

public class ItemStepDefinitions extends BugstoreStepDefinitions{

	private ItemPage itemPage;

	public ItemStepDefinitions() {
		super();

		When("I open the details of {string}", (String item) -> {
			itemPage = getHomePage().navigateToItem(item);
		});

		When("I add {string} items to my shopping cart", (String amount) -> {
			itemPage.addItemToShoppingCart(amount);
		});
	}
}
