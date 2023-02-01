import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.CustomFieldOptions
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.testmanagement.TestCaseDetailPage
import katalon.testops.testmanagement.TestCasesPage

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
					
'Go to the Test Case detail from the Test Management module'
Page.nav(HeaderBar).clickTests()

'Go to Test Case Detail Page'
Page.nav(TestCasesPage).clickUploadedDataFolder()
					   .clickSubFolder('PearTest')
					   .clickTestCaseName(testcase)
					   
'Verify custom field is emplty information'
Page.nav(TestCaseDetailPage).verifyCustomFieldInfoIsEmpty()

'Click on the edit test case'
Page.nav(TestCaseDetailPage).clickEditTestcase()

'Click Add custom fields'
Page.nav(TestCaseDetailPage).clickAddNewCustomField()

'Verify custom field value field and assign button are disabled'
Page.nav(TestCaseDetailPage).verifyCustomFieldValueFieldIsDisabled()
							.verifyAssignCustomFieldButtonIsDisabled()
'Click cancle button'
Page.nav(TestCaseDetailPage).clickCancelAddCustomField()

'Click Add custom fields button'
Page.nav(TestCaseDetailPage).clickAddNewCustomField()

'Input valid a custom field value => click assign button'
Page.nav(TestCaseDetailPage).selectCustomFieldDisplayName(customFieldInfo.displayName)
							.selectCustomFieldDisplayValue(customFieldInfo.customFieldOptions.get(0).value)
							.clickAssignCustomField()
							
'Verify the newly added custom field cannot searchable'
Page.nav(TestCaseDetailPage).clickAddNewCustomField()
							.inputCustomFieldDisplayName(customFieldInfo.displayName)
							.verifyDisplayNameNotPresent(customFieldInfo.displayName)
							.verifyCustomFieldValueFieldIsDisabled()
							.verifyAssignCustomFieldButtonIsDisabled()

'Back to test case detail page without click save edit test case'
Page.nav(HeaderBar).back()


'Verify custom field is emplty information'
Page.nav(TestCaseDetailPage).verifyCustomFieldInfoIsEmpty()
