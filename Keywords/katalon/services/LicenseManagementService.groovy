package katalon.services
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.BodySearch
import katalon.model.ConditionSearch

public class LicenseManagementService extends BaseService<LicenseManagementService>{
	static String ACTIVATE_LICENSE_URL = GlobalVariable.adminUrl+"api/v1/license-keys/activate"
	static String SEARCH_URL = GlobalVariable.adminUrl + "api/v1/search"
	static String RELEASE_ONLINE_LICENSE_URL = GlobalVariable.adminUrl + "api/v1/license-keys/release"
	static String DELETE_MACHINEID_URL = GlobalVariable.adminUrl + "api/v1/machine"

	String username;
	String password;
	String machineKey;
	String ksVersion;
	String orgId;
	String sessionId;
	String packageName;

	int quotaLicense;
	int usedQuota;


	public LicenseManagementService setCredential(String username, String password) {
		this.username = username;
		this.password = password;
		return this
	}

	public LicenseManagementService setOrgId(String orgId) {
		this.orgId = orgId;
		return this
	}

	public LicenseManagementService setPackageName(String packageName) {
		this.packageName = packageName;
		return this
	}

	public LicenseManagementService setKsVersion(String ksVersion) {
		this.ksVersion = ksVersion;
		return this
	}

	public LicenseManagementService setMachineKey(String machineKey) {
		this.machineKey = machineKey ? machineKey : UUID.randomUUID().toString();
		return this
	}

	public LicenseManagementService setSessionId(String sessionId) {
		this.sessionId = sessionId ? sessionId : UUID.randomUUID().toString();
		return this
	}

	public LicenseManagementService printResult () {
		println "===RESPONSE==="
		println parseResponseBodyToJsonObject()
		return this
	}

	public LicenseManagementService verifyValidNoOfCreatingLicense(int noOfCreatingLicense) {
		WS.verifyEqual(true, noOfCreatingLicense <= this.quotaLicense - this.usedQuota)
		return this
	}

	public LicenseManagementService createOnlineSession(String machineKey = this.machineKey, String sessionId = this.sessionId) {
		initRequestObject()
				.setUrl(this.ACTIVATE_LICENSE_URL)
				.setMachineKey(machineKey)
				.setSessionId(machineKey)
				.setParamsOnlineSession()
				.setBasicAuthorizationHeader(this.username, this.password)
				.setJsonContentTypeHeader()
				.sendPostRequest()
				.verifyStatusCode(200)
				.printResult()
		return this
	}

	public LicenseManagementService createOnlineSessions(int noOfCreatingLicense, List<String> machineKeys, List<String> sessionIds) {

		for(int i = 0; i < noOfCreatingLicense; i++) {

			println "Index $i create "+machineKeys.getAt(i)
			createOnlineSession(machineKeys.getAt(i), sessionIds.getAt(i))
		}
		return this
	}

	public LicenseManagementService setParamsOnlineSession() {
		List<TestObjectProperty> parameters = new ArrayList<>()
		parameters.add(new TestObjectProperty('machineKey', ConditionType.EQUALS, this.machineKey))
		parameters.add(new TestObjectProperty('ksVersion', ConditionType.EQUALS, this.ksVersion))
		parameters.add(new TestObjectProperty('organizationId', ConditionType.EQUALS, this.orgId))
		parameters.add(new TestObjectProperty('sessionId', ConditionType.EQUALS, this.sessionId))
		parameters.add(new TestObjectProperty('package', ConditionType.EQUALS, this.packageName))
		setParam(parameters)
		return this
	}

	public LicenseManagementService setFeatureParam(String feature) {
		List<TestObjectProperty> parameters = new ArrayList<>()
		parameters.add(new TestObjectProperty('feature', ConditionType.EQUALS, feature))
		setParam(parameters)
		return this
	}

	public LicenseManagementService verifyMachineKey(String machineKey) {
		String actualKey = parseResponseBodyToJsonObject().machineId
		WS.verifyEqual(actualKey, machineKey)
		return this
	}

	public LicenseManagementService verifyOrgId(String orgId) {
		String actualData = parseResponseBodyToJsonObject().organization.id
		WS.verifyEqual(actualData, orgId)
		return this
	}

	public LicenseManagementService verifyFeature(String feature) {
		String actualData = parseResponseBodyToJsonObject().feature
		WS.verifyEqual(actualData, feature)
		return this
	}

	public LicenseManagementService verifyType(String type) {
		String actualData = parseResponseBodyToJsonObject().type
		WS.verifyEqual(actualData, type)
		return this
	}

	public LicenseManagementService searchOnlineLicense(Map<String, String> conditionMap) {

		List<ConditionSearch> conditions = new ArrayList<>()
		conditionMap.each { item -> conditions.add(new ConditionSearch(item.key, "=", item.value)) }

		BodySearch body = new BodySearch("LicenseKey", conditions)

		initRequestObject()
				.setUrl(this.SEARCH_URL)
				.setBasicAuthorizationHeader(username, password)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
				.printResult()
		return this
	}

	public LicenseManagementService verifyOnlineLicenseFromSearchAPI(String expectedId) {
		WS.verifyEqual(getTheContent().stream().anyMatch {it.machineId == expectedId}, true)
		return this
	}

	public LicenseManagementService searchRegisteredMachine(Map<String, String> conditionMap) {

		List<ConditionSearch> conditions = new ArrayList<>()
		conditionMap.each { item -> conditions.add(new ConditionSearch(item.key, "=", item.value)) }

		BodySearch body = new BodySearch("Machine", conditions)

		initRequestObject()
				.setUrl(this.SEARCH_URL)
				.setBasicAuthorizationHeader(this.username, this.password)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
				.printResult()
		return this
	}

	public LicenseManagementService verifyRegisteredMachinesFromSearchAPI(String expectedKey) {
		WS.verifyEqual(getTheContent().any { it.machineKey == expectedKey}, true)
		return this
	}

	public LicenseManagementService releaseOnlineLicense(String machineKey = this.machineKey, String sessionId = this.sessionId) {
		initRequestObject()
				.setUrl(this.RELEASE_ONLINE_LICENSE_URL)
				.setMachineKey(machineKey)
				.setSessionId(sessionId)
				.setBasicAuthorizationHeader(this.username, this.password)
				.setJsonContentTypeHeader()
				.setParamsOnlineSession()
				.sendPostRequest()
				.verifyStatusCode(200)
		return this
	}

	public LicenseManagementService releaseOnlineLicenses(int noOfCreatingLicense = 1, List<String> machineKeys, List<String> sessionIds) {
		for(int i = 0; i < noOfCreatingLicense; i++) {
			println "Release machine index $i "+machineKeys.getAt(i)
			releaseOnlineLicense(machineKeys.getAt(i), sessionIds.getAt(i))
		}
		return this
	}

	public LicenseManagementService deleteMachineID(String machineKey = this.machineKey, String sessionId = this.sessionId) {
		initRequestObject()
				.setUrl(this.DELETE_MACHINEID_URL)
				.setMachineKey(machineKey)
				.setSessionId(sessionId)
				.setBasicAuthorizationHeader(this.username, this.password)
				.setJsonContentTypeHeader()
				.setParamsOnlineSession()
				.sendDeleteRequest()
				.verifyStatusCode(204)
		return this
	}

	public LicenseManagementService deleteMachineIDs(int noOfCreatingLicense, List<String> machineKeys, List<String> sessionIds) {
		for(int i = 0; i < noOfCreatingLicense; i++) {
			println "Delete machine index $i "+machineKeys.getAt(i)
			deleteMachineID(machineKeys.getAt(i), sessionIds.getAt(i))
		}
		return this
	}

	public LicenseManagementService getQuota(String orgId = this.orgId, String feature = this.packageName) {
		initRequestObject()
				.setUrl(this.buildGetQuotaUrl(orgId))
				.setBasicAuthorizationHeader(this.username, this.password)
				.setJsonContentTypeHeader()
				.setFeatureParam(feature)
				.sendGetRequest()
				.verifyStatusCode(200)
				.printResult()

		switch(feature) {
			case "KSE":
				this.quotaLicense = getResponseBody().quotaKSE as Integer
				this.usedQuota = getResponseBody().usedKSE as Integer
				break;
			case "ENGINE":
				this.quotaLicense = getResponseBody().quotaEngine as Integer
				this.usedQuota = getResponseBody().usedEngine as Integer
				break;
			case "UNLIMITED_KSE":
				this.quotaLicense = getResponseBody().quotaUnlimitedKSE as Integer
				this.usedQuota = getResponseBody().usedUnlimitedKSE as Integer
				break;
			case "UNLIMITED_ENGINE":
				this.quotaLicense = getResponseBody().quotaUnlimitedEngine as Integer
				this.usedQuota = getResponseBody().usedUnlimitedEngine as Integer
				break;
			default:
				this.quotaLicense = 0
				this.usedQuota = 0
		}

		println "Quota $quotaLicense \nUsedQuota $usedQuota"

		return this
	}

	public LicenseManagementService verifyQuotaLicense(int expectedQuota) {
		WS.verifyEqual(this.quotaLicense, expectedQuota)
		return this
	}

	public LicenseManagementService verifyUsedQuota(int expectedUsedQuota) {
		WS.verifyEqual(this.usedQuota, expectedUsedQuota)
		return this
	}

	private List<Object> getTheContent() {
		return parseResponseBodyToJsonObject().content;
	}

	private Object getResponseBody() {
		return parseResponseBodyToJsonObject();
	}

	private String buildGetQuotaUrl(String orgId) {
		return GlobalVariable.adminUrl + "api/v1/organizations/$orgId/subscription-license"
	}
}
