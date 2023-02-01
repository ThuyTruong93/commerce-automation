import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.testexecution.ApplicationRepositoryPage
import katalon.testops.testexecution.TestRunCalendarPage

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential().clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(GlobalVariable.cloudianTeamId, GlobalVariable.cloudBasedExecProjectId)

'User click on Application Repository tab'
Page.nav(TestRunCalendarPage).clickApplicationRepositoryTab()
WebUI.delay(10)

'User clicks “Upload Application” and Selected (or Drag and Drop) the Application file (.apk, ,.aab, .ipa) id(upload-app)'
Page.nav(ApplicationRepositoryPage).clickUploadApplication().selectFileUpload()

