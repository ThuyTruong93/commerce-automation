import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.context.TestCaseContext

import groovy.json.JsonOutput
import katalon.fw.lib.Page
import katalon.my.account.AccountSettingPage
import katalon.my.account.DeleteAccountPopUp
import katalon.services.ProjectService
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.services.ExecutionsService
import katalon.testops.services.ProjectService as toProject
import katalon.testops.services.TestCaseService

class DeleteDataAfterTest {
	
//	@AfterTestCase
//	def deleteAccount() {
//		String currentTestCaseId = GlobalVariable.currentTestCaseName.toString().tokenize(' ').first()
//		List<String> listTC = ['AC-ST01344','AC-ST013414'];
//		if(!listTC.contains(currentTestCaseId)) return
//		Page.nav(AccountSettingPage).clickDeleteAccount()
//		Page.nav(DeleteAccountPopUp).inputAccountName().clickDeleteThisAccount().sleepALittleTime()
//	}
	
	@AfterTestCase
	def deleteCustomField(TestCaseContext testCaseContext) {
//		println "show error"
//		long id = testCaseContext.getTestCaseVariables().get("customFieldId")
//		println "exit error"
//		if(id) {
//			def response = Page.nav(CustomFieldDefinitionService).delete(id)
//			if (response.getResponseStatusCode() == 200) {
//				println('Delete Custom Field success!!! CustomFieldId: ' + id)
//			} else {
//				println('Delete Custom Field faild: ' + JsonOutput.prettyPrint(JsonOutput.toJson(response.response)))
//			}
//		}
	}
	
	@AfterTestCase
	def removeTagFromTestCase(TestCaseContext testCaseContext) {
		def body = testCaseContext.getTestCaseVariables().get("addTagResponseBody")
		if(body) {
			
			def response;
			
			if(testCaseContext.getTestCaseVariables().get("testCaseId")) {
				response = Page.nav(TestCaseService).deleteTag(testCaseContext.getTestCaseVariables().get("testCaseId"), body)
			}
			
			if(testCaseContext.getTestCaseVariables().get("testRunId"))
			{
				response = Page.nav(ExecutionsService).deleteTag(testCaseContext.getTestCaseVariables().get("testRunId"), body)
			}
			
			if (response.getResponseStatusCode() == 200) {
				println('Remove tag success!!! testCaseId: ' + testCaseId)
			} else {
				println('Remove tag faild: ' + JsonOutput.prettyPrint(JsonOutput.toJson(response.response)))
			}
		}
	}
	
	@AfterTestCase
	def deleteProject(TestCaseContext testCaseContext) {
//		long projectId = testCaseContext.getTestCaseVariables().get("projectId")
//		if(projectId) {
//			def response = Page.nav(ProjectService).delete(projectId)
//			if (response.getResponseStatusCode() == 200) {
//				println('Delete project success!!! projectId: ' + projectId)
//			} else {
//				println('Delete project faild: ' + JsonOutput.prettyPrint(JsonOutput.toJson(response.response)))
//			}
//		}
	}
}