import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.services.ProjectService
import katalon.services.TeamService


def listProjectAndTeam = [
							[team: 'Leopard', project: 'Commerce Engine'],
							[team: 'Pegasus', project: 'Account Admin'],
							[team: 'Turing & Gryffindoor', project: 'Platform General'],
							[team: 'Quality Team', project: 'Quality Engineers'],
							[team: 'Cloudian', project: 'Cloud-based Test Execution'],
						 ] as Map[]
						 
'Search all team under qe default org'
def projectService = Page.nav(ProjectService)
def teamService = Page.nav(TeamService)
String requestBody = teamService.teamSearchBodyByOrg()

def lstExistingTeams = teamService.initRequestObject()
											.setSearchUrl()
											.setBasicTokenAuthorizationHeader()
											.setJsonContentTypeHeader()
											.setPayLoad(requestBody)
											.sendPostRequest()
											.verifyStatusCode(200)
											.parseResponseBodyToJsonObject()
											
'Create project and team if not existing on current org'
if(lstExistingTeams.content != []) {
	for(item in listProjectAndTeam) {
		def teamInfo = lstExistingTeams.content.stream().find({it.name.equals(item.team) })
		if(teamInfo != null) {
			boolean isProjectExisting = false
			// Search existing project on team
			String projectRequestBody = projectService.projectSearchBodyByOrgAndTeam(teamInfo.id.toString())
			def lstExistingProjects = projectService.initRequestObject()
														   .setSearchUrl()
														   .setBasicTokenAuthorizationHeader()
														   .setJsonContentTypeHeader()
														   .setPayLoad(projectRequestBody)
														   .sendPostRequest()
														   .parseResponseBodyToJsonObject()
														   
			def projectInfo = lstExistingProjects.content.stream().find({it.name.equals(item.project) })
			// Create project if not exist
			if(projectInfo.equals(null)) {
				Object createProjectBody = ["name": item.project,"teamId": teamInfo.id]
				String payload = projectService.parseObjectToString(createProjectBody)
				
				projectService.initRequestObject()
									.setUrl()
									.setBasicTokenAuthorizationHeader()
									.setJsonContentTypeHeader()
									.setPayLoad(payload)
									.sendPostRequest()
									.verifyStatusCode(200)
									.parseResponseBodyToJsonObject()
			}
		}
		else {
				// Create team and project
				// Create team
				Object createTeamBody = ["name": item.team,"organizationId": GlobalVariable.defaultOrg]
				String payload = teamService.parseObjectToString(createTeamBody)
				def createdteamInfo = teamService.initRequestObject()
									.setUrl()
									.setBasicTokenAuthorizationHeader()
									.setJsonContentTypeHeader()
									.setPayLoad(payload)
									.sendPostRequest()
									.verifyStatusCode(200)
									.parseResponseBodyToJsonObject()
				// Create project
				Object createProjectBody = ["name": item.project,"teamId": createdteamInfo.id]
				String projectPayload = projectService.parseObjectToString(createProjectBody)
				
				projectService.initRequestObject()
										.setUrl()
										.setBasicTokenAuthorizationHeader()
										.setJsonContentTypeHeader()
										.setPayLoad(projectPayload)
										.sendPostRequest()
										.verifyStatusCode(200)
										.parseResponseBodyToJsonObject()
				
			}							
		}
	
	} else {
		'Create team and project'
		for(item in listProjectAndTeam) {
			Object createTeamBody = ["name": item.team,"organizationId": GlobalVariable.defaultOrg]
			String payload = teamService.parseObjectToString(createTeamBody)
			// Create team
			def teamInfo = teamService.initRequestObject()
								.setUrl()
								.setBasicTokenAuthorizationHeader()
								.setJsonContentTypeHeader()
								.setPayLoad(payload)
								.sendPostRequest()
								.verifyStatusCode(200)
								.parseResponseBodyToJsonObject()
			// Create project
			Object createProjectBody = ["name": item.project,"teamId": teamInfo.id]
			String projectPayload = projectService.parseObjectToString(createProjectBody)
								
			projectService.initRequestObject()
									.setUrl()
									.setBasicTokenAuthorizationHeader()
									.setJsonContentTypeHeader()
									.setPayLoad(projectPayload)
									.sendPostRequest()
									.verifyStatusCode(200)
									.parseResponseBodyToJsonObject()
		}
		
	}
