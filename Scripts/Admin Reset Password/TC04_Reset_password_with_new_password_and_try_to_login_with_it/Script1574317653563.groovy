import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.RestRequestObjectBuilder as RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper

'Send POST request'
login_response1 = WS.sendRequest(findTestObject('Login', [('email') : email, ('pass') : pwd]))

KeywordUtil.logInfo('Responsebody: ' + login_response1.getResponseText())

'Get status code'
def login_status_code1 = login_response1.statusCode

'Print status code'
println('The status code is: ' + login_status_code1)

'Print response body'
println(login_response1.getResponseBodyContent())

'Create slurper'
def slurper1 = new groovy.json.JsonSlurper()

'Parse response body'
def result1 = slurper1.parseText(login_response1.getResponseBodyContent())

'Check status code for 200'
if (login_status_code1 == 200) {
    GlobalVariable.LoginIdToken = result1.id_token

    println('IdToken:' + GlobalVariable.LoginIdToken)

    KeywordUtil.logInfo('IdToken: ' + GlobalVariable.LoginIdToken)

    AdminResetPassword_response = WS.sendRequest(findTestObject('AdminResetPassword', [('currentPwd') : currentPwd, ('newPwd') : newPwd]))

    def AdminResetPassword_statusCode = AdminResetPassword_response.statusCode

    if (AdminResetPassword_statusCode == 200) {
		
        KeywordUtil.markPassed('Reset password successful')
		
		login_response2 = WS.sendRequest(findTestObject('Login', [('email') : email, ('pass') : newPwd]))
		
		KeywordUtil.logInfo('Responsebody: ' + login_response2.getResponseText())
		
		'Get status code'
		def login_status_code2 = login_response2.statusCode
		
		'Print status code'
		println('The status code is: ' + login_status_code2)
		
		'Print response body'
		println(login_response2.getResponseBodyContent())
		
		'Create slurper'
		def slurper2 = new groovy.json.JsonSlurper()
		
		'Parse response body'
		def result2 = slurper2.parseText(login_response2.getResponseBodyContent())
		
		
		'Check status code for 200'
		if (login_status_code2 == 200){
		
			WS.verifyResponseStatusCode(login_response2, 200, FailureHandling.STOP_ON_FAILURE)
			KeywordUtil.markPassed("User login successfully")
		
		} else if (login_status_code2 == 500){
		
		println ("Error is: "+result2.error)
		
		String error = result2.error
		
		   if (error.equals('User does not exist')){
			
			//when wrong email entered
			WS.verifyElementPropertyValue(login_response2, 'error', 'User does not exist', FailureHandling.CONTINUE_ON_FAILURE)
			KeywordUtil.markFailed("User does not exist")
			
				} else if (error.equals('Incorrect username or password.')){
		
		   //when wrong password entered
		   WS.verifyElementPropertyValue(login_response2, 'error', 'Incorrect username or password.', FailureHandling.CONTINUE_ON_FAILURE)
		   KeywordUtil.markFailed("Incorrect username or password.")
		 
		   }
		
		
		   WS.verifyResponseStatusCode(login_response2, 200, FailureHandling.STOP_ON_FAILURE)
		
		}
		
    } else {
	
        KeywordUtil.markFailed("AdminResetPassword:Expected status code is '200' but actual status code is "+AdminResetPassword_statusCode)
    }
} else if (login_status_code1 == 500) {

    KeywordUtil.markFailed('Login failed')
}

