Feature: Items are correctly displayed on homepage

  As a customer I want to see all purchasable items on the homepage.

  Scenario Outline: Items are correctly displayed on homepage
	Given I am on the homepage
	Then A tile with the title "<title>" and the description "<description>" is displayed.

	Examples:
	  | title             | description                                                                                                                                                                                                                                                                                                                                                |
	  | Arizona Bound     | Arizona Bound is a lost 1927 American Western silent film directed by John Waters and starring Gary Cooper, Betty Jewel, and El Brendel.                                                                                                                                                                                                                   |
	  | King Kong         | King Kong is a 1933 American pre-Code disaster film directed and produced by Merian C. Cooper and Ernest B. Schoedsack. The screenplay by James Ashmore Creelman and Ruth Rose was from an idea conceived by Cooper and Edgar Wallace. It stars Fay Wray, Bruce Cabot and Robert Armstrong, and opened in New York City on March 2, 1933, to rave reviews. |
	  | PCC 2000          | PCC 2000 is a professional computer released in 1978. It was designed in 1978 by Pertec, the company which merged with MITS by the end of 1976.                                                                                                                                                                                                            |
	  | Pingus            | Pingus is a free, open-source computer game inspired by Lemmings and created by Ingo Ruhnke. It is a video game clone of Lemmings and features penguins instead of lemmings.                                                                                                                                                                               |
	  | The Doctor In War | The Doctor In War is a book published in November 1918 by Woods Hutchinson, an American medical doctor who travelled throughout Europe from 15 January to 24 December 1917 visiting hospitals, ambulance trains, and other locations to offer his services to the war effort during World War I.                                                           |
