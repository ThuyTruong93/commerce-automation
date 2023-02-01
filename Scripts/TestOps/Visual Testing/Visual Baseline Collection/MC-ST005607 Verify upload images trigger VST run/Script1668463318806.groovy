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

'Precondition: '
'- Has an Organization with VST Pro.'

'1. Log into TO.'

'2. Navigate to the Module Visual Testing > Visual Baselines Collections > seclect an ID.'

'3. Click to the button "Upload Images".'

'4. Dialog is shown and the button "Upload Images" is disabled.'

'5. Click the "Click to upload" link inside the dialog.'

'6. Verify user can select image(s) from local environment, preview them after the selection and the "Upload" button is enabled.'

'7. Verify user drag and drop image(s) from local environment, preview them after the selection The "Upload" button is enabled.'

'8. Preview the selected image(s) in the dialog and verify UI with the information in the AC5 of story.'

'9. If the number of selected images are larger than the at-one-time-uploaded number, a banner will be shown.'
'And the files selection is disabled to prevent me from selecting more files.'

'10. Confirm to upload the selected images by clicking to the button "Upload".'
'The images are successfully uploaded AND new version of the baseline collection is immediately generated.'