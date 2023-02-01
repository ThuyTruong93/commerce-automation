package katalon.testops.testexecution

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class SessionDetailsPage extends BasePage<SessionDetailsPage> {
	public SessionDetailsPage verifyGenericCommandDisplayed(String command) {
		WebUI.verifyElementText(id('genericCommand'), command)
		return this
	}
}
