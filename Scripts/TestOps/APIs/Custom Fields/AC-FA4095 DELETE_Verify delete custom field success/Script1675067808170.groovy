import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.testops.services.CustomFieldDefinitionService

CustomFieldDefinitionService customFieldDefinitionService = Page.nav(CustomFieldDefinitionService)
String projectId = GlobalVariable.platformGenaralProjectId

'Precondition: Create new custom field'
long customFieldId = customFieldDefinitionService.create(CustomFieldDefinitionService.generateData())
											.verifyStatusCode(200)
											.parseResponseBodyToJsonObject().id

'Verify that the Custom Field is deleted'
def body = [ "id": customFieldId, "projectId": projectId]
customFieldDefinitionService.initRequestObject()
							.setUrl(customFieldDefinitionService.updateDeleteUrl)
							.setJsonContentTypeHeader()
							.setBasicAuthorizationHeaderEncode(email, password)
							.setPayLoad(customFieldDefinitionService.parseObjectToString(body))
							.sendDeleteRequest()
							.verifyStatusCode(200)
							.search(projectId, customFieldId)
							.verifyStatusCode(200)
							.verifyContentIsNull()






	

