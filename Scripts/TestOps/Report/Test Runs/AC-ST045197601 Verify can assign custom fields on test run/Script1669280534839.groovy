import groovy.json.JsonSlurper
import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.CustomFieldOptions
import katalon.testops.report.TestRunDetailPage
import katalon.testops.report.TestRunsPage
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.testmanagement.TestCaseDetailPage

'Pre-condition: Create custom fields'
def lstCustomFields = new ArrayList<Object>()

JsonSlurper slurper = new JsonSlurper()
listCustomFieldKeys = slurper.parseText(listCustomFieldKeys)

for (String key: listCustomFieldKeys) {
	Object customFieldInfo = Page.nav(CustomFieldDefinitionService).search(GlobalVariable.platformGenaralProjectId, key, 'key')
																   .parseResponseBodyToJsonObject()
	if(customFieldInfo.content != []) {
		Page.nav(CustomFieldDefinitionService).delete("${customFieldInfo.content.get(0).id}", GlobalVariable.platformGenaralProjectId)
	}
	
	Object requestBody = [
				"key": key, "displayName" : "${key} Display Name",
				"projectId": GlobalVariable.platformGenaralProjectId,
				"customFieldOptions": [
					new CustomFieldOptions(value: "${key} Value")]
			]
	customFieldInfo = Page.nav(CustomFieldDefinitionService).create(requestBody).parseResponseBodyToJsonObject()
	lstCustomFields.add(customFieldInfo)
}

'Login into TestOps'
Page.nav(SignInPage).enterCredential()
					.clickSignIn()
					
'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)
					
'Go to the Report > Test Run > Test Runs page'
Page.nav(HeaderBar).clickReports()
Page.nav(TestRunsPage).navigateTestRuns()

'Navigate to test run detail page'
Page.nav(TestRunsPage).filterTestSuite(testSuite)
					  .clickOnTestRun(testRun)

'Add custom fields'
for (customField in lstCustomFields) {
	Page.nav(TestRunDetailPage).clickAddNewCustomField()
								.selectCustomFieldDisplayName(customField.displayName)
								.selectCustomFieldDisplayValue(customField.customFieldOptions.get(0).value)
								.clickAssignCustomField()
}

'Verify custom field are assigned to test test run succesffuly'
for (int i = 0; i < lstCustomFields.size(); i++) {
	Page.nav(TestRunDetailPage).verifyCustomFieldDisplay(lstCustomFields[i].displayName, lstCustomFields[i].customFieldOptions.get(0).value, i+1)
}