import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.CustomFieldOptions
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.testmanagement.TestCaseDetailPage
import katalon.testops.testmanagement.TestCasesPage
import groovy.json.JsonSlurper

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
					
'Go to the Test Case detail from the Test Management module'
Page.nav(HeaderBar).clickTests()

'Go to Test Case Detail Page'
Page.nav(TestCasesPage).clickUploadedDataFolder()
					   .clickSubFolder('PearTest')
					   .clickTestCaseName(testcase)

'Click on the edit test case'
Page.nav(TestCaseDetailPage).clickEditTestcase()

'Add custom fields'
for (customField in lstCustomFields) {
	Page.nav(TestCaseDetailPage).clickAddNewCustomField()
								.selectCustomFieldDisplayName(customField.displayName)
								.selectCustomFieldDisplayValue(customField.customFieldOptions.value)
								.clickAssignCustomField()
}

Page.nav(TestCaseDetailPage).clickSaveUpdateTestCase()

'Verify custom field are assigned to test cases succesffuly'
for (int i = 0; i < lstCustomFields.size(); i++) {
	Page.nav(TestCaseDetailPage).verifyCustomFieldDisplay(lstCustomFields[i].displayName, lstCustomFields[i].customFieldOptions.get(0).value, i+1)
}