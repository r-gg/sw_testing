Feature: Edit account information details

  As a customer I want to edit my personal account information details.

  Scenario Outline: I have moved and want to change my address
    Given I login with valid credentials
    When I navigate to my profile
	And I change my address to "<street>", "<zip>", "<city>", "<country>"
	And I submit my changes
	Then A confirmation notification is shown
	And I can see my new address on my profile

    Examples:
      | street            | zip  | city   | country |
      | Gusshausstraße 28 | 1040 | Vienna | Austria |
