import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.GitInfo
import katalon.services.ProjectService
import katalon.testops.report.DefectsPage
import katalon.testops.report.ReportPage
import katalon.testops.services.GitService

'Pre-condition: The Admin creates new project'
String projectName = 'Auto ' + UUID.randomUUID().toString().substring(0, 3)
projectId = Page.nav(ProjectService).createProject(projectName, GlobalVariable.turingAndGryffindoorTeamId)
								 	.verifyStatusCode(200).parseResponseBodyToJsonObject().id
									 
'Pre-condition: Setup git repo'
Page.nav(GitService).createGit(new GitInfo().createGitInfoData(projectId))

'Admin login to TO success'
String url = "${GlobalVariable.testOpsUrl}team/${GlobalVariable.turingAndGryffindoorTeamId}/project/${projectId}"
Page.nav(SignInPage).navigateToUrl(url).login().clickSignIn()

'Select the project and navigate to Defects page'
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateDefects()

'Verify: Display Defect label'
Page.nav(DefectsPage).verifyDefectLabelIsVisible()

'Verify: Display Jira Integration label'
Page.nav(DefectsPage).verifyJiraIntegrationIsVisible()

'Verify: Enable Jira Integration button is clickable'
Page.nav(DefectsPage).verifyJiraBtnIsClickable()
