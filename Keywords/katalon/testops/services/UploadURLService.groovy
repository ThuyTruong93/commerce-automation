package katalon.testops.services

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class UploadURLService extends BaseService<UploadURLService> {
	def uploadURL = GlobalVariable.testOpsApiUrl + 'v1/files/upload-url'
	def username = 'pgialinh.iuetv+auto@gmail.com'
	def password = 'Abcd@1234'

	public UploadURLService getUploadURL(String projectId) {
		initRequestObject()
				.setUrl(uploadURL)
				.setParamsForUploadURL(projectId)
				.setBasicAuthorizationHeader(username, password)
				.setJsonContentTypeHeader()
				.sendGetRequest()
		return this
	}

	public UploadURLService setParamsForUploadURL(String projectId) {
		List<TestObjectProperty> parameters = new ArrayList<>()
		parameters.add(new TestObjectProperty('projectId', ConditionType.EQUALS, projectId))
		setParam(parameters)
		return this
	}
}
