Feature: Search lists correct results

  As a customer I want to search for items.

  Scenario Outline: Search lists the correct results
	Given I am on the homepage
	When I search for "<searchString>"
	Then I expect "<found>" item to be found

	Examples:
	  | searchString     | found |
	  | A                | 8     |
	  | Apple            | 2     |
	  | Arizona Bound    | 1     |
	  | DRACULA          | 1     |
	  | pingus           | 1     |
	  | Arizona Boundies | 0     |
	  | ZZ               | 0     |
