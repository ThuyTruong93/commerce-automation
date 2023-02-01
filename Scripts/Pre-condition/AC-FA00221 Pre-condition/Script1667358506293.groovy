import com.kms.katalon.core.configuration.RunConfiguration
import katalon.fw.lib.Page
import katalon.testops.services.JUnitReportService
import katalon.testops.services.UploadFileService
import katalon.testops.services.UploadURLService

'================ To be updated ============='
def projectId = '374523' //Turing project

'Uploads and processes the JUnit reports to an Execution.'
'Get uploadURL and put report file to S3'
Object objURL = Page.nav(UploadURLService)
				 .getUploadURL(projectId)
				 .verifyStatusCode(200)
				 .parseResponseBodyToJsonObject()
				 
def filePath = RunConfiguration.getProjectDir() + 'Data/Web UI/Pre-condition/precondition-junit-test-run.xml'
Page.nav(UploadFileService)
	.uploadFileToS3(objURL.getAt("uploadUrl"), filePath)

'Upload JUnit Report to TestOps'
Date date = new Date()
String batch = date.getTime() + '-' + UUID.randomUUID()

Page.nav(JUnitReportService)
	.uploadJUnitReport(projectId, batch, '', 'true', 'precondition-junit-test-run.xml', objURL.getAt("path"))
	.verifyStatusCode(204)