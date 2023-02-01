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

'Pre-condition: Create custom fields'
Object customFieldInfo = Page.nav(CustomFieldDefinitionService).search(GlobalVariable.platformGenaralProjectId, key, 'key')
															   .parseResponseBodyToJsonObject()
if(customFieldInfo.content != []) {
	Page.nav(CustomFieldDefinitionService).delete("${customFieldInfo.content.get(0).id}", GlobalVariable.platformGenaralProjectId)
}

Object requestBody = [
	"key": key, "displayName" : "${key} Display Name",
	"projectId": GlobalVariable.platformGenaralProjectId,
	"customFieldOptions": [
			new CustomFieldOptions(value: "${key} Value 01"),
			new CustomFieldOptions(value: "${key} Value 02"),
			new CustomFieldOptions(value: "${key} Value 03")
		]
]
customFieldInfo = Page.nav(CustomFieldDefinitionService).create(requestBody).parseResponseBodyToJsonObject()

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
					  
'Verify custom field is emplty information'
Page.nav(TestRunDetailPage).verifyCustomFieldInfoIsEmpty()
					  
'Verify custom field value field and assign button are disabled'
Page.nav(TestRunDetailPage).clickAddNewCustomField()
						   .verifyCustomFieldValueFieldIsDisabled()
						   .verifyAssignCustomFieldButtonIsDisabled()
'Click cancle button'
Page.nav(TestRunDetailPage).clickCancelAddCustomField()
  
'Click Add custom fields button'
Page.nav(TestRunDetailPage).clickAddNewCustomField()
  
'Input valid a custom field value => click assign button'
Page.nav(TestRunDetailPage).selectCustomFieldDisplayName(customFieldInfo.displayName)
							  .selectCustomFieldDisplayValue(customFieldInfo.customFieldOptions.get(0).value)
							  .clickAssignCustomField()
							  
'Verify the newly added custom field cannot searchable'
Page.nav(TestRunDetailPage).clickAddNewCustomField()
							  .inputCustomFieldDisplayName(customFieldInfo.displayName)
							  .verifyDisplayNameNotPresent(customFieldInfo.displayName)
							  .verifyCustomFieldValueFieldIsDisabled()
							  .verifyAssignCustomFieldButtonIsDisabled()

  

