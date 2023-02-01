package katalon.testops.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.fw.lib.Page
import katalon.model.GitInfo
import katalon.model.IntegrationProject
import katalon.services.ProjectService

public class SetUpSteps extends BaseService<SetUpSteps>{
	
	public long setUpProject(String projectName) {
		'Create new project'
		long projectId = Page.nav(ProjectService).createProject(projectName, GlobalVariable.turingAndGryffindoorTeamId)
									 .verifyStatusCode(200)
									 .parseResponseBodyToJsonObject().id
									 
		'Setup git repo'
		Page.nav(GitService).createGit(new GitInfo().createGitInfoData(projectId)).verifyStatusCode(200)
		
		return projectId
	}
	
	public void setUpJira(long projectId) {
		IntegrationProject integrationJiraData = new IntegrationProject(projectId)
		IntegrationService integrationService = Page.nav(IntegrationService)
		
		'Add Jira connection'
		integrationService.testIntegrationConnection(integrationJiraData).verifyStatusCode(204)
		
		integrationService.createIntegration(integrationJiraData).verifyStatusCode(200)
		
		'Integrate with external project'
		long externalConnectionId = integrationService.updateIntegration(integrationJiraData)
														.verifyStatusCode(200)
														.parseResponseBodyToJsonObject().id
		integrationJiraData.setId(externalConnectionId)
		integrationJiraData.setOnlyChangedState(true)
		integrationService.updateIntegration(integrationJiraData)
	}

}
