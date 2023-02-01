package katalon.testops.configurations

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import katalon.common.HomePage
import katalon.fw.lib.BasePage

public class CustomFieldCreatePopUp extends BasePage<CustomFieldCreatePopUp> {
	/* TODO-Tris
	 * 1. wait for popup display
	 * 2. Get list elements
	 * */

	public CustomFieldCreatePopUp inputKey(String key) {
		WebUI.sendKeys(id("key"), key)
		return this
	}

	public CustomFieldCreatePopUp inputDisplayName(String displayName) {
		WebUI.sendKeys(id("display-name"), displayName)
		return this
	}

	public CustomFieldCreatePopUp inputValue(String value) {
		WebUI.sendKeys(id("value"), value)
		return this
	}

	public CustomFieldCreatePopUp clickCreateButton() {
		WebUI.click(title("Create"))
		return this
	}

	public CustomFieldCreatePopUp clickCancelButton() {
		WebUI.click(title("Cancel"))
		return this
	}

	public CustomFieldCreatePopUp addNewCustomField() {
		WebUI.click(xpath("//*[contains(@class,'add-value-btn')]"))
		return this
	}
}
