import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.services.LicenseManagementService

def conditionSearchOnlineLicense = [
	'Organization.id': orgId,
	'type': type,
	'feature': packageName,
	'status': 'ACTIVE',
	'mode': 'FLOATING'
	]	
	
def conditionSearchRegisterMachine = [
	'Organization.id': orgId,
	'feature': packageName,
		]
		
String machineKey = UUID.randomUUID().toString()
String sessionId = UUID.randomUUID().toString()
String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(password))


'Create an online session and verify the result by searching the online license and register machine '
Page.nav(LicenseManagementService)
	.setCredential(username, decryptedPwd)
	.setOrgId(orgId)
	.setPackageName(packageName)
	.setKsVersion(ksVersion)
	.setMachineKey(machineKey)
	.setSessionId(sessionId)
	.createOnlineSession()
	.verifyStatusCode(200)
	.verifyMachineKey(machineKey)
	.verifyFeature(packageName)
	.verifyOrgId(orgId)
	.verifyType(type)
	.searchOnlineLicense(conditionSearchOnlineLicense)
	.verifyOnlineLicenseFromSearchAPI(machineKey)
	.searchRegisteredMachine(conditionSearchRegisterMachine)
	.verifyRegisteredMachinesFromSearchAPI(machineKey)
	.releaseOnlineLicense()
	.deleteMachineID()
