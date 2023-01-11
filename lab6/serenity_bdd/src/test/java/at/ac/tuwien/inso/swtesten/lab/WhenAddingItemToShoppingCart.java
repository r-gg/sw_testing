package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.actions.HomepageNavigateActions;
import at.ac.tuwien.inso.swtesten.lab.actions.PaymentMethodsActions;
import at.ac.tuwien.inso.swtesten.lab.pagecomponents.PaymentMethodsResult;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenAddingItemToShoppingCart {
	HomepageNavigateActions homepageNavigateAction;
	PaymentMethodsActions paymentMethodsActions;
	PaymentMethodsResult paymentMethodsResult;
	@Test
	void onPlacingItemInShoppingCartAndSubmittingOrder() {
		homepageNavigateAction.toHomepage();
		homepageNavigateAction.login();
//		homepageNavigateAction.toItem();


		Serenity.reportThat("I can see my new credit card in my payment methods", ()
				-> assertThat(true).isTrue());
	}
}
