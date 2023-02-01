package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.BodySearch
import katalon.model.ConditionSearch

public class TeamService extends BaseService<TeamService> {
	public TeamService setUrl (String uri = '') {
		String endpoint = GlobalVariable.adminApiUrl + GlobalVariable.version + '/teams' + uri
		request.setRestUrl(endpoint)
		return this
	}

	public TeamService setSearchUrl (String uri = '') {
		String endpoint = GlobalVariable.adminApiUrl + GlobalVariable.version +  "/search"
		request.setRestUrl(endpoint)
		return this
	}

	public String teamSearchBodyByOrg (String orgId =  GlobalVariable.defaultOrg) {
		ConditionSearch conditions = new ConditionSearch("Organization.id", "=", orgId)
		BodySearch body = new BodySearch("Team", [conditions])
		return parseObjectToString(body)
	}
}
