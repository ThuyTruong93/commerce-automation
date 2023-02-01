import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.report.TestRunsPage
import katalon.testops.services.TagService

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

'At Home page, users select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)

'User click on Reports module'
Page.nav(HeaderBar).clickReports()

'User click on Test Runs tab'
Page.nav(TestRunsPage).navigateTestRuns()

'Navigate to test run detail page'
Page.nav(TestRunsPage).filterTestSuite(testSuite)
					  .clickOnTestRun(testRun)
					  
'Input valid tag => assign tag'
Page.nav(TestRunDetailPage).clickTagsInputField()
							   .searchExistingTagAndAssign(tag)
							   
'Verify the newly added tag cannot searchable'
Page.nav(TestRunDetailPage).clickTagsInputField()
						   .searchExistingTagAndAssign(tag)
						   .verifyTagNotPresent(tag)


