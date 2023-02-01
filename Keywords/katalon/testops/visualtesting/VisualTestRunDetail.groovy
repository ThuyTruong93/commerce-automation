package katalon.testops.visualtesting

import java.util.stream.IntStream

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage
import katalon.utility.CommonUtility



public class VisualTestRunDetail extends BasePage<VisualTestRunDetail> {
	enum StatusImage{
		MISMATCH,
		MISSING,
		NEW
	}
	def common = new CommonUtility()	
	def pageElement = [		
		xpath_mismatch_thumbnails: '//div[@class="thumbnail unresolved"]/div[2]/div/div/span[contains(text(),"mismatch")]',
		xpath_missing_thumbnails: '//div[@class="thumbnail unresolved"]/div[2]/div/div/span[contains(text(),"missing")]',
		xpath_new_thumbnails: '//div[@class="thumbnail unresolved"]/div[2]/div/div/span[contains(text(),"missing")]',
		id_comparison_method: '//label[contains(text(),"Comparison method")]/following-sibling::div[1]',
		xpath_pixel_sensity_slider: "//span[contains(@class,'MuiSlider-markLabel')]/preceding-sibling::span[1]",
		xpath_diff_count: "//label[contains(@class,'checkpoint-comparison__diff-count')]"
	]
	
	List<Integer> diffCount = new ArrayList<>();
	
	public VisualTestRunDetail clickToImageWithStatus(String thumbnailStatus) {
		WebUI.delay(2)
		switch(StatusImage.valueOf(thumbnailStatus)) {
			case "MISMATCH":
				click(xpath(pageElement['xpath_mismatch_thumbnails']))
				break;
			case "MISSING":
				click(xpath(pageElement['xpath_missing_thumbnails']))
				break;
			case "NEW":
				click(xpath(pageElement['xpath_new_thumbnails']))
				break;
			default:
				break;
		}
		return this
	}

	public VisualTestRunDetail selectComparisonMethod(String method) {
		WebUI.delay(2)
		click(xpath(pageElement['id_comparison_method']))
		
		String dropdownOption = pageElement.get('id_comparison_method') + "//div[text()=\"${method}\"]"
		TestObject option = xpath(dropdownOption) 
		WebUI.delay(2)
		click(option)
		return this
	}
	
	/**
	 * Base on level we want to change we will create the list of Diff-count base on it
	 * Diff-count will be decrease by each level
	 * The range of level from 0 - > 4 (strict -> relaxed)
	 * @param level - how many number you want to change 
	 * @return 
	 */
	public VisualTestRunDetail changePixelSensitivity(int level) {
		List<TestObject> slider = findTestObjects(pageElement['xpath_pixel_sensity_slider'])
		for(int i = 1; i <= level; i++) {
			click(slider.get(i))
			int tempLevel = getDiffCount()
			diffCount.add(tempLevel)
		}
		return this
	}
	
	public VisualTestRunDetail isDiffCountDecreased() {
		boolean result = streamDiffCount()
		WebUI.verifyEqual(result, true)
		return this
	}

	def int getDiffCount() {
		String diffCountMatchLevel = WebUI.getText(xpath(pageElement['xpath_diff_count']))
		return common.substringUseRegExp(diffCountMatchLevel, "\\d+") as Integer
	}
	
	/** 
	 * less than 0 that means diff count decrease from strict to relaxed
	 */
	def boolean astreamDiffCount()  {
		return IntStream.range(0, diffCount.size() - 1)
			.allMatch({i ->
				i.compareTo(i+1) < 0})
	}
	
}

