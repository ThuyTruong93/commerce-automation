import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.testops.services.ProjectService as testOpsProjectService
import katalon.testops.services.SetUpSteps
import katalon.utility.DateTimeUtility
	
'Pre-condtion: Create new project'
String projectName = String.format('Auto %s %s', GlobalVariable.currentTestCaseName, new DateTimeUtility().getCurrentDateTime())
projectId = Page.nav(SetUpSteps).setUpProject(projectName)

'Pre-condtion: Integrate with Jira'
Page.nav(SetUpSteps).setUpJira(projectId)

'Pre-condtion: Create webhook'
Page.nav(testOpsProjectService).createWedhooksJira(projectName, projectId)

'Verify link wedhook successfully'
Page.nav(testOpsProjectService).initRequestObject()
						.setUrl(String.format(Page.nav(testOpsProjectService).createGetDeleteWedhooksJiraUrl, projectId))
						.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
						.sendGetRequest()
						.verifyStatusCode(200)
						.verifyCreateSecretKeySuccess()
				
