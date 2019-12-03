import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('Signup', [('fname') : fname, ('lname') : lname, ('email') : email
	, ('pass') : pass, ('confpass') : confpass]))

KeywordUtil.logInfo('Responsebody: ' + response.getResponseText())

'Get status code'
def signup_status_code = response.statusCode

'Print status code'
println('The status code is: ' + signup_status_code)

'Print response body'
println(response.getResponseBodyContent())

'Create object of jsonslurper'
def slurper = new JsonSlurper()

'Parse response body'
def result = slurper.parseText(response.getResponseBodyContent())


'Check status code for 200'
if (signup_status_code == 200){

println ("First name is: "+result.firstName)
println ("Last name is: "+result.lastName)
println ("Email is: "+result.email)
println ("Password is: "+result.password)
println ("Confirm password is: "+result.confirmPassword)

WS.verifyElementPropertyValue(response, 'firstName', fname, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(response, 'lastName', lname, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(response, 'email', email, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(response, 'password', pass, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyElementPropertyValue(response, 'confirmPassword', confpass, FailureHandling.CONTINUE_ON_FAILURE)

def login_response = WS.sendRequest(findTestObject('Login', [('email') : email, ('pass') : pass]), FailureHandling.CONTINUE_ON_FAILURE)

KeywordUtil.logInfo('Responsebody: ' + login_response.getResponseText())


'Get status code'
def login_status_code = login_response.statusCode

'Print status code'
println('The status code is: ' + login_status_code)

'Print response body'
println(login_response.getResponseBodyContent())

'Check status code for 500'
if (login_status_code == 500){
	
	WS.verifyElementPropertyValue(login_response, 'error', 'User is not confirmed.', FailureHandling.CONTINUE_ON_FAILURE)
	
	KeywordUtil.markPassed("Without activating user account, user can not login")
	
  } else if (login_status_code == 200){
  
  KeywordUtil.markFailed("User registered successfully")
  
	}
  
}else if (signup_status_code == 500){

'Check status code for 500'
println ("Error is: "+result.error)

WS.verifyElementPropertyValue(response, 'error', 'An account with the given email already exists.', FailureHandling.CONTINUE_ON_FAILURE)

KeywordUtil.markFailed("An account with the given email already exists.")

//WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)

}


