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


'Check status code for 200'
if (login_status_code == 200){

    WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
	KeywordUtil.markPassed("User login successfully")

} else if (login_status_code == 500){

println ("Error is: "+result.error)

String error = result.error

   if (error.equals('User does not exist')){
	
	//when wrong email entered
	WS.verifyElementPropertyValue(response, 'error', 'User does not exist', FailureHandling.CONTINUE_ON_FAILURE)
	KeywordUtil.markFailed("User does not exist")
	
        } else if (error.equals('Incorrect username or password.')){

   //when wrong password entered
   WS.verifyElementPropertyValue(response, 'error', 'Incorrect username or password.', FailureHandling.CONTINUE_ON_FAILURE)
   KeywordUtil.markFailed("Incorrect username or password.")
 
   }


   WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)

}
