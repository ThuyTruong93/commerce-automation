import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.configurations.CustomFieldEditPopUp
import katalon.testops.configurations.CustomFieldPage
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.CustomFieldDefinitionService

'Pre-condition: Create Custom Field'
def body = Page.nav(CustomFieldDefinitionService).generateData()
customFieldId = Page.nav(CustomFieldDefinitionService).create(body).parseResponseBodyToJsonObject().id
String displayNameUpdated = 'Updated ' + body.get('displayName')
String valueUpdated = 'Updated value'

'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Go to Custom Field page and update the existed custom field'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject).selectConfigurationTab().selectCustomFieldTab()
Page.nav(CustomFieldPage).clickEditIcon(body.get('key'))

'User edits general info [Display name, Key, Values] on Edit Custom Field Popup'
Page.nav(CustomFieldEditPopUp).updateDisplayName(displayNameUpdated).updateValue(valueUpdated).clickSaveChangesButton()

'Verify edit Custom Field successfully'
Page.nav(CustomFieldPage)
	.verifyKeyIsVisible(body.get('key'))
	.verifyDisplayNameIsVisible(displayNameUpdated)
	.verifyValuesIsVisible(valueUpdated)

