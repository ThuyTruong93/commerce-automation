package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class DefectsPage extends BasePage<DefectsPage>{

	public DefectsPage verifyDefectLabelIsVisible() {
		WebUI.verifyElementVisible(xpath('//div[@title= "Defects"]'))
		return this
	}
	
	public DefectsPage verifyJiraIntegrationIsVisible() {
		WebUI.verifyElementVisible(text('Jira Integration'))
		return this
	}
	
	public DefectsPage verifyJiraBtnIsClickable() {
		WebUI.verifyElementClickable(text('Enable Jira Integration'))
		return this
	}
	

}
