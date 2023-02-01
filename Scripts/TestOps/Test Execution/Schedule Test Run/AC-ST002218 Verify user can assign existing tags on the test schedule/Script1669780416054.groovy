import groovy.json.JsonSlurper
import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.services.TagService
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage


'Pre-condition create new Tag'
def lstTags = new ArrayList<Object>()
JsonSlurper slurper = new JsonSlurper()
listTags = slurper.parseText(listTags)
for(def tag: listTags) {
	tag = tag + System.currentTimeMillis()
	Object requestBody = ['name': tag,
		'projectId': GlobalVariable.platformGenaralProjectId,
		'organizationId': GlobalVariable.defaultOrg
]

addTagResponseBody = Page.nav(TagService)
						 .create(requestBody)
						 .parseResponseBodyToJsonObject()
lstTags.add(addTagResponseBody)
}


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

for(tag in lstTags) {
	'User click on Tag input field'
	Page.nav(NewUIScheduleTestRunPopUp).clickTagInputField()
	
	'Verify the user can search and assign existing tags to Test Schedule and Tag is visible'
	Page.nav(NewUIScheduleTestRunPopUp).searchExistingTagAndAssign(tag.name).verifyTagIsVisible(tag.name)
}
