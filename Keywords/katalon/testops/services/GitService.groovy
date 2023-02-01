package katalon.testops.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.GitInfo

public class GitService extends BaseService<GitService>{
	static String prefix = GlobalVariable.testOpsApiUrl + GlobalVariable.version
	static String createGitUrl =  prefix + "/git/create"

	public GitService createGit(GitInfo body) {
		initRequestObject()
				.setUrl(createGitUrl)
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}
}
