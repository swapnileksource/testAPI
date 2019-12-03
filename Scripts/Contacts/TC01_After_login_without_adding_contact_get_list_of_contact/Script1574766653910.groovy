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
    'Get Id Token from the response'
    GlobalVariable.LoginIdToken = result.id_token

    'Print Id token'
    println('LoginIdToken: ' + GlobalVariable.LoginIdToken)

    'Log Id token'
    KeywordUtil.logInfo('LoginIdToken: ' + GlobalVariable.LoginIdToken)

    'Send Contact GET request'
    def get_contact_response = WS.sendRequest(findTestObject('Contacts/GetContact'))

    //    KeywordUtil.logInfo('Add Contact Response: ' + get_contact_response.getResponseText())
    //
    //    println('Add Contact Response: ' + get_contact_response.getResponseBodyContent())
    'Get contact status code'
    def get_contact_status_code = get_contact_response.getStatusCode()

    'Log contact status code'
    KeywordUtil.logInfo('Get Contact Status Code: ' + get_contact_status_code)

    'Print contact status code'
    println('Get Contact Status Code: ' + get_contact_status_code)

    'Check get contact status code for 200'
    if (get_contact_status_code == 200) {
		
        'Get contact response'
        KeywordUtil.logInfo('Get Contact Response: ' + get_contact_response.getResponseText())

        'Print contact response'
        println('Get Contact Status Code: ' + get_contact_status_code)

        KeywordUtil.markPassed('Contact get successfully')
    } else {
        KeywordUtil.markFailed('Expected status code is \'200\' but actual status code is ' + get_contact_status_code)
    }
} else if (login_staus_code == 500) {
    'Check login status code for 500'
    KeywordUtil.markFailed('Login failed')
}

