package katalon.my.account

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class DeleteAccountPopUp extends BasePage<DeleteAccountPopUp>{

	public DeleteAccountPopUp inputAccountName() {
		String accountName = WebUI.getText(xpath("(//div[@role='dialog']//span[@style])[1]"))
		WebUI.sendKeys(id("account_setting.delete_account_dialog.account_name.text_field"), accountName)
		return this
	}

	public DeleteAccountPopUp clickDeleteThisAccount() {
		WebUI.verifyElementClickable(id("account_setting.delete_account_dialog.form.button.delete"))
		WebUI.click(id("account_setting.delete_account_dialog.form.button.delete"))
		return this
	}

}
