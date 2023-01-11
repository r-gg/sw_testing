package at.ac.tuwien.inso.swtesten.lab.pagecomponents;

import net.serenitybdd.core.pages.PageComponent;

public class SearchResult extends PageComponent {
	public Boolean itemTitleVisible(String title) {
		return !$("//a/h5[contains(text(),'" + title + "')]").getText().equals("");
	}

	public Boolean itemDescriptionVisible(String title, String description) {
		return !$("//a/h5[contains(text(),'" + title + "')]/../div[contains(text(), '" + description + "')]")
				.getText().equals("");
	}
}
