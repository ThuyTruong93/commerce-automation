package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class HeaderBar extends BasePage<HeaderBar> {

	public HeaderBar clickDashboard() {
		WebUI.click(title('Dashboard'))
		return this
	}

	public HeaderBar clickPlanning() {
		WebUI.click(title('Planning'))
		return this
	}

	public HeaderBar clickTests() {
		WebUI.click(title('Tests'))
		return this
	}

	public HeaderBar clickExecutions() {
		WebUI.click(title('Executions'))
		return this
	}

	public HeaderBar clickReports() {
		WebUI.click(title('Reports'))
		return this
	}

	public HeaderBar clickVisualTesting() {
		WebUI.click(title('Visual Testing'))
		return this
	}

	public HeaderBar clickConfigurations() {
		WebUI.click(title('Configurations'))
		return this
	}
}
