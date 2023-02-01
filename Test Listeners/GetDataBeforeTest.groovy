import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext

import internal.GlobalVariable

class GetDataBeforeTest {
	
	@BeforeTestCase
	def getTestCaseId(TestCaseContext testCaseContext) {
		GlobalVariable.currentTestCaseName = testCaseContext.getTestCaseId().tokenize('/').last()
	}
}