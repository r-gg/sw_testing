package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.actions.HomepageNavigateActions;
import at.ac.tuwien.inso.swtesten.lab.pagecomponents.SearchResult;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenIAmOnTheHomepage {

	HomepageNavigateActions homepageNavigateAction;
	SearchResult searchResult;

	@Test
	void theItemShouldBeVisible() {
		homepageNavigateAction.toHomepage();
		String title = "Arizona Bound";
		String description = "Arizona Bound is a lost 1927 American Western silent film directed by John Waters and starring Gary Cooper, Betty Jewel, and El Brendel.";

		Serenity.reportThat("The item title should be visible", ()
				-> assertThat(searchResult.itemTitleVisible(title)).isTrue());

		Serenity.reportThat("The item description should be visible", ()
				-> assertThat(searchResult.itemDescriptionVisible(title, description)).isTrue());
	}
}
