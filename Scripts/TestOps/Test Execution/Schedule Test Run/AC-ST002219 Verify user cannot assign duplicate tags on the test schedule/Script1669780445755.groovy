import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.services.TagService
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage


'Pre-condition: Create Tags'
tag = tag + System.currentTimeMillis()
	Object requestBody = ['name': tag,
		'projectId': GlobalVariable.platformGenaralProjectId,
		'organizationId': GlobalVariable.defaultOrg
]

addTagResponseBody = Page.nav(TagService)
						 .create(requestBody)
						 .parseResponseBodyToJsonObject()
						 
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential()
					.doCheckRemberMe()
					.clickSignIn()

'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)

'User click on Execution module'
Page.nav(HeaderBar).clickExecutions()

'User click on Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User click on Advanced settings hyperlink'
Page.nav(NewUIScheduleTestRunPopUp).clickAdvancedSettings()

'Input valid tag => assign tag'
Page.nav(NewUIScheduleTestRunPopUp).clickTagInputField()
								   .searchExistingTagAndAssign(tag)
								   
'Verify the newly added tag cannot searchable'
Page.nav(NewUIScheduleTestRunPopUp).clickTagInputField()
								   .searchExistingTagAndAssign(tag)
								   .verifyTagNotPresent(tag)