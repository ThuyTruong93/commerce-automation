package katalon.my.productutilization

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class KatalonStudioUtilizationPage extends BasePage<KatalonStudioUtilizationPage> {

	public KatalonStudioUtilizationPage clickKatalonStudioTab() {
		WebUI.click(text("Katalon Studio"))
		return this
	}
	
	public KatalonStudioUtilizationPage verifyUIKatalonStudioUtilizationPage() {
		WebUI.verifyElementPresent(text("License Utilization"), 5)
		WebUI.verifyElementPresent(txt("User"), 5)
		WebUI.verifyElementPresent(txt("Machine ID"), 5)
		WebUI.verifyElementPresent(txt("License"), 5)
		WebUI.verifyElementPresent(btn("Export"), 5)
		WebUI.verifyElementPresent(link("Linear"), 5)
		WebUI.verifyElementPresent(link("Stacked"), 5)
		WebUI.verifyElementPresent(text("KRE Floating"), 5)
		WebUI.verifyElementPresent(text("KRE Node-locked"), 5)
		WebUI.verifyElementPresent(text("KSE Floating"), 5)
		WebUI.verifyElementPresent(text("KSE Node-locked"), 5)
		WebUI.verifyElementPresent(text("KSE per-User"), 5)
		//field datetime picker
		WebUI.verifyElementPresent(xpath("(//*[text()='License']//following::span)[3]"), 5)
		WebUI.verifyElementClickable(xpath("(//*[text()='License']//following::span)[3]"))
		return this
	}
}