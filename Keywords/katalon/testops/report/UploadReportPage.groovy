package katalon.testops.report

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class UploadReportPage extends BasePage<UploadReportPage>{

	public UploadReportPage uploadJUnitReport() {
		String path = RunConfiguration.getProjectDir() + '/Data/TestOps/Report/JUnitReport.xml'
		WebUI.uploadFile(xpath('//form[@data-trackid="upload-junit-reports"]//input[@type="file"]'), path)
		return this
	}

	public UploadReportPage uploadKatalonReport() {
		String path = RunConfiguration.getProjectDir() + '/Data/TestOps/Report/execution0.log'
		WebUI.uploadFile(xpath('//form[@data-trackid="upload-katalon-reports"]//input[@type="file"]'), path)
		return this
	}

	public UploadReportPage inputTagJUnit(String tag) {
		scrollToAndClick(xpath('(//input[@id="tagEntity"])[1]'))
		WebUI.sendKeys(xpath('(//input[@id="tagEntity"])[1]'), tag)
		WebUI.click(xpath("(//*[contains(text(), '${tag}')]/ancestor::li[@role='option'])[1]"))
		return this
	}

	public UploadReportPage inputTagKatalon(String tag) {
		scrollToAndClick(xpath('(//input[@id="tagEntity"])[2]'))
		WebUI.sendKeys(xpath('(//input[@id="tagEntity"])[2]'), tag)
		WebUI.click(xpath("(//*[contains(text(), '${tag}')]/ancestor::li[@role='option'])[1]"))
		return this
	}

	public UploadReportPage clickAddNewCustomFieldJUnit() {
		WebUI.click(xpath("(//*[contains(text(),'Add new')])[1]"))
		return this
	}

	public UploadReportPage clickAddNewCustomFieldKatalon() {
		WebUI.click(xpath("(//*[contains(text(),'Add new')])[2]"))
		return this
	}

	public UploadReportPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		WebUI.click(option(displayName))
		return this
	}

	public UploadReportPage inputCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(option(value))
		return this
	}

	public UploadReportPage clickAddCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public UploadReportPage clickImportJUnitBtn() {
		WebUI.click(xpath('(//button[@type="submit"])[1]'))
		return this
	}


	public UploadReportPage clickImportKatalonBtn() {
		WebUI.click(xpath('(//button[@type="submit"])[2]'))
		return this
	}


	public UploadReportPage verifyUploadReportSuccess() {
		WebUI.verifyElementVisible(cls('toast-message'))
		return this
	}
}
