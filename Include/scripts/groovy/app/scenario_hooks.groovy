package app

import cucumber.api.java.Before
import cucumber.api.java.After
import cucumber.api.Scenario
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

public class scenario_hooks {
	@Before
	public void beforeScenario(Scenario scenario) {
		Mobile.startApplication("./App/app_testing_demo.apk", true, FailureHandling.STOP_ON_FAILURE)
	}

	@After
	public void afterScenario(Scenario scenario) {
		Mobile.closeApplication()
	}
}
