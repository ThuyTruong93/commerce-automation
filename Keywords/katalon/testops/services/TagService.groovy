package katalon.testops.services
import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class TagService extends BaseService<TagService> {
	static String createTagURL = GlobalVariable.testOpsApiUrl + GlobalVariable.version + "/tags"
	public TagService create(Object body) {
		initRequestObject()
				.setUrl(createTagURL)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}
}
