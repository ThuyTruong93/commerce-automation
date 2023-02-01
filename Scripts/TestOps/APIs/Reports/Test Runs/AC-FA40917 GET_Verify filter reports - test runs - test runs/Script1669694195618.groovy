import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.model.Pagination
import katalon.services.SearchService


// TODO-Tris: [Enhance] Add more filter
BodySearch searchBody = new BodySearch()
searchBody.type = 'Execution'
searchBody.conditions = []
List<String> conditions = [browserName, osName, status]
conditions.each{ def condition ->
        if (condition) {
            List<String> params = condition.trim().split(',')
            searchBody.conditions.add(new ConditionSearch(params.first(), params[1], params.last()))
        }
    }
searchBody.conditions.add(new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId))
searchBody.pagination = new Pagination(0, 100, ['order, desc'])

Object searchResponse = Page.nav(SearchService).search(searchBody).parseResponseBodyToJsonObject()

if(Page.nav(SearchService).testRunsExisted(searchResponse)) 
	Page.nav(SearchService).verfiyTestRunBrowserNameFilter(searchResponse, browserName)
							.verfiyTestRunOsNameFilter(searchResponse, osName)
							.verfiyTestRunStatusFilter(searchResponse, status)

