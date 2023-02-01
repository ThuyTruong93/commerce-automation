package katalon.testops.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.IntegrationProject

public class IntegrationService extends BaseService<IntegrationService>{
	static String prefix = GlobalVariable.testOpsApiUrl + GlobalVariable.version
	static String createIntegrationUrl =  prefix + "/integrations/projects"
	static String updateIntegrationUrl =  prefix + "/integrations/setting"
	static String testIntegrationConnectionUrl =  prefix + "/integrations/setting/test-connection"

	public IntegrationService testIntegrationConnection(IntegrationProject body) {
		initRequestObject()
				.setUrl(testIntegrationConnectionUrl)
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public IntegrationService createIntegration(IntegrationProject body) {
		initRequestObject()
				.setUrl(createIntegrationUrl)
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public IntegrationService updateIntegration(IntegrationProject body) {
		initRequestObject()
				.setUrl(updateIntegrationUrl)
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}
}
