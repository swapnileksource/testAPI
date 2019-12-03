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
    
    WS.verifyElementPropertyValue(response, 'error', '1 validation error detected: Value at \'password\' failed to satisfy constraint: Member must have length greater than or equal to 6', 
        FailureHandling.CONTINUE_ON_FAILURE)
	
	KeywordUtil.markPassed("Entered password length is less than 6")
	
	
} else if (signup_status_code == 400) {
    'Check status code for 400'
    WS.verifyElementPropertyValue(response, 'ValidationErrors[0].Name', 'ConfirmPassword', FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'ValidationErrors[0].Description', 'Password and its confirmation do not match.', 
        FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'Title', 'Request Validation Error', FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'Status', 400, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'Detail', 'See ValidationErrors for details', FailureHandling.CONTINUE_ON_FAILURE)
	
	KeywordUtil.markFailed("Expected status code is '500' but actual status code is "+signup_status_code)
	
} else if (signup_status_code == 200) {
    'Check status code for 200'
    println('First name is: ' + result.firstName)

    println('Last name is: ' + result.lastName)

    println('Email is: ' + result.email)

    println('Password is: ' + result.password)

    println('Confirm password is: ' + result.confirmPassword)

    WS.verifyElementPropertyValue(response, 'firstName', fname, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'lastName', lname, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'email', email, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'password', pass, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'confirmPassword', confpass, FailureHandling.CONTINUE_ON_FAILURE)
	
	KeywordUtil.markFailed("Expected status code is '500' but actual status code is "+signup_status_code)
	
}

