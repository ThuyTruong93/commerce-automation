package katalon.testops.testexecution
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class ScheduleTestRunPopUp extends BasePage<ScheduleTestRunPopUp> {

	public ScheduleTestRunPopUp inputTestRunName(String value) {
		WebUI.clearText(txt('Name'))
		WebUI.sendKeys(txt('Name'), value)
		return this
	}

	public ScheduleTestRunPopUp clickScriptRepo() {
		WebUI.click(id('testProjectId'))
		return this
	}

	public ScheduleTestRunPopUp selectScriptRepo(String value) {
		WebUI.scrollToElement(text(value), 5)
		WebUI.click(text(value))
		return this
	}

	public ScheduleTestRunPopUp clickConfigType() {
		WebUI.click(id('configType'))
		return this
	}

	public ScheduleTestRunPopUp selectConfigType(String value) {
		WebUI.click(text(value))
		return this
	}

	public ScheduleTestRunPopUp clickTestSuite() {
		WebUI.click(id('tsList'))
		return this
	}

	public ScheduleTestRunPopUp selectTestSuite(String value) {
		WebUI.click(text(value))
		return this
	}


	public ScheduleTestRunPopUp clickBrowserType() {
		WebUI.click(id('browserType'))
		return this
	}

	public ScheduleTestRunPopUp selectBrowserType(String value) {
		WebUI.click(text(value))
		return this
	}

	public ScheduleTestRunPopUp clickProfile() {
		WebUI.click(id('executionProfileList'))
		return this
	}

	public ScheduleTestRunPopUp selectProfile(String value) {
		WebUI.click(title(value))
		return this
	}

	public ScheduleTestRunPopUp clickTurnOffRepeat() {
		WebUI.click(id('repeat-enable'))
		return this
	}

	public ScheduleTestRunPopUp clickTestCloudEnvironment() {
		WebUI.click(id('testCloudType'))
		return this
	}

	public ScheduleTestRunPopUp selectPlatform(String value) {
		WebUI.click(title(value))
		return this
	}

	public ScheduleTestRunPopUp selectBrowser(String value) {
		WebUI.click(title(value))
		return this
	}

	public ScheduleTestRunPopUp clickMobileNativeApp() {
		WebUI.click(id("mobileNative"))
		return this
	}

	public ScheduleTestRunPopUp selectMobileNativeApp(String value) {
		WebUI.click(text(value))
		return this
	}

	public ScheduleTestRunPopUp verifyMobielNativeApp(String value) {
		WebUI.verifyTextNotPresent(value, false)
		return this
	}

	public ScheduleTestRunPopUp selectVersion(String value) {
		WebUI.click(title(value))
		return this
	}

	public ScheduleTestRunPopUp selectDeviceType(String value) {
		WebUI.click(title(value))
		return this
	}

	public ScheduleTestRunPopUp selectDevice(String value) {
		WebUI.scrollToElement(title(value), 1)
		WebUI.click(title(value))
		return this
	}

	public ScheduleTestRunPopUp clickSchedule() {
		WebUI.click(btn('Schedule'))
		return this
	}

	public ScheduleTestRunPopUp clickRun() {
		WebUI.click(btn('Run'))
		return this
	}

	public ScheduleTestRunPopUp clickCancel() {
		WebUI.click(btn('Cancel'))
		return this
	}

	public ScheduleTestRunPopUp clickTestCloudTunnelEnable() {
		WebUI.click(id('testcloud-tunnel-enable'))
		return this
	}
}