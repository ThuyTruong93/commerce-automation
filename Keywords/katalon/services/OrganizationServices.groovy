package katalon.services
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import groovy.json.JsonBuilder
import katalon.model.OrganizationModel as OrgModel

public class OrganizationServices extends BaseService<OrganizationServices> {
	def CREATE_ORG_URL = GlobalVariable.adminUrl + '/api/v1/organizations'
	OrgModel org;
	public OrganizationServices createOrgWithPrefix(String userName, String password) {
		initRequestObject()
				.setUrl(CREATE_ORG_URL)
				.setBasicAuthorizationHeader(userName, password)
				.setJsonContentTypeHeader()
				.setPayLoad(orgWithPrefix(userName))
				.sendPostRequest()
		return this
	}

	//	public OrganizationServices verifyStatusCode() {}

	def orgWithPrefix = {String ownerEmail ->
		String orgHasPrefix = 'TestOps_' + System.currentTimeMillis().toString().substring(0, 7)
		println '---------------' + orgHasPrefix
		org = new OrgModel(orgHasPrefix, ownerEmail)
		println parseObjectToString(org).toString()
		return parseObjectToString(org)
	}

	@Override
	def OrganizationServices verifyStatusCode(int statusCode) {
		assert getResponseStatusCode().equals(statusCode)
		return this
	}

	def OrganizationServices verifyOrgName() {
		Map res = parseResponseBodyToJsonObject()
		assert org.getName().equalsIgnoreCase(res.get('name'))
		return this
	}

}

//.verifyStatusCode()
//.verifyOrgName()