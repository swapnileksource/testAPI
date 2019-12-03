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
login_response = WS.sendRequest(findTestObject('Login', [('email') : email, ('pass') : pwd]))

KeywordUtil.logInfo('Responsebody: ' + login_response.getResponseText())

'Get status code'
def login_status_code = login_response.statusCode

'Print status code'
println('The status code is: ' + login_status_code)

'Print response body'
println(login_response.getResponseBodyContent())

'Create slurper'
def slurper = new groovy.json.JsonSlurper()

'Parse response body'
def result = slurper.parseText(login_response.getResponseBodyContent())

'Check status code for 200'
if (login_status_code == 200) {
    GlobalVariable.LoginIdToken = result.id_token

    println('IdToken:' + GlobalVariable.LoginIdToken)

    KeywordUtil.logInfo('IdToken: ' + GlobalVariable.LoginIdToken)

    AdminResetPassword_response = WS.sendRequest(findTestObject('AdminResetPassword', [('currentPwd') : currentPwd, ('newPwd') : newPwd]))

    def AdminResetPassword_statusCode = AdminResetPassword_response.statusCode

    if (AdminResetPassword_statusCode == 200) {
		
        KeywordUtil.markPassed('Reset password successful')
		
    } else {
	
        KeywordUtil.markFailed('Password reset failed')
		
    }
	
} else if (login_status_code == 500) {

    KeywordUtil.markFailed('Login failed')
}

