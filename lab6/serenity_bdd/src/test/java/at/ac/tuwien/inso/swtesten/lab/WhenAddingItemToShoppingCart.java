package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.actions.HomepageNavigateActions;
import at.ac.tuwien.inso.swtesten.lab.actions.ItemActions;
import at.ac.tuwien.inso.swtesten.lab.actions.ShoppingCartActions;
import at.ac.tuwien.inso.swtesten.lab.pagecomponents.ShoppingCartResult;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenAddingItemToShoppingCart {
	HomepageNavigateActions homepageNavigateAction;
	ItemActions itemActions;
	ShoppingCartActions shoppingCartActions;
	ShoppingCartResult shoppingCartResult;

	@Test
	void onPlacingItemInShoppingCartAndSubmittingOrder() {
		homepageNavigateAction.toHomepage();
		homepageNavigateAction.login();

		String title = "Arizona Bound";
		String amount = "4";
		String price = "63.96";

		homepageNavigateAction.toItem(title);
		itemActions.addToShoppingCart(amount);
		homepageNavigateAction.toShoppingCart();

		Serenity.reportThat("I expect 4 Arizona Bound items in my cart with the total price 63.96", ()
				-> assertThat(shoppingCartResult.isItemInShoppingCart(title, amount, price)).isTrue());

		shoppingCartActions.submitOrder();

		Serenity.reportThat("I expect my order is successfully placed", ()
				-> assertThat(shoppingCartResult.isOrderSuccessfullyPlaced()).isTrue());
	}
}
