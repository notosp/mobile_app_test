package login

import internal.GlobalVariable
import cucumber.api.java.en.*
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import groovy.json.JsonSlurper

public class steps {
	String filePath = "Test Data/Login/Assert.json"
	def jsonSlurper = new JsonSlurper()
	def assertData = jsonSlurper.parse(new File(filePath))

	@Given("The Login Page")
	def the_login_page() {
		"Checking the main screen and making sure the app is running perfectly"
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Dashboard/android.widget.ImageView - App Logo"), 10)
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Dashboard/android.widget.ImageView - Menu Button"), 10)
		Mobile.tap(findTestObject("Object Repository/OB_Dashboard/android.widget.ImageView - Menu Button"), 0)

		"Waiting the side menu until opened"
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Dashboard/android.widget.TextView - Log In Menu"), 10)
		Mobile.tap(findTestObject("Object Repository/OB_Dashboard/android.widget.TextView - Log In Menu"), 0)

		"Waiting the login page until loaded"
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Login/android.widget.TextView - Login Page Title"), 10)
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Login/android.widget.TextView - Login Page Description"), 10)
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Login/android.widget.EditText - Username Input"), 10)
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Login/android.widget.EditText - Password Input"), 10)
		Mobile.waitForElementPresent(findTestObject("Object Repository/OB_Login/android.view.ViewGroup - Login Button"), 10)

		"Assert the login page title and description"
		String login_title = Mobile.getText(findTestObject("Object Repository/OB_Login/android.widget.TextView - Login Page Title"), 0)
		String login_description = Mobile.getText(findTestObject("Object Repository/OB_Login/android.widget.TextView - Login Page Description"), 0)
		Mobile.verifyEqual(login_title, assertData.title)
		Mobile.verifyEqual(login_description, assertData.desc)
	}

	@When(/^I enter (?:"([^"]*)?" on username field|a valid username)$/)
	def i_enter_a_valid_username(String username) {
		if (username == null || username.trim().isEmpty()) username = "bob@example.com"
		String object = "Object Repository/OB_Login/android.widget.EditText - Username Input"

		Mobile.waitForElementPresent(findTestObject(object), 10)
		Mobile.clearText(findTestObject(object), 0)
		Mobile.setText(findTestObject(object), username, 0)
	}

	@When(/^enter (?:"([^"]*)?" on password field|a valid password)$/)
	def enter_a_valid_password(String password) {
		if (password == null || password.trim().isEmpty()) password = "10203040"
		String object = "Object Repository/OB_Login/android.widget.EditText - Password Input"

		Mobile.waitForElementPresent(findTestObject(object), 10)
		Mobile.clearText(findTestObject(object), 0)
		Mobile.setText(findTestObject(object), password, 0)
	}

	@When("click login button")
	def click_login_button() {
		String object = "Object Repository/OB_Login/android.view.ViewGroup - Login Button"

		Mobile.waitForElementPresent(findTestObject(object), 10)
		Mobile.tap(findTestObject(object), 0)
	}

	@Then("I success do login")
	def i_success_do_login() {
		String object = "Object Repository/OB_Dashboard/android.widget.TextView - Products Page Title"

		Mobile.waitForElementPresent(findTestObject(object), 10)
		String titleText = Mobile.getText(findTestObject(object), 0)

		if (titleText == "Products") Mobile.verifyEqual(titleText, "Products")
		if (titleText == "Login") Mobile.verifyEqual(titleText, "Login")
	}

	@Then("I will see error message about invalid credential")
	def i_will_see_error_message_about_invalid_credential() {
		String object = "Object Repository/OB_Login/android.widget.TextView - Login Error Message"

		Mobile.waitForElementPresent(findTestObject(object), 10)
		String errorMessage = Mobile.getText(findTestObject(object), 0)

		Mobile.verifyEqual(errorMessage, assertData.error.invalidCredential)
	}

	@Then("I will see error message about blocked credential")
	def i_will_see_error_message_about_blocked_credential() {
		String object = "Object Repository/OB_Login/android.widget.TextView - Login Error Message"

		Mobile.waitForElementPresent(findTestObject(object), 10)
		String errorMessage = Mobile.getText(findTestObject(object), 0)

		Mobile.verifyEqual(errorMessage, assertData.error.blockedCredential)
	}
}