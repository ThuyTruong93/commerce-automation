import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.model.IntegrationProject
import katalon.testops.services.IntegrationService
import katalon.testops.services.ProjectService as testOpsProjectService
import katalon.testops.services.SetUpSteps
import katalon.utility.DateTimeUtility

'Pre-condtion: Create new project'
String projectName = String.format('Auto %s %s', GlobalVariable.currentTestCaseName, new DateTimeUtility().getCurrentDateTime())
projectId = Page.nav(SetUpSteps).setUpProject(projectName)

'Pre-condtion: Integrate with Jira'
Page.nav(SetUpSteps).setUpJira(projectId)
		
'Verify link wedhook successfully'
Object body = ['name': projectName]
Page.nav(testOpsProjectService).initRequestObject()
						.setUrl(String.format(testOpsProjectService.createGetDeleteWedhooksJiraUrl, projectId))
						.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
						.setJsonContentTypeHeader()
						.setPayLoad(Page.nav(testOpsProjectService).parseObjectToString(body))
						.sendPostRequest()
						.verifyStatusCode(200)
						.verifyCreateSecretKeySuccess()
						
