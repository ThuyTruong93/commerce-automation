import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.services.JUnitReportService
import katalon.testops.services.UploadFileService
import katalon.testops.services.UploadURLService
import katalon.testops.testmanagement.TestCaseDetailPage
import katalon.testops.testmanagement.TestCasesPage

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential()
					.doCheckRemberMe()
					.clickSignIn()

'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)

'User click on Test Management module'
Page.nav(HeaderBar).clickTests()

'Go to Test Case Detail Page'
Page.nav(TestCasesPage).clickUploadedDataFolder()
					   .clickSubFolder('PearTest')
					   .clickTestCaseName('testSuccess')
					   
'Click on Tag input field'
Page.nav(TestCaseDetailPage).clickTagsInputField()

'Input new Tag and Verify user can create new Tag'
def nameOfTag = 'Auto-'+ UUID.randomUUID().toString().substring(0, 30)
Page.nav(TestCaseDetailPage).createNewTag(nameOfTag).verifyCreateNewTag(nameOfTag)

'Verify user can Assign new tags on Test Case detail'
Page.nav(TestCaseDetailPage).assignTag(nameOfTag)
Page.nav(TestCaseDetailPage).verifyTagIsVisible(nameOfTag)

'Verify user can Unassign tag on Test Case detail'
Page.nav(TestCaseDetailPage).unassignTag(nameOfTag)
Page.nav(TestCaseDetailPage).verifyTagIsVisibleOnDropdown(nameOfTag)
