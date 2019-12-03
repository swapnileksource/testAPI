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

    'Send Contact GET request'
    def get_contact_response = WS.sendRequest(findTestObject('Contacts/GetContact'))

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
        println('Get Contact Response: ' + get_contact_response.getResponseBodyContent())

        'Create slurper'
        def get_contact_slurper = new JsonSlurper()

        'Parse response body'
        def get_contact_result = get_contact_slurper.parseText(get_contact_response.getResponseBodyContent())

        KeywordUtil.markPassed('Contact get successfully')

        
//        String[] test = get_contact_result.id
//
//        for (int i = 0; i < test.length; i++) {
//            println(test[i])
//        }
//        
//        'Assign the first contact ID to the global variable contact ID'
//        GlobalVariable.ContactID = (test[0])
		
		'Assign the first contact ID to the global variable contact ID'
		 GlobalVariable.ContactID = get_contact_result[0].id


        'Print the deleting contact ID'
        println('Following ID Contact delete: ' + GlobalVariable.ContactID)

        KeywordUtil.logInfo('Following ID Contact delete: ' + GlobalVariable.ContactID)

        'Send delete contact request'
        delete_contact_response = WS.sendRequest(findTestObject('Contacts/Delete', [('ContactID') : GlobalVariable.ContactID]))

        'Get the status code from the delete contact request'
        delete_contact_status_code = delete_contact_response.getStatusCode()

        'Check delete contact status code for 204'
        if (delete_contact_status_code == 204) {
            KeywordUtil.markPassed('Contact deleted successfully')
        } else {
            KeywordUtil.markFailed('delete_contact_status_code:Expected status code is \'204\' but actual status code is ' + 
                delete_contact_status_code)
        }
    } else {
        KeywordUtil.markFailed('get_contact_status_code:Expected status code is \'200\' but actual status code is ' + get_contact_status_code)
    }
} else if (login_staus_code == 500) {
    'Check login status code for 500'
    KeywordUtil.markFailed('Login failed')
}

