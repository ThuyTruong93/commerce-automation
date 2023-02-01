
package katalon.fw.lib

/*
 * This class leverage Singleton design pattern in 2 forms (Lazy & Early Instantiation)
 * 	Early Instantiation means 'creation of instance at load time'
 * 	Lazy Instantiation means 'creation of instance when required'
 * With this design help to save memory because object is not created at each request. Only single instance is reused again and again
 * Singleton pattern is mostly used in multi-threaded
 * 
 */
//import org.openqa.selenium.support.events.EventFiringWebDriver

import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

/***
 * This class control page object class's instantiation and thread safe
 * @version 1.0.0 Sep 2022
 * @author initial by hong.le 
 *
 */

public class Page {

	private static final Page instance = new Page();
	private static final EventFiringWebDriver eventFiring = GlobalVariable.isAPIRunning ? null : ((DriverFactory.getWebDriver()) as EventFiringWebDriver).register(new KEventHandler());

	private Map<Class,Object> map = new HashMap<Class,Object>();
	/***
	 *
	 * @param <T> page object class . Ex - LoginPage | AccountPage
	 * @param classOf - page object class . Ex - LoginPage | AccountPage
	 * @return a Page instance
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static  <T> T nav(Class<T> classOf) throws InstantiationException, IllegalAccessException {

		synchronized(instance){
			if(!instance.map.containsKey(classOf)){

				T obj = classOf.newInstance();

				instance.map.put(classOf, obj);
			}
			return (T)instance.map.get(classOf)
		}
	}

	/****
	 * This for take screenshot purpose, if isScreenShort turns off, no screenshot step is performed
	 * For evidence, each screenshot should not duplicated
	 * @return
	 */
	public static takeScreenShot() {
		if (GlobalVariable.isCapture) {
			WebUI.takeScreenshot(FailureHandling.CONTINUE_ON_FAILURE)
		}
	}
}
