import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

response = WS.sendRequest(findTestObject('Login', [('email') : email, ('pass') : password]))

KeywordUtil.logInfo('Responsebody: ' + response.getResponseText())

'Get status code'
def login_status_code = response.statusCode

'Print status code'
println('The status code is: ' + login_status_code)

'Print response body'
println(response.getResponseBodyContent())

'Create slurper'
def slurper = new groovy.json.JsonSlurper()

'Parse response body'
def result = slurper.parseText(response.getResponseBodyContent())


'Check status code for 400'
if (login_status_code == 400){
	
	
    WS.verifyElementPropertyValue(response, 'ValidationErrors[0].Name', 'Email', FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'ValidationErrors[0].Description', 'Email is required.', FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'ValidationErrors[1].Name', 'Email', FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyElementPropertyValue(response, 'ValidationErrors[1].Description', 'The Email field is not a valid e-mail address.', 
    FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyElementPropertyValue(response, 'Title', 'Request Validation Error', FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyElementPropertyValue(response, 'Status', 400, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyElementPropertyValue(response, 'Detail', 'See ValidationErrors for details', FailureHandling.CONTINUE_ON_FAILURE)
	
    KeywordUtil.markPassed("For login email & password field required")

} else if (login_status_code == 200){

   KeywordUtil.markFailed("Expected status code is '400' but actual status code is "+login_status_code)

}
