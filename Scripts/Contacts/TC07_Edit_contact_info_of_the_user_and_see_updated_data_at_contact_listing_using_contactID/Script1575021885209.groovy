import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

'Send Login POST request'
login_response = WS.sendRequest(findTestObject('Login', [('email') : email, ('pass') : pwd]))

'Responsebody'
KeywordUtil.logInfo('Responsebody: ' + login_response.getResponseText())

'Get status code'
def login_staus_code = login_response.getStatusCode()

'Print status code'
println('The status code is: ' + login_staus_code)

'Print login response body'
println(login_response.getResponseBodyContent())

'Create slurper'
def slurper = new JsonSlurper()

'Parse response body'
def result = slurper.parseText(login_response.getResponseBodyContent())

'Check login status code for 200'
if (login_staus_code == 200) {
    'Get Id token from response & save to the global variable login Id token'
    GlobalVariable.LoginIdToken = result.id_token

    'Print global varaiable login Id token'
    println('LoginIdToken: ' + GlobalVariable.LoginIdToken)

    'Log global varaiable login Id token'
    KeywordUtil.logInfo('LoginIdToken: ' + GlobalVariable.LoginIdToken)

    'Send Contact GET request'
    def get_contact_response = WS.sendRequest(findTestObject('Contacts/GetContact'))

    'Get contact status code'
    def get_contact_status_code = get_contact_response.getStatusCode()

    'Log contact status code'
    KeywordUtil.logInfo('Get Contact Status Code: ' + get_contact_status_code)

    'Print contact status code'
    println('Get Contact Status Code: ' + get_contact_status_code)

    'Check get contact status code for 200'
    if (get_contact_status_code == 200) {
        'Get contact response'
        KeywordUtil.logInfo('Get Contact Response: ' + get_contact_response.getResponseText())

        'Print contact response'
        println('Get Contact Response: ' + get_contact_response.getResponseBodyContent())

        'Create slurper'
        def get_contact_slurper = new JsonSlurper()

        'Parse response body'
        def get_contact_result = get_contact_slurper.parseText(get_contact_response.getResponseBodyContent())

        KeywordUtil.markPassed('Contact get successfully')

        
//        String[] test = get_contact_result.id
//
//        for (int i = 0; i < test.length; i++) {
//            println(test[i])
//        }
//        
//        'Assign the first contact ID to the global variable contact ID'
//        GlobalVariable.ContactID = (test[0])
		
		'Assign the first contact ID to the global variable contact ID'
		 GlobalVariable.ContactID = get_contact_result[0].id
		 

        'Print contact ID which going to display'
        println('Following ID Contact is updating: ' + GlobalVariable.ContactID)

        'Log contact ID which going to update'
        KeywordUtil.logInfo('Following ID Contact is updating: ' + GlobalVariable.ContactID)

        'Send UpdateContact request'
        UpdateContact_response = WS.sendRequest(findTestObject('Contacts/UpdateContact', [('value') : value, ('type') : type
                    , ('LoginIdToken') : GlobalVariable.LoginIdToken, ('ContactID') : GlobalVariable.ContactID]))
		
//		UpdateContact_response = WS.sendRequest(findTestObject('Contacts/UpdateContact', [('value') : value, ('type') : type
//			,('ContactID') : GlobalVariable.ContactID]))

        'Get the status code from the UpdateContact request'
        UpdateContact_status_code = UpdateContact_response.getStatusCode()

        'Check UpdateContact status code for 202'
        if (UpdateContact_status_code == 202) {
            KeywordUtil.markPassed('Contact updated successfully')
			
			
			GlobalVariable.ContactTypeID = get_contact_result[0].contactTypeId
			GlobalVariable.ContactValue = get_contact_result[0].value
			
			//////////////////////////////////
			'Send Contact GET request'
			def get_contact_response2 = WS.sendRequest(findTestObject('Contacts/GetContact'))

			'Get contact status code'
			def get_contact_status_code2 = get_contact_response2.getStatusCode()

			'Log contact status code'
			KeywordUtil.logInfo('Get Contact Status Code: ' + get_contact_status_code2)

			'Print contact status code'
			println('Get Contact Status Code: ' + get_contact_status_code2)

			'Check get contact status code for 200'
			if (get_contact_status_code2 == 200) {
				'Get contact response'
				KeywordUtil.logInfo('Get Contact Response2: ' + get_contact_response2.getResponseText())

				'Print contact response'
				println('Get Contact Response: ' + get_contact_response2.getResponseBodyContent())

				'Create slurper'
				def get_contact_slurper2 = new JsonSlurper()

				'Parse response body'
				//def get_contact_result2 = get_contact_slurper2.parseText(get_contact_response2.getResponseBodyContent())
				
				List get_contact_result2 = get_contact_slurper2.parseText(get_contact_response2.getResponseBodyContent())
				
				
				println("list Size: "+get_contact_result2.size())
				println (get_contact_result2)
				
				
//
//				KeywordUtil.markPassed('Contact2 get successfully')
//
//				'Get all contact ID from the response'
              String[] all_contact_id = get_contact_result2.id
				String[] all_contact_type_id = get_contact_result2.contactTypeId
				String[] all_contact_value = get_contact_result2.value
				
				
					
				//println("ALL CONTACT IDS:",+get_contact_result2.)
				
				//println("ALL CONTACT IDS:",+all_contact_id)
				


				for (int i = 0; i < all_contact_id.length; i++) {
					
//					println("aaa"+all_contact_id[i])
//					println("bbb"+all_contact_type_id[i])
//					println("ccc"+all_contact_value[i])
					
					println ('Global variable:'+GlobalVariable.ContactID)
					println ("GlobalVariable.ContactTypeID"+GlobalVariable.ContactTypeID)
					println ("GlobalVariable.ContactValue"+GlobalVariable.ContactValue)

					'Check wheather edited contact present in contact list or not'
//					if ((all_contact_id[i].equals(GlobalVariable.ContactID)) && (all_contact_type_id[i].equals(GlobalVariable.ContactTypeID)) && (all_contact_value[i].equals(GlobalVariable.ContactValue))) {
					if ((all_contact_id[i].equals(GlobalVariable.ContactID))) {
						KeywordUtil.markPassed('Following edited contact present at the current contact listing. ' + GlobalVariable.ContactID)
						
//						for(j=0;j<all_contact_id.length;j++){
					
//				WS.verifyElementPropertyValue(get_contact_response2, "["+j+"].id", GlobalVariable.ContactID)
				
				WS.verifyElementPropertyValue(get_contact_response2,"["+i+"].id", GlobalVariable.ContactID, FailureHandling.CONTINUE_ON_FAILURE)
				WS.verifyElementPropertyValue(get_contact_response2, "["+i+"].contactTypeId", GlobalVariable.ContactTypeID, FailureHandling.CONTINUE_ON_FAILURE)
				WS.verifyElementPropertyValue(get_contact_response2, "["+i+"].value", GlobalVariable.ContactValue, FailureHandling.CONTINUE_ON_FAILURE)
					
//				}
							
						break
						
					} else {
						KeywordUtil.markFailed('Following edited contact not present at the current contact listing. ' + GlobalVariable.ContactID)
					}
				}
         }
		
			
			
			////////////////////////////////////
        } 
			else {
            KeywordUtil.markFailed('UpdateContact_status_code:Expected status code is \'202\' but actual status code is ' + 
                UpdateContact_status_code)
        }
    } else {
        KeywordUtil.markFailed('get_contact_status_code:Expected status code is \'200\' but actual status code is ' + get_contact_status_code)
    }
} else if (login_staus_code == 500) {
    'Check login status code for 500'
    KeywordUtil.markFailed('Login failed')
}

