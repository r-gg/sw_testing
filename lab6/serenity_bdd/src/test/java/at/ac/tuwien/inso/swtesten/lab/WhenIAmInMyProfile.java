package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.actions.AddressActions;
import at.ac.tuwien.inso.swtesten.lab.actions.HomepageNavigateActions;
import at.ac.tuwien.inso.swtesten.lab.pagecomponents.AccountInfoResult;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenIAmInMyProfile {
	HomepageNavigateActions homepageNavigateAction;
	AddressActions addressActions;
	AccountInfoResult accountInfoResult;

	@Test
	void onAddressChangeTheNewAddressDisplayed() {
		homepageNavigateAction.toHomepage();
		homepageNavigateAction.login();
		homepageNavigateAction.toAccountInfo();

		String city = "Vienna";
		String street = "Gusshausstraße 28";
		String zip = "1040";
		String country = "Austria";

		addressActions.changeStreet(street);
		addressActions.changeCity(city);
		addressActions.changeZip(zip);
		addressActions.changeCountry(country);
		addressActions.submit();

		Serenity.reportThat("I can see my new address on my profile", ()
				-> assertThat(accountInfoResult.addressIsShown(street, city, zip, country)).isTrue());
	}
}

