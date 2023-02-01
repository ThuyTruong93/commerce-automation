package katalon.testops.services

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.fw.lib.BaseService


public class ProjectService extends BaseService<ProjectService>{
	static String prefix = GlobalVariable.testOpsApiUrl + GlobalVariable.version
	static String createGetDeleteWedhooksJiraUrl = prefix + "/project/%s/webhooks/jira"
	static String rotateWedhooksJiraUrl =prefix + "/project/%s/webhooks/jira/rotate"

	public ProjectService createWedhooksJira (String webhookName, long projectId) {
		Object body = ['name': webhookName]
		
		initRequestObject()
				.setUrl(String.format(createGetDeleteWedhooksJiraUrl, projectId))
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public ProjectService generateSercretKey (long projectId) {
		initRequestObject()
				.setUrl(String.format(createGetDeleteWedhooksJiraUrl, projectId))
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.sendGetRequest()
		return this
	}

	public ProjectService deleteWedhooksJira (long projectId) {
		initRequestObject()
				.setUrl(String.format(createGetDeleteWedhooksJiraUrl, projectId))
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.sendDeleteRequest()
		return this
	}

	public ProjectService rotateWedhooksJira (String projectId) {
		initRequestObject()
				.setUrl(String.format(rotateWedhooksJiraUrl, projectId))
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.sendPostRequest()
		return this
	}

	// TODO-Tris: Adding check with db
	public ProjectService verifyCreateSecretKeySuccess() {
		String actualResult = parseResponseBodyToJsonObject().secretKey
		WS.verifyEqual(!actualResult.isEmpty(), true)
		return this
	}

	public ProjectService verifyCreateSecretKeySuccess(String secretKey) {
		WS.verifyEqual(parseResponseBodyToJsonObject().secretKey, secretKey)
		return this
	}

	public ProjectService verifyNoSecretKeyInProject(projectId) {
		generateSercretKey(projectId).verifyStatusCode(200).verifyContentIsNull()
		return this
	}
}
