import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import katalon.testops.visualtesting.VisualTestRunPage as VisualTestRunPage
import katalon.common.SignInPage
import katalon.fw.lib.Page as Page
import katalon.common.HeaderBar as HeaderBar

def email = internal.GlobalVariable.vst_pro_email
def password = internal.GlobalVariable.vst_pro_password

'1. Login to TO as admin account'
Page.nav(SignInPage).enterCredential(email, password).clickSignIn()

'2. Navigate to project visual testing with pro'
Page.nav(VisualTestRunPage)
	.accessDetailsVSTProject('143841', '158146')
'3. Select Visual testing tab'
Page.nav(HeaderBar).clickVisualTesting()
	
'4. Select latest Visual baseline Collections'
Page.nav(VisualTestRunPage)
	.selectVisualBaselineCollectionsTab()
	.selectLatestBaselineCollection()
	.verifyPixelSensitivityComponent()