package katalon.fw.lib

import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.events.AbstractWebDriverEventListener


public class KEventHandler extends AbstractWebDriverEventListener{

	@Override
	public void beforeAlertAccept(WebDriver driver) {
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		println "Navigating to ============ " + url;
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		println "Completed navigate to ============ " + url;
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		println "Clicking on ============ " + element.toString();
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		println "Completed click on ============ " + element.toString();
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		println "Sending keys to ============ " + element.toString();
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		println "Completed send keys to ============ " + element.toString();
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
	}
}
