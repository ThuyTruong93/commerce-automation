package katalon.my.licensemanagement

import katalon.fw.lib.BasePage



public class RemoveUserDialog extends BasePage<RemoveUserDialog>{

	public RemoveUserDialog clickRemove() {
		click(xpath("//form//button[@type='submit']"))
		return this
	}
}
