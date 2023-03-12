package katalon.common

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.FormDataBodyParameter
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.util.CryptoUtil

import external.services.GSheetService
import internal.GlobalVariable
import katalon.fw.db.PostgreSql
import katalon.fw.lib.Page
import katalon.model.Account
import katalon.model.GSheetValueRange
import katalon.testops.services.LoginService
import katalon.utility.DateTimeUtility

public class SeedingData {
	public dataSetUp() {
		Page.nav(PostgreSql).openConnection().executeUpdate("update  public.accounts set name = 'QEAUTO Account Name_hl' where owneremail='hong.le@katalon.com' and name ='QEAUTO Account Name_updated'")
		Page.nav(PostgreSql).closeConnection()
	}

	public static void setUpKatOneToken(String email, String encodedPwd) {
		Account account = new Account(email: email, password: CryptoUtil.decode(CryptoUtil.getDefault(encodedPwd)))

		Page.nav(LoginService)
				.initRequestObject()
				.setUrl("${GlobalVariable.myApi}/v1/auth/login")
				.setJsonContentTypeHeader()
				.setPayLoad(account)
				.sendPostRequest()
				.verifyStatusCode(200)
				.getToken()
	}

	private static void refreshGSheetToken() {
		Page.nav(GSheetService)
				.initRequestObject()
				.setUrl('https://accounts.google.com/o/oauth2/token')
				.setFormDataContentTypeHeader()
				.setFormDataPayLoad([
					new FormDataBodyParameter('grant_type', 'refresh_token', FormDataBodyParameter.PARAM_TYPE_TEXT),
					new FormDataBodyParameter('client_id', GlobalVariable.gshClientID, FormDataBodyParameter.PARAM_TYPE_TEXT),
					new FormDataBodyParameter('client_secret', GlobalVariable.gshClientSecret, FormDataBodyParameter.PARAM_TYPE_TEXT),
					new FormDataBodyParameter('refresh_token', GlobalVariable.gshRefreshToken, FormDataBodyParameter.PARAM_TYPE_TEXT)
				])
				.sendPostRequest()
				.verifyStatusCode(200)
				.getToken()
	}

	private static void addNewRowToGSheet(String spreadSheetId, String sheetName, List<String> rowData) {
		Page.nav(GSheetService)
				.initRequestObject()
				.setUrl("https://sheets.googleapis.com/v4/spreadsheets/${spreadSheetId}/values/${sheetName}!A2:append")
				.setJsonContentTypeHeader()
				.setBearerAuthorizationHeader(GlobalVariable.gshToken)
				.setParam([
					new TestObjectProperty('valueInputOption', ConditionType.EQUALS, 'RAW'),
					new TestObjectProperty('insertDataOption', ConditionType.EQUALS, 'INSERT_ROWS')
				])
				.setPayLoad(new GSheetValueRange('ROWS', [rowData]))
				.sendPostRequest()
				.verifyStatusCode(200)
	}

	public static void saveTestExecToGSheet(String tcSubId) {
		SeedingData.refreshGSheetToken()
		SeedingData.addNewRowToGSheet(GlobalVariable.testExecSPRId, GlobalVariable.testExecSheetName, [
			GlobalVariable.tsName,
			// testSuiteName
			GlobalVariable.tsStartTime,
			// testSuiteStartTime
			GlobalVariable.tcName,
			// testCaseName
			tcSubId,
			// testCaseSubId
			GlobalVariable.tcStartTime,
			// testCaseStartTime
			new DateTimeUtility().getCurrentDateTime('dd/MM/yyyy HH:mm:ss'),
			// testCaseEndTime
			GlobalVariable.testExecUrl
		])
	}

	private static GSheetValueRange getRowsFromGSheet(String spreadSheetId, String sheetName, String range) {
		return Page
				.nav(GSheetService)
				.initRequestObject()
				.setUrl("https://sheets.googleapis.com/v4/spreadsheets/${spreadSheetId}/values/${sheetName}!${range}")
				.setBearerAuthorizationHeader(GlobalVariable.gshToken)
				.setParam([
					new TestObjectProperty('majorDimension', ConditionType.EQUALS, 'ROWS'),
					new TestObjectProperty('valueRenderOption', ConditionType.EQUALS, 'UNFORMATTED_VALUE'),
					new TestObjectProperty('dateTimeRenderOption', ConditionType.EQUALS, 'FORMATTED_STRING')
				])
				.sendGetRequest()
				.verifyStatusCode(200)
				.getGetRowsResponseBody()
	}

	public static String getTestExecURLFromGSheet(String tcSubId) {
		SeedingData.refreshGSheetToken()
		SeedingData.getRowsFromGSheet(GlobalVariable.testExecSPRId, GlobalVariable.testExecSheetName, 'A2:G1000').values.find { it.get(3) == tcSubId }.get(6)
		// Get the testRunsUrl of the first (the latest) test execution that belongs to the expected tcSubId
	}

	private static void deleteRowsFromGSheet(String spreadSheetId, String sheetName, String range) {
		Page.nav(GSheetService)
				.initRequestObject()
				.setUrl("https://sheets.googleapis.com/v4/spreadsheets/${spreadSheetId}/values/${sheetName}!${range}:clear")
				.setContentLengthHeader('0')
				.setBearerAuthorizationHeader(GlobalVariable.gshToken)
				.sendPostRequest()
				.verifyStatusCode(200)
	}

	public static void deleteAllTestExecFromGSheet() {
		SeedingData.refreshGSheetToken()
		SeedingData.deleteRowsFromGSheet(GlobalVariable.testExecSPRId, GlobalVariable.testExecSheetName, 'A2:G1000')
	}
}
