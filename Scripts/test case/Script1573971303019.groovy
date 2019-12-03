import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

def response = WS.sendRequest(findTestObject('Signup - Copy', [('fname') : fname, ('lname') : lname, ('email') : email
            , ('pass') : pass, ('confpass') : confpass]))

def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(response.getResponseBodyContent())

println('The response is: ' + response)

String status_code = response
String[] actual_status_code = status_code.split(" ")

for(int i=0;i<actual_status_code.length;i++)
{
	println (actual_status_code[i])
	
	if (actual_status_code[0].equals("200"))
	break;
}

if (actual_status_code[0].equals("200")){
	
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
	


} else{

println ("Error is: "+result.error)

WS.verifyElementPropertyValue(response, 'error', 'An account with the given email already exists.', FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)

}


//////////////////////////////////////////////////////////////////
//println('The response is: ' + actual_response)
//println(actual_response.getResponseBodyContent())
//
//def slurper = new groovy.json.JsonSlurper()
//def result = slurper.parseText(actual_response.getResponseBodyContent())
//
//def expected_response = 500
//
//if (actual_response == 500) {
//	
//	println ("a")
//}
//	
//println ("First name is: "+result.firstName)
//println ("Last name is: "+result.lastName)
//println ("Email is: "+result.email)
//println ("Password is: "+result.password)
//println ("Confirm password is: "+result.confirmPassword)
//	
//  WS.verifyElementPropertyValue(actual_response, 'firstName', fname, FailureHandling.CONTINUE_ON_FAILURE)
//  WS.verifyElementPropertyValue(actual_response, 'lastName', lname, FailureHandling.CONTINUE_ON_FAILURE)
//	
//	WS.verifyElementPropertyValue(actual_response, 'email', email, FailureHandling.CONTINUE_ON_FAILURE)
//	
//	WS.verifyElementPropertyValue(actual_response, 'password', pass, FailureHandling.CONTINUE_ON_FAILURE)
//	
//	WS.verifyElementPropertyValue(actual_response, 'confirmPassword', confpass, FailureHandling.CONTINUE_ON_FAILURE)
//	
//
////    WS.verifyResponseStatusCode(actual_response, 200, FailureHandling.CONTINUE_ON_FAILURE)
//	
//	
////    assert true
//	
//else {
//	println ("b")
//// println ("Error is: "+result.error)
// 
//
//WS.verifyElementPropertyValue(actual_response, 'error', 'An account with the given email already exists.', FailureHandling.STOP_ON_FAILURE)
//// WS.verifyResponseStatusCode(actual_response, 200, FailureHandling.CONTINUE_ON_FAILURE)
//assert false
//}
//
//
//
//
//
