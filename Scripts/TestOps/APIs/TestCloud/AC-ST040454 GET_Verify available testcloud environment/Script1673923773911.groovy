import com.kms.katalon.core.annotation.SetUp

import internal.GlobalVariable
import katalon.common.SeedingData
import katalon.fw.lib.Page
import katalon.services.ExecutionEnvService
import ro.skyah.comparator.CompareMode as CompareMode

'Call get testcloud environment API without authorization and verify the status code and response body content'
Page.nav(ExecutionEnvService).initRequestObject()
							 .setUrl("$GlobalVariable.testCloudUrl/environments")
							 .setHeader('TC-Channel', 'G5')
							 .sendGetRequest()
							 .verifyStatusCode(401)
							 .verifyResponseBodyContentEqual('KatOne\'s authentication failed!')

'Set up the expected environment'
String expected = 
"""
[
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "107"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "106"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "105"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "104"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "103"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "102"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "101"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "100"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "99"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "98"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "97"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "96"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "95"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "94"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "93"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "92"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "91"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "90"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "89"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "88"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "107"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "106"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "105"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "104"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "103"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "102"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "101"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "100"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "99"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "98"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "97"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "96"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "95"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "94"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "93"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "92"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "91"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "90"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "msedge",
        "browserDisplayName": "Edge Chromium",
        "browserVersion": "89"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "106"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "105"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "104"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "103"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "102"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "101"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "100"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "99"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "98"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "97"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "96"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "95"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "94"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "93"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "92"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "91"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "90"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "89"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "88"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "87"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "86"
    },
    {
        "id": ".*",
        "os": "windows",
        "osDisplayName": "Windows",
        "browser": "internet explorer",
        "browserDisplayName": "Internet Explorer",
        "browserVersion": "11"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "107"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "106"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "105"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "104"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "103"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "102"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "101"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "100"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "99"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "98"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "97"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "96"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "95"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "94"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "93"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "92"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "91"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "90"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "89"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "chrome",
        "browserDisplayName": "Chrome",
        "browserVersion": "88"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "106"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "105"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "104"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "103"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "102"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "101"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "100"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "99"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "98"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "97"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "96"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "95"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "94"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "93"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "92"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "91"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "90"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "89"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "88"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "87"
    },
    {
        "id": ".*",
        "os": "linux",
        "osDisplayName": "Linux",
        "browser": "firefox",
        "browserDisplayName": "Firefox",
        "browserVersion": "86"
    }
]
"""

'Call testcloud environment API verify the response contains the expected environment'
Page.nav(ExecutionEnvService).initRequestObject()
							 .setUrl("$GlobalVariable.testCloudUrl/environments")
							 .setHeader('TC-Channel', 'G5')
							 .setBearerAuthorizationHeader()
							 .sendGetRequest()
							 .verifyStatusCode(200)
							 .verifyJSONResponseBodyEqual(expected, CompareMode.JSON_ARRAY_NON_EXTENSIBLE, CompareMode.JSON_OBJECT_NON_EXTENSIBLE)
							  
@SetUp
def setUp() {
	SeedingData.setUpKatOneToken(GlobalVariable.owner_mail, GlobalVariable.owner_pass)
}
