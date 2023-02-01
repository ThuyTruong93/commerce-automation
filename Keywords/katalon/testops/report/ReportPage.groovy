package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class ReportPage extends BasePage<ReportPage>{

	public ReportPage navigateTestCases() {
		WebUI.click(title('Test Cases'))
		return this
	}

	public ReportPage navigateTestRuns() {
		WebUI.click(title('Test Runs'))
		return this
	}

	public ReportPage navigateDefects() {
		WebUI.click(title('Defects'))
		return this
	}

	public ReportPage navigateUploadReport() {
		WebUI.click(title('Upload Reports'))
		return this
	}
}
