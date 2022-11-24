Feature: Search for Wikipedia article

  Background:
	Given the browser language is "EN"
	And I am on the Wikipedia start page
	And the English version is selected

  Scenario Outline: Search for an existing article
	When I enter the search term "<term>"
	Then the result page with heading "<heading>" is shown

	Examples:
	| term 				| heading 			|
	| Software Test     | Software testing 	|
	| Icecream          | Ice cream         |
	| umbrella          | Umbrella          |

  Scenario Outline: Search for non existing article
	When I enter the invalid search term "<wrong-term>"
	Then no results are found and an error message "<message>" is displayed

	Examples:
	| wrong-term 	| message 	|
	| iximixi		| Iximixi  	|
	| lumidomi##!   | Lumidomi 	|

