package katalon.testops.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class ExecutionsService extends BaseService<ExecutionsService>{
	static String addDeleteTagUrl = GlobalVariable.testOpsApiUrl + GlobalVariable.version +  "/executions/%s/tags"
	static String assignCustomFieldUrl = GlobalVariable.testOpsApiUrl + GlobalVariable.version +  "/executions/%s/custom-fields"

	public ExecutionsService assignCustomField(String executionId, Object body) {
		initRequestObject()
				.setUrl(String.format(assignCustomFieldUrl, executionId))
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()

		return this
	}

	public ExecutionsService addTag(String executionId, Object body) {
		initRequestObject()
				.setUrl(String.format(addDeleteTagUrl, executionId))
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()

		return this
	}

	public ExecutionsService deleteTag(String executionId, Object body) {
		initRequestObject()
				.setUrl(String.format(addDeleteTagUrl, executionId))
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendDeleteRequest()

		return this
	}

	public Object generateTagBody(){
		String random = UUID.randomUUID().toString().substring(0, 3);
		String name = 'auto-' + random

		return ['name': name,
			'projectId' : GlobalVariable.platformGenaralProjectId,
			'organizationId': GlobalVariable.defaultOrg]
	}
}
