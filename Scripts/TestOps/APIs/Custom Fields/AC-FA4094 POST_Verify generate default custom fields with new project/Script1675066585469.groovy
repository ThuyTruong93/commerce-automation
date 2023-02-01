import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.model.Pagination
import katalon.services.ProjectService
import katalon.utility.DateTimeUtility

'The Admin creates new project'
String projectName = String.format('Auto %s %s', GlobalVariable.currentTestCaseName, new DateTimeUtility().getCurrentDateTime())
def projectService = Page.nav(ProjectService)
projectId = projectService.createProject(projectName, GlobalVariable.turingAndGryffindoorTeamId)
								 .verifyStatusCode(200).parseResponseBodyToJsonObject().id

'Verify Default Custom Field is added and corrected'
BodySearch body = new BodySearch()
body.type = 'CustomFieldDefinition'
body.conditions = [new ConditionSearch("Project.id", "=", projectId)]
body.pagination = new Pagination()

projectService.initRequestObject()
				.setUrl(projectService.searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(projectService.parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
				.verifyDefaultCustomField()
				