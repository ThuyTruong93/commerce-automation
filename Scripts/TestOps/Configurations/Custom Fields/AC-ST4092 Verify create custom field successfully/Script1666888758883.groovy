import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.configurations.CustomFieldCreatePopUp
import katalon.testops.configurations.CustomFieldPage
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.CustomFieldDefinitionService

String random = UUID.randomUUID().toString().substring(0, 3)
String key = 'auto-' + random
String displayName = 'Auto ' + random
String value = 'value ' + random

'1. Login to TO as admin account'
Page.nav(SignInPage).enterCredential(internal.GlobalVariable.owner_mail, internal.GlobalVariable.owner_pass).clickSignIn()

'2. Go to Custom Field page and create new custom field'
Page.nav(DashboardPage).selectProject(internal.GlobalVariable.platformGeneralProject).selectConfigurationTab().selectCustomFieldTab()
Page.nav(CustomFieldPage).clickCreateBtn()

'3. User inputs general info [Display name, Key, Values] on Create New Custom Field Popup'
Page.nav(CustomFieldCreatePopUp).inputKey(key).inputDisplayName(displayName).inputValue(value).clickCreateButton()

'4. Verify create Custom Field successfully'
Page.nav(CustomFieldPage).verifyKeyIsVisible(key).verifyDisplayNameIsVisible(displayName).verifyValuesIsVisible(value)

