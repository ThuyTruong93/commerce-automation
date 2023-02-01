import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.projectmanagement.EditProjectNamePopup
import katalon.my.projectmanagement.ProjectManagementPage

'1. User sign in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'2. User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'3. Select Project Management item in Setting dropdown'
Page.nav(AdminHeaderBar).clickSettingIcon().clickProjectManagement()

'4. Select Edit Project Name'
Page.nav(ProjectManagementPage).clickActionButtonOfProject().clickEditProjectName()

projectName = projectName + new Random().nextInt()

'5. Input new project name, click save and verify edit project name successfully'
Page.nav(EditProjectNamePopup).inputProjectName(projectName).clickSave().sleepALittleTime()

'6. Verify edit project name successfully'
Page.nav(ProjectManagementPage).verifyProjectName(projectName)
