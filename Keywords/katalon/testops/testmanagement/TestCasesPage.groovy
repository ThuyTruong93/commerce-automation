package katalon.testops.testmanagement

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestCasesPage extends BasePage<TestCasesPage> {
	// SEARCH
	public TestCasesPage openMultiSearchPopup() {
		WebUI.click(xpath('//input[@id="search-input"]/following-sibling::div[1]'))
		return this
	}

	public TestCasesPage searchTestCase (String testCaseName) {
		clearTextAndSendKeys(id('search-input'), testCaseName)
		WebUI.sendKeys(id('search-input'), Keys.chord(Keys.ENTER))
	}

	public TestCasesPage verifyTestCaseIsVisible(String testcase) {
		WebUI.verifyElementVisible(title(testcase))
		return this
	}

	public TestCasesPage verifyTheTotalOfTestCase(String text) {
		WebUI.verifyElementText(xpath('//div[@class="pagination-item-count"]'), text)
		return this
	}

	// FOLDER
	public TestCasesPage clickUploadedDataFolder() {
		WebUI.click(id("root_uploaded_data"))
		return this
	}

	public TestCasesPage clickSubFolder(String folderName) {
		WebUI.click(text(folderName))
		return this
	}

	public TestCasesPage clickTestCaseName(String testCaseName) {
		WebUI.click(title(testCaseName))
	}


}