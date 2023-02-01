import com.kms.katalon.util.CryptoUtil

import katalon.fw.lib.Page
import katalon.services.LicenseManagementService
import katalon.utility.CommonUtility

noOfCreatingLicense = noOfCreatingLicense.toString().toInteger()
List<String> machineKeys = CommonUtility.generateRandomUUIDs(noOfCreatingLicense)
List<String> sessionIds = CommonUtility.generateRandomUUIDs(noOfCreatingLicense)
String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(password))

'Get inital number of used one'
Page.nav(LicenseManagementService)
	.setCredential(username, decryptedPwd)
	.setKsVersion(ksVersion)
	.setOrgId(orgId)
	.setPackageName(packageName)
	.getQuota()
	.verifyValidNoOfCreatingLicense(noOfCreatingLicense)
	
int initialUsedQuota = Page.nav(LicenseManagementService).getUsedQuota()

'Create some online license and verify the current number of used one, then clean up DB'
Page.nav(LicenseManagementService)
	.createOnlineSessions(noOfCreatingLicense, machineKeys, sessionIds)
	.getQuota()
	.verifyUsedQuota(initialUsedQuota+noOfCreatingLicense)
	.releaseOnlineLicenses(noOfCreatingLicense, machineKeys, sessionIds)
	.deleteMachineIDs(noOfCreatingLicense, machineKeys, sessionIds)
