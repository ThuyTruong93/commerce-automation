import katalon.fw.lib.Page
import katalon.testops.services.CustomFieldDefinitionService

'1. POST - Create new custom field'
String id = Page.nav(CustomFieldDefinitionService).create(CustomFieldDefinitionService.generateData()).parseResponseBodyToJsonObject().id

'2. DELETE - Delete the newest created custom field'
Page.nav(CustomFieldDefinitionService).delete(id).verifyStatusCode(200)

'3. Verify that the Custom Field is deleted'
Page.nav(CustomFieldDefinitionService).search(internal.GlobalVariable.defaultProjectId, id).verifyStatusCode(200).verifyContentIsNull()




	

