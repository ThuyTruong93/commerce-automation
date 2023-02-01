package katalon.my.supportmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class SupportManagementPage extends BasePage<SupportManagementPage>{
	
	public SupportManagementPage verifyUISupportManagement ()
	{
		WebUI.verifyElementPresent(xpath("//h2[text()='Support Management']"), 5)
		def textContentLineOne = WebUI.getText(xpath("(//h2[text()='Support Management']//following::p)[1]"))
		def textContentLineTwo = WebUI.getText(xpath("(//h2[text()='Support Management']//following::p)[2]"))
		WebUI.verifyEqual(textContentLineOne, "We provide support about license issues via our CRM platform, Salesforce.")
		WebUI.verifyEqual(textContentLineTwo, "Only the selected members can submit a ticket to Katalon Help Center.")
		WebUI.verifyElementPresent(text("Support Summary"), 5)
		WebUI.verifyElementPresent(text("Quota"), 5)
		WebUI.verifyElementPresent(text("Available slots"), 5)
		WebUI.verifyElementPresent(text("Assigned slots"), 5)
		WebUI.verifyElementPresent(text("Select the members to assign supported slots."), 5)
		WebUI.verifyElementPresent(xpath("//*[@placeholder='Filter by email or user name']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Full Name']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Email']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Assign Salesforce Slots']"), 5)
		return this
	}
}
