package katalon.model

public class IntegrationProject {
	long projectId
	boolean onlyChangedState
	long id
	String serverUrl
	String username
	String password
	boolean active
	String clientId
	String clientSecret
	boolean enabledXray
	ExternalProject defaultExternalProject

	public IntegrationProject(long projectId) {
		this.projectId = projectId
		this.onlyChangedState = false
		this.serverUrl = "https://tristah.atlassian.net/"
		this.username = "blitztestingkatalon@gmail.com"
		this.password = "DQG2MxKTWDUqPzamHBVU3BB8"
		this.active = true
		this.clientId = ""
		this.clientSecret = ""
		this.enabledXray = false
		this.defaultExternalProject = new ExternalProject()
	}

	public long getId() {
		return this.id
	}

	public void setId(long id) {
		this.id = id
	}

	public boolean isOnlyChangedState() {
		return onlyChangedState
	}

	public void setOnlyChangedState(boolean onlyChangedState) {
		this.onlyChangedState = onlyChangedState
	}
}
