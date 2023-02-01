import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable
import katalon.common.SeedingData
import katalon.fw.lib.Page
import katalon.model.Device
import katalon.services.ExecutionEnvService

String url = "${GlobalVariable.testCloudUrl}ees/devices"

'Call get mobile devices API without authorization and verify the status code and response body content'
Page.nav(ExecutionEnvService)
	.initRequestObject()
	.setUrl(url)
	.sendGetRequest()
	.verifyStatusCode(401)
	.verifyResponseBodyContentEqual("KatOne's authentication failed!")
	
'Set up the expected devices (at least one android of LambdaTest, one iOS of LambdaTest, one android of SauceLabs, one iOS of SauceLabs'
String expected = """
[
    {
        "id": ".*",
        "isTablet": false,
        "manufacturer": [
            ".*"
        ],
        "name": ".*",
        "os": "ANDROID",
        "deviceFamily": ".*",
        "capabilities": [
            {
                "version": ".*",
                "provider": "sc",
                "deviceId": ".*",
                "deviceName": ".*"
            }
        ]
    },
	{
        "id": ".*",
        "isTablet": false,
        "manufacturer": [
            ".*"
        ],
        "name": ".*",
        "os": "IOS",
        "deviceFamily": ".*",
        "capabilities": [
            {
                "automation": "1",
                "id": ".*",
                "manual": "0",
                "name": ".*",
                "version": ".*",
                "provider": "lt",
                "deviceId": ".*",
                "deviceName": ".*"
            },
            {
                "version": ".*",
                "provider": "sc",
                "deviceId": ".*",
                "deviceName": ".*"
            }
        ]
    },
	{
        "id": ".*",
        "isTablet": true,
        "manufacturer": [
            ".*"
        ],
        "name": ".*",
        "os": "IOS",
        "deviceFamily": ".*",
        "capabilities": [
            {
                "automation": "1",
                "id": ".*",
                "manual": "1",
                "name": ".*",
                "version": ".*",
                "provider": "lt",
                "deviceId": ".*",
                "deviceName": ".*"
            },
            {
                "version": ".*",
                "provider": "sc",
                "deviceId": ".*",
                "deviceName": ".*"
            }
        ]
    },
	{
        "id": ".*",
        "isTablet": false,
        "manufacturer": [
            ".*"
        ],
        "name": ".*",
        "os": "ANDROID",
        "deviceFamily": ".*",
        "capabilities": [
            {
                "automation": "1",
                "id": ".*",
                "manual": "1",
                "name": ".*",
                "version": ".*",
                "provider": "lt",
                "deviceId": ".*",
                "deviceName": ".*"
            }
        ]
    }
]
"""
	
'Call get mobile devices API with availableOnly = true and verify the response contains the expected devices'	
List<Device> availableOnlyDevices = Page.nav(ExecutionEnvService)
	.initRequestObject()
	.setUrl(url)
	.setParam([new TestObjectProperty('availableOnly', ConditionType.EQUALS, 'true')])
	.setBearerAuthorizationHeader()
	.sendGetRequest()
	.verifyStatusCode(200)
	.verifyJSONResponseBodyEqual(expected)
	.getResponseBody()
	
'Call get mobile devices API with availableOnly = false and verify the response contains the expected devices'
List<Device> allDevices = Page.nav(ExecutionEnvService)
	.initRequestObject()
	.setUrl(url)
	.setParam([new TestObjectProperty('availableOnly', ConditionType.EQUALS, 'false')])
	.setBearerAuthorizationHeader()
	.sendGetRequest()
	.verifyStatusCode(200)
	.verifyJSONResponseBodyEqual(expected)
	.getResponseBody()
	
'Verify list of all devices has more devices than list of only available devices'
Page.nav(ExecutionEnvService).compareAllDevicesListToOnlyAvailableList(allDevices, availableOnlyDevices)

'Call get mobile devices API without availableOnly param'
allDevices = Page.nav(ExecutionEnvService)
	.initRequestObject()
	.setUrl(url)
	.setBearerAuthorizationHeader()
	.sendGetRequest()
	.verifyStatusCode(200)
	.getResponseBody()
	
'Verify when no availableOnly param, all devices are returned'
Page.nav(ExecutionEnvService).compareAllDevicesListToOnlyAvailableList(allDevices, availableOnlyDevices)

'Call get mobile devices API with availableOnly = invalid'
allDevices = Page.nav(ExecutionEnvService)
	.initRequestObject()
	.setUrl(url)
	.setParam([new TestObjectProperty('availableOnly', ConditionType.EQUALS, 'invalid')])
	.setBearerAuthorizationHeader()
	.sendGetRequest()
	.verifyStatusCode(200)
	.getResponseBody()
	
'Verify when availableOnly param is invalid, all devices are returned'
Page.nav(ExecutionEnvService).compareAllDevicesListToOnlyAvailableList(allDevices, availableOnlyDevices)

@SetUp
def setUp() {
	SeedingData.setUpKatOneToken(GlobalVariable.owner_mail, GlobalVariable.owner_pass)
}
