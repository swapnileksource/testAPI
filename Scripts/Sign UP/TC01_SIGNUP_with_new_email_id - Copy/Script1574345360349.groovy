import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

def data = TestDataFactory.findTestData(‘testdata’)
data .changeSheet('SignUp')

'Send POST request'
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


'Check status code for 200'
if (signup_status_code == 200) {
	
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
	
	KeywordUtil.markPassed("User registered successfully")
	
} else if (signup_status_code == 500) {

	'Check status code for 500'
	
	println('Error is: ' + result.error)

	WS.verifyElementPropertyValue(response, 'error', 'An account with the given email already exists.', FailureHandling.CONTINUE_ON_FAILURE)

	KeywordUtil.markFailed("An account with the given email already exists.")
	
//	WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
} 
