package katalon.fw.lib

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.common.SignInPage

public class BasePage <T> extends BaseElement {
	WebDriver driver = DriverFactory.getWebDriver()
	/**
	 * Try to stop a little time
	 * Return page object class
	 */
	protected T sleepALittleTime() {
		WebUI.delay(GlobalVariable.smallSleepTime)
		return this
	}

	protected WebDriver getDriver() {
		return driver;
	}

	/**
	 * Try to stop a some time, it is longer than sleepALittleTIme
	 * Return page object class
	 */
	protected T sleepSomeTime() {
		WebUI.delay(GlobalVariable.sleepSomeTime)
		return this
	}

	/**
	 * Try to stop a some time, it is longer than sleepSomeTime 10s
	 * Return page object class
	 */
	protected T sleepTime() {
		WebUI.delay(GlobalVariable.sleepSomeTime+10)
	}

	public T navigateToUrl(String url) {
		WebUI.navigateToUrl(url)
		return this
	}

	protected T refreshPage() {
		WebUI.refresh()
		return this
	}

	protected T back() {
		WebUI.back()
		return this
	}

	protected clearTextAndSendKeys(TestObject to, String value) {
		WebUI.clearText(to)
		WebUI.sendKeys(to, value)
	}

	protected clearTextAndSendKeysByActions(TestObject to, String value) {
		WebDriver driver = DriverFactory.getWebDriver()
		By by
		TestObjectProperty property = to.activeProperties.first()

		switch (property.name) {
			case 'id':
				by = By.id(property.value)
				break

			default:
				by = By.xpath(property.value)
				break
		}

		WebElement el = driver.findElement(by)
		new Actions(driver)
				.click(el)
				.sendKeys(Keys.END)
				.keyDown(Keys.SHIFT)
				.sendKeys(Keys.HOME)
				.keyUp(Keys.SHIFT)
				.sendKeys(Keys.BACK_SPACE)
				.sendKeys(value)
				.perform()
	}

	protected scrollToAndSendKeys(TestObject to, String value) {
		WebUI.scrollToElement(to, 0)
		WebUI.clearText(to)
		WebUI.sendKeys(to, value)
	}

	protected scrollToAndClick(TestObject to) {
		WebUI.scrollToElement(to, 0)
		WebUI.click(to)
	}

	protected boolean verifyElementNotVisible(TestObject to) {
		try {
			WebUI.verifyElementNotVisible(to)
		} catch (StepFailedException e) {
			return true
		}
		return false
	}

	protected boolean verifyTextPresent(String text, boolean isRegex = false) {
		return WebUI.verifyTextPresent(text, isRegex)
	}

	protected List<TestObject> findTestObjects(String locator){
		List<WebElement> els = driver.findElements(By.xpath(locator))
		List<TestObject> listTO = new ArrayList<>();
		for (WebElement element: els) {
			TestObject convertedTO = WebUI.convertWebElementToTestObject(element);
			listTO.add(convertedTO);
		}
		return listTO;
	}

	protected click(TestObject to) {
		WebUI.waitForElementPresent(to, 10, FailureHandling.OPTIONAL)
		WebUI.click(to,FailureHandling.CONTINUE_ON_FAILURE)
	}
}
