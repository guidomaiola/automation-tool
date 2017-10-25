@smokeTest
Feature: To test my cucumber test is running
I want to run a sample feature file.

 Scenario: I visit Google web search
       Given I navigate to "https://www.google.com" in firefox
	   And take screenshot with filename "screenshot-google.png" 
       And I wait for 15 sec
       And I enter "prueba" into input field having id "lst-ib"
       And I press enter into element with id "lst-ib"
	   And take screenshot with filename "screenshot-google-results.png" 
	   And I wait for 15 sec
       Then I close browser

 