@smokeTest
Feature: To test my cucumber test is running
I want to run a sample feature file.

 Scenario: I login with valid credential
       Given I navigate to "https://elcomercio-aws.qubikdigital.com:9002/elcomercioscfront/static/app/" in firefox
       And I press enter into field having id ""
       And I enter "gum" into input field having id ""
       And I enter "guido123" into input field having id "register.checkPwd"
       When I press enter into field having xpath ""
       And I wait for 15 sec
	   And take screenshot with filename "screenshot-login-success1.png" 
       Then I close browser
 
