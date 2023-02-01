import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.configurations.CustomFieldPage
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.CustomFieldDefinitionService as CustomFieldDefinitionService

'Pre-condition: Has at least 1 existed Custom Field'
def body = Page.nav(CustomFieldDefinitionService).generateData()
customFieldId = Page.nav(CustomFieldDefinitionService).create(body).parseResponseBodyToJsonObject().id

'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject).selectConfigurationTab().selectCustomFieldTab()

'Go to Custom Field page and delete the existed custom field'
Page.nav(CustomFieldPage).clickDeleteIcon(body.get('key')).clickDeleteButton()

'Verify delete Custom Field successfully'
Page.nav(CustomFieldPage).verifyCustomFieldIsNotDisplay(body.get('key'))
Page.nav(CustomFieldDefinitionService).search(GlobalVariable.platformGenaralProjectId, customFieldId).verifyStatusCode(200).verifyContentIsNull()


