package katalon.fw.lib

import com.kms.katalon.core.testdata.TestData

import internal.GlobalVariable

public class Credential {
	String email
	String pwd
	String abbrName
	String accountId
	String accountName
	String orgId
	String orgName
	String teamId
	String teamName
	String role
	String projectId
	String projectName
	Boolean testCloudLambdaTestEnvironment
	Boolean testCloudMobileNativeAppEnabled
	String numberOfParallels
	String forTestCases
	String systemRole

	private List<Credential> filteredCredentials = []
	private List<Credential> onlyForSomeTestCasesCredentials = []

	public Credential() {
	}

	public Credential getCredentials() {
		TestData testData = GlobalVariable.credentialDataFile
		int rowNumbers = testData.getRowNumbers()
		List<Credential> credentials = []
		if (rowNumbers > 0) {
			(1..rowNumbers).each({ rowNumber ->
				credentials.add(new Credential().with {
					email = testData.getValue('email', rowNumber)
					pwd = testData.getValue('pwd', rowNumber)
					abbrName = testData.getValue('abbrName', rowNumber)
					accountId = testData.getValue('accountId', rowNumber)
					accountName = testData.getValue('accountName', rowNumber)
					orgId = testData.getValue('orgId', rowNumber)
					orgName = testData.getValue('orgName', rowNumber)
					teamId = testData.getValue('teamId', rowNumber)
					teamName = testData.getValue('teamName', rowNumber)
					role = testData.getValue('role', rowNumber)
					projectId = testData.getValue('projectId', rowNumber)
					projectName = testData.getValue('projectName', rowNumber)
					testCloudLambdaTestEnvironment = testData.getValue('testCloudLambdaTestEnvironment', rowNumber).toBoolean()
					testCloudMobileNativeAppEnabled = testData.getValue('testCloudMobileNativeAppEnabled', rowNumber).toBoolean()
					numberOfParallels = testData.getValue('numberOfParallels', rowNumber)
					forTestCases = testData.getValue('forTestCases', rowNumber)
					systemRole = testData.getValue('systemRole', rowNumber)
					it
				})
			})
		}

		// Remove credentials that should only be used for certain test cases
		filteredCredentials = credentials.findAll {
			it.forTestCases.trim() == ''
		}

		// Put all credentials that are used for a specific test case in another array to use later if needed
		onlyForSomeTestCasesCredentials = credentials.findAll {
			it.forTestCases.trim() != ''
		}

		return this
	}

	/**
	 * This is used in case you want a credential that should be used by some specific test cases. Other test cases will not be able to access this credential.
	 * @return
	 */
	public Credential forThisTestCase() {
		String testCaseID = ((String) GlobalVariable.tcName).split('/').last().split(' ').first()

		filteredCredentials = onlyForSomeTestCasesCredentials.findAll {
			it.forTestCases.contains(testCaseID)
		}
		return this
	}

	public Credential withRole(String role) {
		filteredCredentials = filteredCredentials.findAll {
			it.role.toLowerCase() == role.toLowerCase()
		}
		return this
	}

	public Credential inProject(String projectName) {
		filteredCredentials = filteredCredentials.findAll {
			it.projectName == projectName
		}
		return this
	}

	public Credential inAccount(String accountName) {
		filteredCredentials = filteredCredentials.findAll {
			it.accountName == accountName
		}
		return this
	}

	public Credential inOrg(String orgName) {
		filteredCredentials = filteredCredentials.findAll {
			it.orgName == orgName
		}
		return this
	}

	public Credential withTestCloudLambdaTestEnvironmentFlag(def enabled) {
		filteredCredentials = filteredCredentials.findAll {
			it.testCloudLambdaTestEnvironment == ((enabled instanceof String) ? enabled.toBoolean() : enabled)
		}
		return this
	}

	public Credential withTestCloudMobileNativeAppEnabledFlag(def enabled) {
		filteredCredentials = filteredCredentials.findAll {
			it.testCloudMobileNativeAppEnabled == ((enabled instanceof String) ? enabled.toBoolean() : enabled)
		}
		return this
	}

	public Credential getFirst() {
		return filteredCredentials.first()
	}

	public Credential withSystemRole(String systemRole) {
		filteredCredentials = filteredCredentials.findAll {
			it.systemRole.toLowerCase() == systemRole.toLowerCase()
		}
		return this
	}
}
