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
	'Input valid tag => assign tag'
	Page.nav(TestRunDetailPage).clickTagsInputField()
						   	   .searchExistingTagAndAssign(tag.name)
}

for(tag in lstTags) {
	'Unassign Tag'
	Page.nav(TestRunDetailPage).unassignTag(tag.name)
	
	'Verify Tag is unassigned successfully'
	Page.nav(TestRunDetailPage).verifyTagNotPresent(tag.name)
}

