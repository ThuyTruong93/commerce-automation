package katalon.services

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.model.Pagination

public class ProjectService extends BaseService<ProjectService> {
	static String prefix = GlobalVariable.adminApiUrl + GlobalVariable.version
	static String createUrl = prefix + "/projects"
	static String deleteUrl = prefix + "/projects/%s"
	static String searchUrl = prefix +  "/search"
	static String createDefaultCustomFieldUrl = prefix +  "/projects/%s/default-custom-fields"

	public ProjectService createProject (String projectName, String teamId) {
		def body = ["name": projectName, "teamId" : teamId]

		initRequestObject()
				.setUrl(createUrl)
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()

		return this
	}

	public ProjectService delete (long projectId) {
		initRequestObject()
				.setUrl(String.format(deleteUrl, projectId))
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.sendDeleteRequest()

		return this
	}

	public ProjectService search (String projectId) {
		BodySearch body = new BodySearch()
		body.type = 'CustomFieldDefinition'
		body.conditions = [
			new ConditionSearch("Project.id", "=", projectId)
		]
		body.pagination = new Pagination()

		initRequestObject()
				.setUrl(searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public ProjectService createDefault (String projectId) {
		initRequestObject()
				.setUrl(String.format(createDefaultCustomFieldUrl, projectId))
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.sendPostRequest()
		return this
	}

	public ProjectService verifyDefaultCustomField () {
		ArrayList<String> key = new ArrayList<String>()
		ArrayList<String> displayName = new ArrayList<String>()
		ArrayList<String> value = new ArrayList<String>()

		List<String> expectedKey = [
			"test_layer",
			"framework",
			"priority"
		]
		List<String> expectedDisplayName = [
			"Test Layer",
			"Framework",
			"Priority"
		]
		List<String> expectedValue = [
			"API Tests",
			"UI Tests",
			"Unit Tests",
			"JUnit Platform",
			"TestNG",
			"Critical",
			"High",
			"Medium",
			"Low"
		]

		parseResponseBodyToJsonObject().content.each{ item ->
			key.add(item.key);
			displayName.add(item.displayName);
			item.customFieldOptions.each { option ->
				value.add(option.value)
			}
		}

		WS.verifyEqual(key.sort(), expectedKey.sort())
		WS.verifyEqual(displayName.sort(), expectedDisplayName.sort())
		WS.verifyEqual(value.sort(), expectedValue.sort())

		return this
	}

	public String projectSearchBodyByOrgAndTeam (String teamId, String orgId =  GlobalVariable.defaultOrg) {
		ConditionSearch orgCondition = new ConditionSearch("Organization.id", "=", orgId)
		ConditionSearch teamCondition = new ConditionSearch("Team.id", "=", teamId)
		BodySearch body = new BodySearch("Project", [orgCondition, teamCondition])
		return parseObjectToString(body)
	}
}
