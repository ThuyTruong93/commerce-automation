package katalon.testops.testexecution
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class NewUIScheduleTestRunPopUp extends BasePage<NewUIScheduleTestRunPopUp> {
	def platform = { value -> return xpath("//div[@role='button']//span[text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def browser = { value -> return xpath("//div[@role='button']//span[text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def version = { value -> return xpath("//div[@role='button' and text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def deviceType = { value -> return xpath("//div[@role='button']//span[text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def device = { value -> return xpath("//div[@role='button' and text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def dropdownOption = { value -> xpath("//div[contains(@id, 'option')]//div[. = '${value}'] | //div[contains(@id, 'option') and text()='${value}'] | //div[contains(@id, 'option') and contains(., '${value}')]") }
	def tscTCEnv = { rowIndex -> xpath("//div[@data-rowindex='${rowIndex - 1}']//div[@id='testCloudType']") }

	public NewUIScheduleTestRunPopUp inputTestRunName(String value) {
		clearTextAndSendKeysByActions(id('name'), value)
		return this
	}

	public NewUIScheduleTestRunPopUp clickScriptRepo() {
		WebUI.click(id('testProjectId'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectScriptRepo(String value) {
		WebUI.click(text(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestSuiteTab() {
		WebUI.click(btn('Test Suite'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCTab() {
		WebUI.click(btn('Test Suite Collection'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKatalonCmdTab() {
		WebUI.click(btn('Katalon Command'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickGenericCommandTab() {
		WebUI.click(btn('Generic Command'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestSuiteObjectName() {
		WebUI.click(id('testSuiteId'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCObjectName() {
		WebUI.click(id('testSuiteCollectionId'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectTestSuite(String value) {
		WebUI.click(text(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectTSC(String value) {
		WebUI.click(text(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSEnvDropdown() {
		WebUI.click(xpath("(//label[@for='environment'])[1]/following-sibling::div"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKatalonCmdEnvDropdown() {
		WebUI.click(xpath("(//label[@for='environment'])[3]/following-sibling::div"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickGenericCmdEnvDropdown() {
		WebUI.click(xpath("(//label[@for='environment'])[4]/following-sibling::div"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCConfigLink() {
		WebUI.click(text('Configure environments for Test Suites'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCTestCloudEnv(int rowIndex) {
		WebUI.scrollToElement(tscTCEnv(rowIndex), 1)
		WebUI.click(tscTCEnv(rowIndex))
		return this
	}

	public NewUIScheduleTestRunPopUp clickMoreOptions() {
		WebUI.click(xpath("//li[contains(text(), 'More options')]"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestCloudRadio() {
		WebUI.click(css("input[value='TEST_CLOUD_AGENT']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickLocalRadio() {
		WebUI.click(css("input[value='LOCAL_AGENT']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKubernetesRadio() {
		WebUI.click(css("input[value='K8S_AGENT']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickCircleCIRadio() {
		WebUI.click(css("input[value='CIRCLE_CI_AGENT']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickMobileBrowsersTab() {
		WebUI.click(btn('Mobile Browsers'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickMobileNativeAppTab() {
		WebUI.click(btn('Mobile Native App'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickDesktopBrowsersTab() {
		WebUI.click(btn('Desktop Browsers'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickWebServicesTab() {
		WebUI.click(btn('Web Services'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickProfile() {
		WebUI.click(id('executionProfileList'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectProfile(String value) {
		WebUI.scrollToElement(dropdownOption(value), 1)
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestCloudTunnelEnable() {
		WebUI.click(id('testcloud-tunnel-enable'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickAppDropdown() {
		WebUI.click(id('mobileNative'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectApp(String value) {
		WebUI.scrollToElement(dropdownOption(value), 1)
		WebUI.click(dropdownOption(value))
		return this
	}

	public ScheduleTestRunPopUp verifyAppNotExist(String value) {
		WebUI.verifyTextNotPresent(value, false)
		return this
	}

	public NewUIScheduleTestRunPopUp selectPlatform(String value) {
		WebUI.scrollToElement(platform(value), 1)
		WebUI.click(platform(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectBrowser(String value) {
		WebUI.scrollToElement(browser(value), 1)
		WebUI.click(browser(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectVersion(String value) {
		WebUI.scrollToElement(version(value), 1)
		WebUI.click(version(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectDeviceType(String value) {
		WebUI.scrollToElement(deviceType(value), 1)
		WebUI.click(deviceType(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectDevice(String value) {
		WebUI.scrollToElement(device(value), 1)
		WebUI.click(device(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickLocalTestEnvDropdown() {
		WebUI.click(id('agentList'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectLocalTestEnv(String value) {
		WebUI.scrollToElement(dropdownOption(value), 1)
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickCircleCIEnvDropdown() {
		WebUI.click(id('circleCIAgentList'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectCircleCIEnv(String value) {
		WebUI.scrollToElement(dropdownOption(value), 1)
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKubernetesEnvDropdown() {
		WebUI.click(id('k8sAgentList'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectKubernetesEnv(String value) {
		WebUI.scrollToElement(dropdownOption(value), 1)
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRunWithDropdown() {
		WebUI.click(id('browserType'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectRunWith(String value) {
		WebUI.scrollToElement(dropdownOption(value), 1)
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickBackMoreOptions() {
		WebUI.click(xpath("//div[./following-sibling::div[contains(text(),'Assign Test Environment')]]//button"))
		return this
	}

	public NewUIScheduleTestRunPopUp inputGenericCommand(String value) {
		WebUI.clearText(id('genericCommand'))
		WebUI.sendKeys(id('genericCommand'), value)
		return this
	}

	public NewUIScheduleTestRunPopUp clickRepeatEnable() {
		WebUI.click(id('repeat-enable'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickAdvancedSettings() {
		WebUI.click(id('go-to-advance-tab'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickExecutionMode() {
		WebUI.click(css("label[for='executionMode'] + div"))
		return this
	}

	public NewUIScheduleTestRunPopUp selectExecutionMode(String value) {
		WebUI.click(text(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickBackAdvancedSettings() {
		WebUI.click(xpath("//div[./following-sibling::div[text()='Schedule Test Run']]//button"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRun() {
		WebUI.click(btn('Run'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickSchedule() {
		WebUI.click(btn('Schedule'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickScheduleBtnArrow() {
		WebUI.click(css('[data-testid="KeyboardArrowDownIcon"]'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRunOption() {
		WebUI.click(xpath("//li[. = 'Run']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickScheduleOption() {
		WebUI.click(xpath("//li[. = 'Schedule']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickSaveConfigOption() {
		WebUI.click(xpath("//li[. = 'Save Configuration']"))
		return this
	}

	//TAGS
	public NewUIScheduleTestRunPopUp clickTagInputField() {
		WebUI.click(id("tagEntity"))
		return this
	}

	public NewUIScheduleTestRunPopUp createNewTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), nameOfTag)
		return this
	}

	public NewUIScheduleTestRunPopUp searchExistingTagAndAssign(String nameOfTag) {
		WebUI.setText(id("tagEntity"), nameOfTag)
		scrollToAndClick(title(nameOfTag))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyCreateNewTag(String nameOfTag) {
		WebUI.verifyElementVisible(title(nameOfTag + ' (new tag)'))
		return this
	}

	public NewUIScheduleTestRunPopUp assignTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), Keys.chord(Keys.ENTER))
		return this
	}

	public NewUIScheduleTestRunPopUp unassignTag(String nameOfTag) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${nameOfTag}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTagIsVisible(String nameOfTag) {
		WebUI.verifyElementText(text(nameOfTag), nameOfTag)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTagNotPresent(String tag) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${tag}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public NewUIScheduleTestRunPopUp inputKatalonCmd(String value) {
		WebUI.setText(id('command'), value)
		return this
	}
}