package katalon.testops.services

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.model.CustomFieldOptions
import katalon.utility.DateTimeUtility

public class CustomFieldDefinitionService extends BaseService<CustomFieldDefinitionService>{
	static String createUrl = GlobalVariable.testOpsApiUrl + GlobalVariable.version + "/custom-field-definitions/create"
	static String updateDeleteUrl = GlobalVariable.testOpsApiUrl + GlobalVariable.version + "/custom-field-definitions"
	static String searchUrl = GlobalVariable.testOpsApiUrl + GlobalVariable.version +  "/search"

	public CustomFieldDefinitionService create(Object body) {
		initRequestObject()
				.setUrl(createUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public CustomFieldDefinitionService update() {
		initRequestObject()
				.setUrl(updateDeleteUrl)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(customField))
				.sendPostRequest()

		return this
	}

	public CustomFieldDefinitionService delete(long id, String projectId = GlobalVariable.platformGenaralProjectId) {
		def body = [ "id": id, "projectId": projectId]
		initRequestObject()
				.setUrl(updateDeleteUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendDeleteRequest()
		return this
	}

	public CustomFieldDefinitionService search(String projectId, long customFieldId) {
		ConditionSearch project = new ConditionSearch("Project.id", "=", projectId)
		ConditionSearch customField = new ConditionSearch("id", "=", customFieldId)
		BodySearch body = new BodySearch("CustomFieldDefinition", [project, customField])
		
		initRequestObject()
				.setUrl(searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
	
		return this
	}
	
	def CustomFieldDefinitionService verifyCutomFieldExisted() {
		WS.verifyGreaterThan(parseResponseBodyToJsonObject().content.size(), 0)
		return this
	}


	public static Object generateData(String projectId = GlobalVariable.platformGenaralProjectId){
		String random = new DateTimeUtility().getCurrentDateTime()
		String key = 'auto-' + random
		String displayName = 'Auto ' + random
		String value = 'value ' + random

		return [
			"key": key, "displayName" : displayName,
			"projectId": projectId,
			"customFieldOptions": [new CustomFieldOptions(value: value)]]
	}
}
