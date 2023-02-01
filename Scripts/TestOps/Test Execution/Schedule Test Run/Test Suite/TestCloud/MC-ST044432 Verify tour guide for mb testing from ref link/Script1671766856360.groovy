import com.kms.katalon.core.util.KeywordUtil

'Given a user has already logged in'

'And the user goes to this link {{TO url}}/mobile-referral-link'

'Then verify the tour guide is displayed for the user'

'Given a user has not logged in'

'And the user goes to this link {{TO url}}/mobile-referral-link'

'And the user logs in'

'Then verify the tour guide is displayed for the user'

KeywordUtil.markFailed('Please execute this test manually')