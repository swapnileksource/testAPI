import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

'Send Login POST request'
login_response = WS.sendRequest(findTestObject('Login', [('email') : email, ('pass') : pwd]))

'Responsebody'
KeywordUtil.logInfo('Responsebody: ' + login_response.getResponseText())

'Get status code'
def login_staus_code = login_response.getStatusCode()

'Print status code'
println('The status code is: ' + login_staus_code)

'Print login response body'
println(login_response.getResponseBodyContent())

'Create slurper'
def slurper = new JsonSlurper()

'Parse response body'
def result = slurper.parseText(login_response.getResponseBodyContent())

'Check login status code for 200'
if (login_staus_code == 200) {
    'Get Id token from response & save to the global variable login Id token'
    GlobalVariable.LoginIdToken = result.id_token

    'Print global varaiable login Id token'
    println('LoginIdToken: ' + GlobalVariable.LoginIdToken)

    'Log global varaiable login Id token'
    KeywordUtil.logInfo('LoginIdToken: ' + GlobalVariable.LoginIdToken)

    'Send Add Contact POST request'
    def add_contact_response = WS.sendRequest(findTestObject('Contacts/AddContact', [('value') : value, ('type') : type]))

    'Log add contact response'
    KeywordUtil.logInfo('Add Contact Response: ' + add_contact_response.getResponseText())

    'Print add contact response'
    println('Add Contact Response: ' + add_contact_response.getResponseBodyContent())

    'Get add contact status code from the response'
    def add_contact_status_code = add_contact_response.getStatusCode()

    'Log add contact status code'
    KeywordUtil.logInfo('Add Contact Status Code: ' + add_contact_status_code)

    'Print add contact status code'
    println('Add Contact Status Code: ' + add_contact_status_code)

    'Check add contact status code for 201'
    if (add_contact_status_code == 201) {
        KeywordUtil.markPassed('Contact added successfully')
    } else {
        KeywordUtil.markFailed('Expected status code is \'201\' but actual status code is ' + add_contact_status_code)
    }
} else if (login_staus_code == 500) {
    'Check login status code for 500'
    KeywordUtil.markFailed('Login failed')
}

