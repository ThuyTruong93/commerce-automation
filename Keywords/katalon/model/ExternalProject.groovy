package katalon.model

public class ExternalProject {
	String externalProjectId
	String externalProjectKey
	String name
	String value
	String label
	
	public ExternalProject() {
		this.externalProjectId = "10002"
		this.externalProjectKey = "TT"
		this.name = "Tuyen test"
		this.value = "10002"
		this.label = "Tuyen test"
	}

	public ExternalProject(String externalProjectId, String externalProjectKey, String name, String value,String label) {
		this.externalProjectId = externalProjectId
		this.externalProjectKey = externalProjectKey
		this.name = name
		this.value = value
		this.label = label
	}
}
