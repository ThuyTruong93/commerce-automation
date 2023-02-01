package katalon.fw.lib

import com.fasterxml.jackson.databind.ObjectMapper
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpFileBodyContent
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.common.RestfulClient
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.util.CryptoUtil

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import internal.GlobalVariable
import ro.skyah.comparator.CompareMode
import ro.skyah.comparator.JSONCompare

public class BaseService <T> {

	RequestObject request;
	ResponseObject response;
	ArrayList httpHeader;

	def T initRequestObject() {
		request = new RequestObject()
		httpHeader = new ArrayList()
		return this
	}

	def T setUrl(String url) {
		request.setRestUrl(url)
		return this
	}

	def T setBasicAuthorizationHeader(String token = GlobalVariable.encodedToken) {
		httpHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, token))
		return this
	}

	def T setBasicAuthorizationHeader(String username, String password) {
		String token = "$username:$password".bytes.encodeBase64().toString()
		httpHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, "Basic $token"))
		return this
	}

	def T setBasicAuthorizationHeaderEncode(String username, String encodepwd) {
		String password = CryptoUtil.decode(CryptoUtil.getDefault(encodepwd))
		String token = "$username:$password".bytes.encodeBase64().toString()
		httpHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, "Basic $token"))
		return this
	}

	def T setBasicTokenAuthorizationHeader(String token = GlobalVariable.basicToken) {
		httpHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, "Basic $token"))
		return this
	}


	def T setBearerAuthorizationHeader(String token = GlobalVariable.encodedToken) {
		httpHeader.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, "Bearer $token"))
		return this
	}

	def T setJsonContentTypeHeader() {
		httpHeader.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
		return this
	}
	
	def T setHeader(String headerName, String headerValue) {
		httpHeader.add(new TestObjectProperty(headerName, ConditionType.EQUALS, headerValue))
		return this
	}

	def T setPayLoad(Object payload) {
		String payloadStr = payload instanceof String ? payload : parseObjectToString(payload)
		request.setBodyContent(new HttpTextBodyContent(payloadStr))
		return this
	}

	def T setParam(List<TestObjectProperty> params) {
		request.setRestParameters(params)
		RestfulClient.processRequestParams(request)
		return this
	}

	def T sendGetRequest() {
		request.setHttpHeaderProperties(httpHeader)
		request.setRestRequestMethod('GET')
		response = WS.sendRequest(request, FailureHandling.STOP_ON_FAILURE)
		return this
	}

	def T sendPostRequest() {
		request.setHttpHeaderProperties(httpHeader)
		request.setRestRequestMethod('POST')
		response = WS.sendRequest(request, FailureHandling.STOP_ON_FAILURE)
		return this
	}

	def T sendPutRequest() {
		request.setHttpHeaderProperties(httpHeader)
		request.setRestRequestMethod('PUT')
		response = WS.sendRequest(request)
	}

	def T sendDeleteRequest() {
		request.setHttpHeaderProperties(httpHeader)
		request.setRestRequestMethod('DELETE')
		response = WS.sendRequest(request, FailureHandling.STOP_ON_FAILURE)
		return this
	}

	def T verifyStatusCode (int statusCode) {
		WS.verifyResponseStatusCode(response, statusCode)
		return this
	}

	def int getResponseStatusCode() {
		return response.getStatusCode()
	}

	def T verifyResponseBodyContentEqual(String expected) {
		String actual = response.responseBodyContent
		WS.verifyEqual(actual, expected)
		return this
	}

	def T verifyJSONResponseBodyEqual(String expected, CompareMode... compareModes) {
		String actual = response.responseBodyContent
		JSONCompare.assertEquals(expected, actual, compareModes)
		return this
	}

	def T verifyContentIsNull () {
		boolean check = parseResponseBodyToJsonObject().content == null || parseResponseBodyToJsonObject().content == []
		WS.verifyEqual(check, true)
		return this
	}

	def String parseObjectToString(Object payload) {
		return JsonOutput.toJson(payload).toString()
	}

	def Object parseResponseBodyToJsonObject() {
		def jsonSlurper = new JsonSlurper()
		return jsonSlurper.parseText(response.getResponseText())
	}

	def Object parseResponseBodyToClassObject(def clazz) {
		return new ObjectMapper().readValue(response.getResponseBodyContent(), clazz)
	}

	def T withFileBodyContent(String filePath) {
		HttpFileBodyContent fileBodyContent = new HttpFileBodyContent(filePath)
		request.setBodyContent(fileBodyContent)
		return this
	}
}
