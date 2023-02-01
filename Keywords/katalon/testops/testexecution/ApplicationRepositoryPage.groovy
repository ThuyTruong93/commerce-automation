package katalon.testops.testexecution

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject


import katalon.fw.lib.BasePage


class ApplicationRepositoryPage extends BasePage<ApplicationRepositoryPage> {

	public ApplicationRepositoryPage clickUploadApplication() {
		WebUI.click(btn('Upload Application'))
		return this
	}

	public ApplicationRepositoryPage clickChooseFile() {
		WebUI.click(id('upload-app'))
		return this
	}

	public ApplicationRepositoryPage verìfySupporttext() {
		WebUI.verifyTextPresent('Supported files', false)
		return this
	}

	public ApplicationRepositoryPage selectFileUpload() {
		WebUI.uploadFile(id('upload-app'), RunConfiguration.getProjectDir() + '/androidapp/APIDemos.apk')
		return this
	}

	public ApplicationRepositoryPage clickDeleteApplication() {
		WebUI.click(title('Delete Application'))
		return this
	}

	public ApplicationRepositoryPage clickConfirmDeleteApplication() {
		WebUI.click(btn('Delete'))
		return this
	}

	public ApplicationRepositoryPage verifyDeleteSuccessMessage(String appName) {
		def deleteMsg = 'The application ' + appName +' has been remove'
		WebUI.verifyTextPresent(deleteMsg, false)
		return this
	}

	public ApplicationRepositoryPage placeholderForEmptyList() {
		WebUI.verifyTextPresent('There is no data to display.', false)
		return this
	}
}