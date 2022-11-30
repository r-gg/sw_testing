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
      | street            | zip  		| city   | country |
      | Gusshausstraße 28 | 1040 		| Vienna | Austria |
	  | Gusshausstraße 28 | 1234567891 	| Vienna | Austria |

  Scenario Outline: I mistakenly input an invalid value in one of the fields
	Given I login with valid credentials
	When I navigate to my profile
	And I change my name to "<firstname>" "<lastname>"
	And I change my date of birth to "<dateofbirth>"
	And I change my address to "<street>", "<zip>", "<city>", "<country>"
	And I submit my changes
	Then An error message is shown
	And My old data is unchanged

	Examples:
	  | firstname | lastname 	| dateofbirth 	| street            	| zip  			| city   	| country |
	  | Spongebob | Squarepants | 06/14/1986	| Gusshausstraße 28 	| 104 			| Vienna 	| Austria |
	  | Spongebob | Squarepants | 06/14/1986 	| Gusshausstraße 28 	| 123456789012 	| Vienna 	| Austria |
	  | Spongebob | Squarepants | 06/14/1986 	|  						| 1040 			| Vienna 	| Austria |
	  | Spongebob | Squarepants | 06/14/1986 	| Gusshausstraße 28 	| 1040 			|  			| Austria |
