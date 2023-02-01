package katalon.testops.services

import katalon.fw.lib.BaseService

public class TestCaseService extends BaseService<TestCaseService>{
	static String updateUrl = internal.GlobalVariable.testOpsApiUrl + internal.GlobalVariable.version +  "/test-cases/update"
	static String addDeleteTagUrl = internal.GlobalVariable.testOpsApiUrl + internal.GlobalVariable.version +  "/test-cases/%s/tags"

	public TestCaseService update (Object body) {
		initRequestObject()
				.setUrl(updateUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(internal.GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()

		return this
	}

	public TestCaseService addTag(String testCaseId, Object body) {
		initRequestObject()
				.setUrl(String.format(addDeleteTagUrl, testCaseId))
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(internal.GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()

		return this
	}
	
	public TestCaseService deleteTag(String testCaseId, Object body) {
		initRequestObject()
				.setUrl(String.format(addDeleteTagUrl, testCaseId))
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(internal.GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendDeleteRequest()

		return this
	}
	
	public static Object generateTagBody(){
		String random = UUID.randomUUID().toString().substring(0, 3);
		String name = 'auto-' + random
		String label = 'auto- ' + random
		return ["name": name, "label" : label]
	}
	
}
