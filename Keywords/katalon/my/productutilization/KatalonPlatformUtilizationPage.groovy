package katalon.my.productutilization

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class KatalonPlatformUtilizationPage extends BasePage<KatalonPlatformUtilizationPage> {

	public KatalonPlatformUtilizationPage clickKatalonPlatformTab(String name) {
		WebUI.click(text("Katalon Platform"))
		return this
	}

	public KatalonPlatformUtilizationPage verifyUIKatalonPlatformUtilizationPage() {
		WebUI.verifyElementPresent(text("Usage Dashboard"), 5)
		WebUI.verifyElementPresent(xpath("//*[contains(text(),'You are on')]"), 5)
		WebUI.verifyElementPresent(btn("Export"), 5)
		WebUI.verifyElementPresent(text("Test Results"), 5)
		//icon (i) next to Test Results text
		WebUI.verifyElementPresent(xpath("//*[text()='Test Results']/*[name()='svg']"), 5)
		WebUI.mouseOver(xpath("//*[text()='Test Results']/*[name()='svg']"))
		WebUI.verifyElementPresent(xpath("//*[contains(text(),'Test results will be reset each month. ')]"), 5)
		//hyperlink learn more
		WebUI.mouseOver(xpath("//*[text()='Test Results']/*[name()='svg']"))
		WebUI.verifyElementPresent(xpath("//*[@href='https://docs.katalon.com/docs/administer/katalon-platform-packages/katalon-platform-quotas#test-results']"), 5)
		//tooltips donut chart
		WebUI.mouseOver(xpath("//*[name()='canvas']"))
		WebUI.verifyElementPresent(xpath("//*[contains(text(),'Balance')]"),5)
		WebUI.verifyElementPresent(text("Test Activities"), 5)
		//icon (i) next to Test Activities text
		WebUI.verifyElementPresent(xpath("//*[text()='Test Activities']/*[name()='svg']"), 5)
		WebUI.mouseOver(xpath("//*[text()='Test Activities']/*[name()='svg']"))
		def contTooltipsTestActivities = WebUI.getText(xpath("//*[@role='tooltip']/div/div"))
		WebUI.verifyEqual(contTooltipsTestActivities, "This number represents the total number of test results for the last 7 days, and includes from tests run directly on the Katalon Platform.")
		WebUI.verifyElementPresent(text("Test results"), 5)
		WebUI.verifyElementPresent(text("Test duration"), 5)
		return this
	}
}
