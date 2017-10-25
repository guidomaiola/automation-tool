@smokeTest
Feature: To test the login feature for El Comercio MKP

Scenario: I visit El Comercio MKP
		Given I navigate to "https://elcomercio-aws.qubikdigital.com:9002/elcomerciomkpstorefront/elcomerciomkp/es/" in firefox
		And I wait for 30 sec
		And take screenshot with filename "screenshot-elcomercio.png" 
		And I enter "hdmi" into input field having id "js-site-search-input"
		And I press enter into element with id "js-site-search-input"
		And I wait for 30 sec
		And take screenshot with filename "screenshot-elcomerciobusqueda.png" 
		Then I close browser

Scenario: I login into El Comercio MKP with valid credentials
		Given I navigate to "https://elcomercio-aws.qubikdigital.com:9002/elcomerciomkpstorefront/elcomerciomkp/es/" in firefox
		And I wait for 30 sec 
		And I enter "guidomaiola@gmail.com" into input field having id "j_username"
		And I enter "guido123" into input field having id "j_password"
		And I press enter into element with id "j_password"
		And I wait for 30 sec
		And take screenshot with filename "screenshot-elcomercio-login-OK.png" 
		Then I close browser

Scenario: I cant login into El Comercio MKP with invalid credentials
		Given I navigate to "https://elcomercio-aws.qubikdigital.com:9002/elcomerciomkpstorefront/elcomerciomkp/es/" in firefox
		And I wait for 30 sec
		And take screenshot with filename "screenshot-elcomercio-login.png" 
		And I enter "invalid@gmail.com" into input field having id "j_username"
		And I enter "invalid" into input field having id "j_password"
		And I press enter into element with id "j_password"
		And I wait for 30 sec
		And take screenshot with filename "screenshot-elcomercio-login-FAIL.png" 
		Then I close browser	