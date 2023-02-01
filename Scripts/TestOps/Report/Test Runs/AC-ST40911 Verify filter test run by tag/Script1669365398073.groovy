import java.util.stream.Collectors

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.report.ReportPage
import katalon.testops.report.TestRunsPage
import katalon.testops.services.ExecutionsService
							
'PRE-CONDITON: Assign new tag to Test Run'
BodySearch searchTRBody = new BodySearch('Execution',
								[new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)])
Object testRunInfo = Page.nav(SearchService)
							.search(searchTRBody)
							.parseResponseBodyToJsonObject()					
testRunId = testRunInfo.content[0].id.toString()

'PRE-CONDITON: Create new tag'
Object tag = Page.nav(ExecutionsService).generateTagBody()
addTagResponseBody = Page.nav(ExecutionsService)
							.addTag(testRunId , tag)
							.parseResponseBodyToJsonObject()
							
'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Filter Test run by tag on Report page'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)

Page.nav(HeaderBar).clickReports()

Page.nav(ReportPage).navigateTestRuns()

Page.nav(TestRunsPage)
		.navigateTestRuns()
		.clickTag()
		.inputTag(tag.name)
		.clickUpdateBtn()

'Verify the test run has added tag is shown in the result'
List<ConditionSearch> conditions = [new ConditionSearch("Project.id", "=", GlobalVariable.platformGenaralProjectId),
									new ConditionSearch("Tag.id", "=", addTagResponseBody.id)]
									
BodySearch searchBody = new BodySearch("Execution", conditions)
def searchResponseBody = Page.nav(SearchService).search(searchBody).parseResponseBodyToJsonObject()

List<String> actualResult = searchResponseBody.content.stream()
												.map{r -> r.id.toString()}
												.collect(Collectors.toList())

WS.verifyEqual(actualResult, [testRunId])