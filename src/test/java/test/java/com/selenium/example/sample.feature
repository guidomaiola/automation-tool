@smokeTest
Feature: To test my cucumber test is running
I want to run a sample feature file.

 Scenario: I visit Google web search
		Given I navigate to "https://www.google.com" in firefox
		And take screenshot with filename "screenshot-google.png" 
		And I wait for 30 sec
		And I enter "prueba" into input field having id "lst-ib"
		And I press enter into element with id "lst-ib"
		And I wait for 30 sec
		And take screenshot with filename "screenshot-google-results.png" 
		Then I close browser

Scenario: I visit El Comercio MKP
		Given I navigate to "https://elcomercio-aws.qubikdigital.com:9002/elcomerciomkpstorefront/elcomerciomkp/es/" in firefox
		And I wait for 30 sec
		And take screenshot with filename "screenshot-elcomercio.png" 
		And I enter "hdmi" into input field having id "js-site-search-input"
		And I press enter into element with id "js-site-search-input"
		And I wait for 30 sec
		And take screenshot with filename "screenshot-elcomerciobusqueda.png" 
		Then I close browser
