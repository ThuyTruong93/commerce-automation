package katalon.testops.visualtesting

import org.junit.After
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage
import katalon.utility.CommonUtility

public class VisualBaselineCollectionPage extends BasePage<VisualBaselineCollectionPage> {
	int random1, random2
	def common = new CommonUtility()
	def pageElements = [xpath_baselineCollectionId: "//a[contains(@href,'/team/143841/project/158146/baseline-collection/')]",
		xpath_properties_pixel: "//label[contains(text(),'Pixel Sensitivity')]",
		xpath_properties: "//label[@class='title mb-0']",
		cls_pixel_sensitivity: "MuiSlider-colorPrimary MuiSlider-sizeSmall MuiSlider-root MuiSlider-marked css-r7zp8",
		cls_default_comparison: "react-select__control css-yk16xz-control",
		cls_current_comparison: "react-select__single-value css-1uccc91-singleValue",
		id_pixel_comparison: "react-select-2-option-0",
		id_layout_comparison: "react-select-2-option-1",
		xpath_save_changes: "//button[@data-trackid='page-visual-testing-save']",
		id_btn_create_baseline_collection: "create-baseline-collection-btn",
		xpath_pixel_sensity_slider: "//span[contains(@class,'MuiSlider-markLabel')]/preceding-sibling::span[1]",
		xpath_baseline_image_title: "//div[@class='baseline-image-title']/div[@class='title']",
		id_upload_baseline_images_btn: "upload-baseline-images-button",
		css_upload_baseline_images_icon: "div.ml-auto.d-flex.align-items-center > svg.mr-2 > path",
		xpath_upload_file_dialog: "//*[@id='upload-baseline-images-dialog']",
		xpath_upload_images_dialog: "//*[@id='upload-baseline-images-dialog']",
		xpath_default_comparison_method: "//*[@id='react-select-2']",
		xpath_title_baseline_image: "//div[@class='title' and contains(text(),'Baseline Images')]"
	]

	def pixel_sensitivity= [
		"//*[@id='properties']/div[7]/div[1]/label",
		"Pixel Sensitivity"
	]
	def comparisonMethod = ['pixel', 'layout']
	def upload_image_dialog = "//*[@role='dialog']/form//input[@type='file']"

	public VisualBaselineCollectionPage verifyTextIsVisible(String texts) {
		WebUI.verifyElementVisible(text(texts))
		return this
	}

	public VisualBaselineCollectionPage selectLatestBaselineCollection() {
		TestObject to = xpath(pageElements['xpath_baselineCollectionId']) //6
		List<WebElement> listofBaselineCollectionID = WebUI.findWebElements(to, 10)
		listofBaselineCollectionID.get(0).click();
		return this
	}

	public VisualBaselineCollectionPage selectDefaultComparisonMethod() {
		click(cls(pageElements['cls_default_comparison']))
		def getCurrentDefaultMethod = WebUI.getText(cls(pageElements['cls_current_comparison']))
		if (getCurrentDefaultMethod.toLowerCase() == comparisonMethod.first()) {
			click(id(pageElements['id_layout_comparison']))
		} else {
			click(id(pageElements['id_pixel_comparison']))
		}
		return this
	}

	public VisualBaselineCollectionPage clickSaveChange() {
		click(xpath(pageElements['xpath_save_changes']))
		return this
	}

	public VisualBaselineCollectionPage isSaveChangedSuccess() {
		def btn_saveChange = xpath(pageElements['xpath_save_changes'])
		WebUI.verifyElementHasAttribute(btn_saveChange, 'disable', 3)
	}

	public VisualBaselineCollectionPage verifyPixelSensitivityComponent() {
		def result = false;
		List<TestObject> listTO = findTestObjects(pageElements['xpath_properties'])
		for(TestObject to : listTO) {
			if(WebUI.getText(to) == 'Pixel Sensitivity') {
				result = true
				break
			}
		}
		WebUI.verifyEqual(result, true)
		WebUI.verifyElementPresent(cls(pageElements['cls_pixel_sensitivity']), 0)
		return this
	}

	public VisualBaselineCollectionPage createBaselineCollection() {
		click(id(pageElements['id_btn_create_baseline_collection']))
		return this
	}

	public VisualBaselineCollectionPage verifyCreateBaselineCollectionBtn(String attribute) {
		def btn_CreateBaselineCollectionBtn = id(pageElements['id_btn_create_baseline_collection'])
		WebUI.verifyElementHasAttribute(btn_CreateBaselineCollectionBtn, attribute,3)
		return this
	}

	public VisualBaselineCollectionPage randomSelectPixelSensitive() {
		random1 = new Random().nextInt(4)
		random2 = new Random().nextInt(4 - random1)
		List<TestObject> slider = findTestObjects(pageElements['xpath_pixel_sensity_slider'])
		click(slider.get(random1))
		click(slider.get(random2))
		click(xpath(pageElements['xpath_save_changes']))
		return this
	}

	def String getCurrentVersion() {
		String baselineCollectionLatestVersion = WebUI.getText(xpath(pageElements['xpath_baseline_image_title']))
		return common.substringUseRegExp(baselineCollectionLatestVersion, "\\d+")
	}

	public VisualBaselineCollectionPage selectPointPixelSensivity(int index) {
		List<TestObject> slider = findTestObjects(pageElements['xpath_pixel_sensity_slider'])
		click(slider.get(0))
		click(slider.get(4))
		WebUI.delay(2)
		return this
	}

	public VisualBaselineCollectionPage verifyUploadImagesDisableAndDisplaySign() {
		WebUI.verifyElementNotClickable(id(pageElements['id_upload_baseline_images_btn']))
		WebUI.mouseOver(css(pageElements['css_upload_baseline_images_icon']))
		WebUI.verifyTextPresent("Please save all changes before uploading new image(s).", false)
		return this
	}

	public VisualBaselineCollectionPage verifyChangePixelSaveChangesEnables() {
		selectPointPixelSensivity(0)
		selectPointPixelSensivity(4)
		WebUI.verifyElementClickable(xpath(pageElements['xpath_save_changes']))
		return this
	}

	// Codebase a Minh
	public VisualBaselineCollectionPage verifyUploadImageDialogDisplayAndDisableSaveChange() {
		WebUI.verifyElementVisible(xpath(pageElements['xpath_upload_file_dialog']))
		return this
	}

	public VisualBaselineCollectionPage verifyNotContainText(String cssText, String findText) {
		TestObject element = css(cssText)
		String c = WebUI.getText(element)
		WebUI.verifyEqual(c.contains(findText), false)
		return this
	}

	// new code
	// TODO: need a Minh update test case use this method instead verifyNotContainText
	public VisualBaselineCollectionPage verifyContainText(String cssText, String findText,boolean isContain) {
		TestObject element = css(cssText)
		String c = WebUI.getText(element)
		WebUI.verifyEqual(c.contains(findText), isContain)
		return this
	}

	public VisualTestRunPage verifyDisableDropDown() {
		WebUI.delay(2)
		TestObject element = id('react-select-2')
		String c = WebUI.getAttribute(element, 'class')
		WebUI.verifyEqual(c.contains('react-select--is-disabled'), false)
		return this
	}

	public VisualTestRunPage verifyDisableDropDownComparisonMethod() {
		WebUI.verifyElementNotClickable(xpath("//div[@id='properties']/div[6]/div[2]"))
		return this
	}

	public VisualBaselineCollectionPage verifyUpdateVersionChangeComparisonMethod() {
		Integer old = getBaselineVersion()
		changeDefaultComparisonMethod()
		return this
	}

	public VisualBaselineCollectionPage verifyDefaultComparisonMethod() {
		WebUI.verifyElementText(xpath("//*[@id='properties']/div[6]/div[1]/label"), "Default Comparison Method")
		return this
	}

	public VisualBaselineCollectionPage verifyTextContent(String xpathText, String textContent) {
		TestObject element = xpath(xpathText)
		String c = WebUI.getText(element)
		WebUI.verifyEqual(c, textContent)
		return this
	}

	public VisualBaselineCollectionPage verifyTextPixelSensitivity() {
		verifyTextContent(pixel_sensitivity[0], pixel_sensitivity[1])
		return this
	}

	public VisualBaselineCollectionPage changeDefaultComparisonMethod() {
		String textComparison = WebUI.getText(xpath("//div[@id='react-select-2']"))
		if (textComparison == "Layout")
			WebUI.click(xpath(pageElements['xpath_default_comparison_method']))
		WebUI.delay(1)
		WebUI.click(xpath("//div[@id='react-select-2']"))
		WebUI.click(text("Pixel"))
		if (textComparison == "Pixel")
			WebUI.click(xpath(pageElements["xpath_default_comparison_method"]))
		WebUI.delay(1)
		WebUI.click(xpath(pageElements["xpath_default_comparison_method"]))
		WebUI.click(text("Layout"))
		return this
	}

	public Integer getBaselineVersion() {
		String title = WebUI.getText(xpath(pageElements['xpath_title_baseline_image']))
		Integer version = title.findAll( /\d+/ ).first().toInteger()
		return version
	}



}