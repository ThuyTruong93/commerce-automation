package katalon.model

import katalon.fw.lib.BaseService

public class GitInfo{
	String id
	String repository
	String username
	String password
	String branch
	long projectId
	String name
	String description
	String vcsType
	boolean shouldMergeTestResultsForNewScriptRepo

	public GitInfo createGitInfoData(long projectId) {
		this.repository = "https://github.com/BichTuyenNguyen/Demo.git"
		this.username = "tuyen"
		this.password = "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"
		this.branch = "refs/heads/main"
		this.projectId = projectId
		this.name = "Demo.git"
		this.vcsType = "GITHUB"
		this.description = "Git demo"
		this.shouldMergeTestResultsForNewScriptRepo = false

		return this
	}
}
