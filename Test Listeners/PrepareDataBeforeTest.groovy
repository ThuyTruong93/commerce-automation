import com.kms.katalon.core.annotation.AfterTestCase

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.my.account.ChangePasswordPopup
import katalon.services.ProjectService
import katalon.services.TeamService

class PrepareDataBeforeTest {
	def listProjectAndTeam = [
		[team: 'Leopard', project: 'Commerce Engine'],
		[team: 'Pegasus', project: 'Account Admin'],
		[team: 'Turing & Gryffindoor', project: 'Platform General'],
		[team: 'Quality Team', project: 'Quality Engineers'],
		[team: 'Cloudian', project: 'Cloud-based Test Execution'],
	 ] as Map[]
	
	def storeDefaultTestData() {
		'Search all team under qe default org'
		String requestBody = Page.nav(TeamService).teamSearchBodyByOrg()
		
		def lstExistingTeams = Page.nav(TeamService).initRequestObject()
													.setSearchUrl()
													.setBasicTokenAuthorizationHeader()
													.setJsonContentTypeHeader()
													.setPayLoad(requestBody)
													.sendPostRequest()
													.parseResponseBodyToJsonObject()
		for(item in listProjectAndTeam) {
			def teamInfo = lstExistingTeams.content.stream().find({it.name.equals(item.team) })
		
			switch(item.team)
			{
				case 'Leopard':
								GlobalVariable.leopardTeamId = teamInfo.id.toString()
								// Search existing project on team
								String projectRequestBody = Page.nav(ProjectService).projectSearchBodyByOrgAndTeam(GlobalVariable.leopardTeamId)
								def lstExistingProjects = Page.nav(ProjectService).initRequestObject()
																			   .setSearchUrl()
																			   .setBasicTokenAuthorizationHeader()
																			   .setJsonContentTypeHeader()
																			   .setPayLoad(projectRequestBody)
																			   .sendPostRequest()
																			   .parseResponseBodyToJsonObject()
								def projectInfo = lstExistingProjects.content.stream().find({it.name.equals(item.project) })
								GlobalVariable.commerceEngineProjectId = projectInfo.id
								break
				case 'Pegasus':
								GlobalVariable.pegasusTeamId = teamInfo.id.toString()
								// Search existing project on team
								String projectRequestBody = Page.nav(ProjectService).projectSearchBodyByOrgAndTeam(GlobalVariable.pegasusTeamId)
								def lstExistingProjects = Page.nav(ProjectService).initRequestObject()
																			   .setSearchUrl()
																			   .setBasicTokenAuthorizationHeader()
																			   .setJsonContentTypeHeader()
																			   .setPayLoad(projectRequestBody)
																			   .sendPostRequest()
																			   .parseResponseBodyToJsonObject()
								def projectInfo = lstExistingProjects.content.stream().find({it.name.equals(item.project) })
								GlobalVariable.accuntAdminProjectId = projectInfo.id
								break
				
				case 'Turing & Gryffindoor':
								GlobalVariable.turingAndGryffindoorTeamId = teamInfo.id.toString()
								// Search existing project on team
								String projectRequestBody = Page.nav(ProjectService).projectSearchBodyByOrgAndTeam(GlobalVariable.turingAndGryffindoorTeamId)
								def lstExistingProjects = Page.nav(ProjectService).initRequestObject()
																			   .setSearchUrl()
																			   .setBasicTokenAuthorizationHeader()
																			   .setJsonContentTypeHeader()
																			   .setPayLoad(projectRequestBody)
																			   .sendPostRequest()
																			   .parseResponseBodyToJsonObject()
								def projectInfo = lstExistingProjects.content.stream().find({it.name.equals(item.project) })
								GlobalVariable.platformGenaralProjectId = projectInfo.id
								GlobalVariable.platformGenaralProjectId = 10
								break
				
				case 'Cloudian':
								GlobalVariable.cloudianTeamId = teamInfo.id.toString()
								// Search existing project on team
								String projectRequestBody = Page.nav(ProjectService).projectSearchBodyByOrgAndTeam(GlobalVariable.cloudianTeamId)
								def lstExistingProjects = Page.nav(ProjectService).initRequestObject()
																			   .setSearchUrl()
																			   .setBasicTokenAuthorizationHeader()
																			   .setJsonContentTypeHeader()
																			   .setPayLoad(projectRequestBody)
																			   .sendPostRequest()
																			   .parseResponseBodyToJsonObject()
								def projectInfo = lstExistingProjects.content.stream().find({it.name.equals(item.project) })
								GlobalVariable.cloudBasedExecProjectId = projectInfo.id
								break
			}
		}
	}
}
