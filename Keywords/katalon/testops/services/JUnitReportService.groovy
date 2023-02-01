package katalon.testops.services

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import groovy.json.JsonOutput
import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class JUnitReportService extends BaseService<JUnitReportService>{
	def junitReportUrl = GlobalVariable.testOpsApiUrl + 'v1/junit/test-reports'
	def username = 'pgialinh.iuetv+auto@gmail.com'
	def password = 'Abcd@1234'

	public JUnitReportService uploadJUnitReport(String projectId, String batch, String folderPath
			, String isEnd, String fileName, String uploadedPath) {
		initRequestObject()
				.setUrl(junitReportUrl)
				.setParamsForUploadJUnitReport(projectId, batch, folderPath, isEnd, fileName, uploadedPath)
				.setBasicAuthorizationHeader(username, password)
				.setJsonContentTypeHeader()
				.sendPostRequest()
		return this
	}

	public JUnitReportService setParamsForUploadJUnitReport(String projectId, String batch, String folderPath
			, String isEnd, String fileName, String uploadedPath) {
		List<TestObjectProperty> parameters = new ArrayList<>()
		parameters.add(new TestObjectProperty('projectId', ConditionType.EQUALS, projectId))
		parameters.add(new TestObjectProperty('batch', ConditionType.EQUALS, batch))
		parameters.add(new TestObjectProperty('folderPath', ConditionType.EQUALS, folderPath))
		parameters.add(new TestObjectProperty('isEnd', ConditionType.EQUALS, isEnd))
		parameters.add(new TestObjectProperty('fileName', ConditionType.EQUALS, fileName))
		parameters.add(new TestObjectProperty('uploadedPath', ConditionType.EQUALS, uploadedPath))
		setParam(parameters)
		return this
	}
}
