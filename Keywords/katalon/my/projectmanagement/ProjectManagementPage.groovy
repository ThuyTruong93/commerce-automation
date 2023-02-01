package katalon.my.projectmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class ProjectManagementPage extends BasePage<ProjectManagementPage> {

	public ProjectManagementPage clickActionButtonOfProject(int orderNum=1) {
		WebUI.click(xpath("//tbody/tr[$orderNum]/td[3]//button[1]"))
		return this
	}

	public ProjectManagementPage clickEditProjectName() {
		WebUI.click(text("Edit project name"))
		return this
	}

	public ProjectManagementPage verifyProjectName(String expectedName, int orderNum=1)  {
		WebUI.verifyElementText(xpath("//tbody/tr[$orderNum]/td[1]"), expectedName)
		return this
	}
}
