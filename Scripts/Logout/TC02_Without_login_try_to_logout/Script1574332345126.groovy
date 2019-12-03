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

GlobalVariable.LoginIdToken = ""

println('IdToken:' + GlobalVariable.LoginIdToken)

 logout_response = WS.sendRequest(findTestObject('Logout'))
	
	'Get status code'
	def logout_status_code = logout_response.statusCode
	
	'Print status code'
	println('The status code is: ' + logout_status_code)
	
	'Check logout status code for 401'
	if(logout_status_code == 401){
		
		KeywordUtil.markPassed('Without login, user try to logout')
		
	} else {
	
	    KeywordUtil.markFailed("logout:Expected status code is '401' but actual status code is "+logout_status_code)
	}
	
	
