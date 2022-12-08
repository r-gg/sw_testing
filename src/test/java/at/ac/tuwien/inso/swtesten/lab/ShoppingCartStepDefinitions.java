package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.ShoppingCartPage;

public class ShoppingCartStepDefinitions extends BugstoreStepDefinitions {

	private ShoppingCartPage shoppingCartPage;

	public ShoppingCartStepDefinitions(){
		super();

		When("I navigate to my cart", () -> {
			shoppingCartPage = getHomePage().navigateToShoppingCart();
		});

		Then("I expect {string} {string} in my cart with the total price {string}", (String amount, String item, String price) -> {
			shoppingCartPage.assertItemInShoppingCart(amount, item, price);
		});

		When("I submit my order", () -> {
			shoppingCartPage.submitOrder();
		});

		Then("my order is successfully placed", () -> {
			shoppingCartPage.assertOrderSuccessfullyPlaced();
		});
	}
}
