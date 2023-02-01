import katalon.fw.lib.Page

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.common.HomePage
import katalon.testops.testmanagement.TestCaseDetailPage
import katalon.testops.testmanagement.TestCasesPage


'1.Login into TestOps'
Page.nav(SignInPage).enterCredential()
					.doCheckRemberMe()
					.clickSignIn()
					
'2. At Home page, user select Project'
Page.nav(HomePage).selectProject('Turing')
					
'3.Go to the Test Case detail from the Test Management module'
Page.nav(HeaderBar).clickTests()

'4. Go to Test Case Detail Page'
Page.nav(TestCasesPage).clickUploadedDataFolder()
					   .clickSubFolder('PearTest')
					   .clickTestCaseName('testSuccess')

'4.Click on the Tags input field.'
Page.nav(TestCaseDetailPage).clickTagsInputField()

'5.Input general info [tag name] in the Tags input field.'
Page.nav(TestCaseDetailPage).createNewTag(tagName)
							.verifyCreateNewTag(tagName)
							.assignTag(tagName)

'6.Verify throw the message Tags must be within 1-50 characters long.'
'Verify throw the message Tags only allow the following characters: a-z, A-Z, 0-9, -, _.'
Page.nav(TestCaseDetailPage).verifyErrorMessage(errorMessage)



