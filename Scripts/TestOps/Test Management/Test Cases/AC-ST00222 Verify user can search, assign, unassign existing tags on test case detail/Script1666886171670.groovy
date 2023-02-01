import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.testops.testmanagement.TestCaseDetailPage
import katalon.testops.testmanagement.TestCasesPage
import katalon.fw.lib.Page

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

'Input new Tag and Verify create new Tag'
def nameOfTag = 'Auto-'+ UUID.randomUUID().toString().substring(0, 30)
Page.nav(TestCaseDetailPage).createNewTag(nameOfTag)

'Assign new tags on Test Case detail'
Page.nav(TestCaseDetailPage).assignTag(nameOfTag)

'Unassign Existing Tag'
Page.nav(TestCaseDetailPage).unassignTag(nameOfTag)

'Verify user can Search and Assign Existing Tag'
Page.nav(TestCaseDetailPage).clickTagsInputField()
Page.nav(TestCaseDetailPage).searchExistingTag(nameOfTag)

'Unassign Existing Tag'
Page.nav(TestCaseDetailPage).unassignTag(nameOfTag)
