package katalon.testops.testexecution

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class TestRunCalendarPage extends BasePage<TestRunCalendarPage> {

	public navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/test-planning")
	}

	public TestRunCalendarPage clickTestRunListTab() {
		WebUI.click(title('Test Run List'))
		return this
	}

	public TestRunCalendarPage clickScheduleTestRun() {
		WebUI.click(btn('Schedule Test Run'))
		return this
	}

	public TestRunCalendarPage clickScheduleSampleTestRun() {
		WebUI.click(btn('Schedule Sample Test Run'))
		return this
	}

	public TestRunListPage clickApplicationRepositoryTab() {
		WebUI.click(title('Application Repository'))
		return this
	}
}
