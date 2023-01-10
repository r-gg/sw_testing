package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;

public class SearchActions extends UIInteractions {
	public void byKeyword(String keyword) {
		$(InputField.withNameOrId("searchString")).sendKeys(keyword);
		$(Button.withNameOrId("submitSearchButton")).click();
	}
}
