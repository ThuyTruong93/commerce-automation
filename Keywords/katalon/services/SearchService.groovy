package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class SearchService extends BaseService<SearchService> {
	static String searchUrl = GlobalVariable.testOpsApiUrl + GlobalVariable.version +  "/search"

	public SearchService search (Object body) {
		initRequestObject()
				.setUrl(searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public SearchService searchTagInfo () {
		BodySearch body = new BodySearch('Tag', [
			new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)
		], true)

		initRequestObject()
				.setUrl(searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public SearchService verfiyTestRunBrowserNameFilter(Object searchResponse, String browserName) {
		if(browserName) {
			int count = 0;
			searchResponse.content.each{ content ->
				content.executionTestSuiteResources.each{ execution ->
					if(execution.platform.browserName == browserName.trim().split(',').last()) count++
				}
			}
			WS.verifyGreaterThan(count, 0)
		}
		return this
	}

	public SearchService verfiyTestRunOsNameFilter(Object searchResponse, String osName) {
		if(osName) {
			int count = 0;
			searchResponse.content.each{ content ->
				content.executionTestSuiteResources.each{ execution ->
					if(execution.platform.osName == osName.trim().split(',').last()) count++
				}
			}
			WS.verifyGreaterThan(count, 0)
		}
		return this
	}

	public SearchService verfiyTestRunStatusFilter(Object searchResponse, String status) {
		if(status) {
			searchResponse.content.each{ content ->
				WebUI.verifyEqual(content.status, status.trim().split(',').last())
			}
		}
		return this
	}

	public boolean testRunsExisted (Object searchResponse) {
		return searchResponse.content != []
	}
}