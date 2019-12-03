import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

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

    println('Printing Token')
	
	println('IdToken:' + GlobalVariable.LoginIdToken)

    KeywordUtil.logInfo('IdToken: ' + GlobalVariable.LoginIdToken)

    AdminResetPassword_response = WS.sendRequest(findTestObject('AdminResetPassword', [('currentPwd') : currentPwd, ('newPwd') : newPwd]))

    def AdminResetPassword_statusCode = AdminResetPassword_response.statusCode

    if (AdminResetPassword_statusCode == 200) {
		
        KeywordUtil.markPassed('Reset password successful')
		
    } else {
	
        KeywordUtil.markFailed("Expected status code is '200' but actual status code is "+AdminResetPassword_statusCode)
    }
} else if (login_status_code == 500) {

    KeywordUtil.markFailed('Login failed')
}

