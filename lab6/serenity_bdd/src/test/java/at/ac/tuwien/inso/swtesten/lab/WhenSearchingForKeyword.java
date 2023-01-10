package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.actions.HomepageNavigateActions;
import at.ac.tuwien.inso.swtesten.lab.actions.SearchActions;
import at.ac.tuwien.inso.swtesten.lab.pagecomponents.SearchResult;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenSearchingForKeyword {

	@Managed(driver = "chrome")
	WebDriver driver;

	HomepageNavigateActions homepageNavigateAction;
	SearchActions search;
	SearchResult searchResult;

	@Test
	void theItemShouldBeVisible() {
		homepageNavigateAction.toHomepage();
		String title = "Arizona Bound";
		String description = "Arizona Bound is a lost 1927 American Western silent film directed by John Waters and starring Gary Cooper, Betty Jewel, and El Brendel.";

		search.byKeyword(title);

		Serenity.reportThat("The item title should be visible", ()
				-> assertThat(searchResult.itemTitleVisible(title)).isTrue());

		Serenity.reportThat("The item description should be visible", ()
				-> assertThat(searchResult.itemDescriptionVisible(title, description)).isTrue());
	}
}
