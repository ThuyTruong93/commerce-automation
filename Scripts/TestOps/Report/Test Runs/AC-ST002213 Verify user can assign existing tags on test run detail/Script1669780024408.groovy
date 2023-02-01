import groovy.json.JsonSlurper
import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.report.TestRunsPage
import katalon.testops.services.TagService

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
					
'At Home page, users select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)

'User click on Reports module'
Page.nav(HeaderBar).clickReports()

'User click on Test Runs tab'
Page.nav(TestRunsPage).navigateTestRuns()

'Navigate to test run detail page'
Page.nav(TestRunsPage).filterTestSuite(testSuite)
					  .clickOnTestRun(testRun)

for(tag in lstTags) {
	'User click on Tag input field'
	Page.nav(TestRunDetailPage).clickTagsInputField()
	'Verify the user can search and assign existing tags to Test Runs and Tag is visible'
	Page.nav(TestRunDetailPage).searchExistingTagAndAssign(tag.name).verifyTagIsVisible(tag.name)
}
