import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

def response = WS.sendRequest(findTestObject('Signup', [('fname') : fname, ('lname') : lname, ('email') : email, ('pass') : pass
            , ('confpass') : confpass]))

KeywordUtil.logInfo('Responsebody: ' + response.getResponseText())

'Get status code'
def signup_status_code = response.statusCode

'Print status code'
println('The status code is: ' + signup_status_code)

'Print response body'
println(response.getResponseBodyContent())

'Create slurper'
def slurper = new groovy.json.JsonSlurper()

'Parse response body'
def result = slurper.parseText(response.getResponseBodyContent())

'Check status code for 500'
if (signup_status_code == 500) {
	
    println('Error is: ' + result.error)

    WS.verifyElementPropertyValue(response, 'error', 'An account with the given email already exists.', FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyResponseStatusCode(response, 500, FailureHandling.STOP_ON_FAILURE)
	
	KeywordUtil.markPassed("An account with the given email already exists.")
	
} else if (signup_status_code == 200) {

   KeywordUtil.markFailed("Expected status code is '500' but actual status code is "+signup_status_code)
   
//    WS.verifyResponseStatusCode(response, 500, FailureHandling.STOP_ON_FAILURE)
}

