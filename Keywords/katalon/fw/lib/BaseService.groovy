package katalon.fw.lib

import com.fasterxml.jackson.databind.ObjectMapper
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.FormDataBodyParameter
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpFileBodyContent
import com.kms.katalon.core.testobject.impl.HttpFormDataBodyContent
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
	String createUrl;
	String deleteUrl;
	String updateUrl;


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

	def T setAcceptHeader(String name) {
		httpHeader.add(new TestObjectProperty('Accept', ConditionType.EQUALS, name))
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

	def T setFormDataContentTypeHeader() {
		httpHeader.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'multipart/form-data'))
		return this
	}

	def T setContentLengthHeader(String length) {
		httpHeader.add(new TestObjectProperty('Content-Length', ConditionType.EQUALS, length))
		return this
	}

	def T setPayLoad(Object payload) {
		String payloadStr = payload instanceof String ? payload : parseObjectToString(payload)
		request.setBodyContent(new HttpTextBodyContent(payloadStr))
		return this
	}

	def T setFormDataPayLoad(List<FormDataBodyParameter> params) {
		request.setBodyContent(new HttpFormDataBodyContent(params))
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
		return this
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

	def T create(String url,Object payload) {
		setUrl(url)
		setPayLoad(payload)
		sendPostRequest()
		verifyStatusCode(200)
		return this
	}

	def T create(Object payload) {
		setUrl(createUrl)
		setJsonContentTypeHeader()
		setPayLoad(payload)
		sendPostRequest()
		verifyStatusCode(200)
		if(parseResponseBodyToJsonObject().data != null) {
			GlobalVariable.responseId = parseResponseBodyToJsonObject().data.id
		}
		return this
	}

	def T createWithoutStatusCheck(Object payload) {
		setUrl(createUrl)
		setJsonContentTypeHeader()
		setPayLoad(payload)
		sendPostRequest()
		return this
	}

	def T update(String url,Object payload) {
		setUrl(updateUrl)
		setPayLoad(payload)
		sendPutRequest()
		verifyStatusCode(200)
		return this
	}

	def T deleteWithoutStatusCheck() {
		setUrl(deleteUrl)
		sendDeleteRequest()
		return this
	}

	def T delete() {
		setUrl(deleteUrl)
		sendDeleteRequest()
		verifyStatusCode(200)
		return this
	}
}
