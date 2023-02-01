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
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.services.ExecutionsService

'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Navigate to Report - Test Runs - Test Runs page'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateTestRuns()
Page.nav(TestRunsPage).navigateTestRuns()

'Verify all items in Test Runs Page is map with mockup'
Page.nav(TestRunsPage).verifySearchStringIsVisible()
						.verifyTimeRangeClickable()
						.verifyReleaseFilterClickable()
						.verifyProfileFilterClickable()
						.verifyCustomFieldFilterClickable()
						.verifyTagFilterClickable()
						.verifyAddMoreButtonClickable()
						.verifyChartIsVisible()
						.verifyBoardIsVisible()

