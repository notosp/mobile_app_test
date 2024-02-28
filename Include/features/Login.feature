@Login
Feature: Login
	
	As a User, 
	I should be able to access the login page,
	and verify my credentials.
	
	Background:
		Given The Login Page
	
	@LoginSuccess
	Scenario: Login with valid credential
		When I enter a valid username
		And enter a valid password
		And click login button
		Then I success do login

	@LoginFailedInvalidCredential
	Scenario: Login with invalid credential
		When I enter "testingLogin" on username field
		And enter "123456" on password field
		And click login button
		Then I will see error message about invalid credential
	
	@LoginFailedBlockedCredential
	Scenario: Login with blocked credential
		When I enter "nsp@test.com" on username field
		And enter a valid password
		And click login button
		Then I will see error message about blocked credential