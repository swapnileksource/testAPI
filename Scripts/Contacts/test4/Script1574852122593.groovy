import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

response = WS.sendRequest(findTestObject('Contacts/GetContact'))

def status = response.getStatusCode()

println(status)


println(response.getResponseBodyContent())

def slurper = new JsonSlurper()

//Map result = slurper.parseText(response.getResponseBodyContent())
'Parse response body'
def result = slurper.parseText(response.getResponseBodyContent())

String[] test = result.id

for (int i = 0; i < test.length; i++) {
    println(test[i])
}

GlobalVariable.ContactID = (test[0])

println(GlobalVariable.ContactID)

response_delete = WS.sendRequest(findTestObject('Contacts/Delete', [('ContactID') : GlobalVariable.ContactID]))

del_status_code = response_delete.statusCode

println (del_status_code)
