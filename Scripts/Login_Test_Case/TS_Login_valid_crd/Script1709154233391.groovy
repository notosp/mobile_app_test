import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

CucumberKW.GLUE = ["app", "login"]
CucumberKW.runFeatureFileWithTags("Include/features/Login.feature", [ "@LoginSuccess" ] as String[])