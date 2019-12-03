<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Signup - Copy</name>
   <tag></tag>
   <elementGuidId>70d7783b-e405-4254-8f40-d50eb7e80560</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;firstName\&quot;: \&quot;\&quot;,\n  \&quot;lastName\&quot;: \&quot;\&quot;,\n  \&quot;email\&quot;: \&quot;\&quot;,\n  \&quot;password\&quot;: \&quot;\&quot;,\n  \&quot;confirmPassword\&quot;: \&quot;\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://iu3awmrec8.execute-api.us-east-1.amazonaws.com/Prod/api/v1/Accounts/signup</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'martin'</defaultValue>
      <description></description>
      <id>ebee211e-cbc1-4074-b17c-97bee6f9ac73</id>
      <masked>false</masked>
      <name>fname</name>
   </variables>
   <variables>
      <defaultValue>'guptil'</defaultValue>
      <description></description>
      <id>f44758bf-ed22-42d8-9787-1036bf363691</id>
      <masked>false</masked>
      <name>lname</name>
   </variables>
   <variables>
      <defaultValue>'marti1n@abc.com'</defaultValue>
      <description></description>
      <id>8ffcc0dd-1407-4a3f-8e47-84ad9cce9fbe</id>
      <masked>false</masked>
      <name>email</name>
   </variables>
   <variables>
      <defaultValue>'123456'</defaultValue>
      <description></description>
      <id>99c5bc59-f655-4417-ae7b-7c0e5400faba</id>
      <masked>false</masked>
      <name>pass</name>
   </variables>
   <variables>
      <defaultValue>'123456'</defaultValue>
      <description></description>
      <id>acd44ffc-6e7e-45b6-bca5-ec79bf1bdfbc</id>
      <masked>false</masked>
      <name>confpass</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()



WS.verifyResponseStatusCode(response, 200)

assertThat(response.getStatusCode()).isEqualTo(200)




WS.verifyElementPropertyValue(response, 'firstName', '')
WS.verifyElementPropertyValue(response, 'lastName', '')
WS.verifyElementPropertyValue(response, 'email', '')
WS.verifyElementPropertyValue(response, 'password', '')
WS.verifyElementPropertyValue(response, 'confirmPassword', '')
WS.verifyElementPropertyValue(response, 'error', &quot;An account with the given email already exists.&quot;)
WS.verifyElementPropertyValue(response, 'ValidationErrors[0].Name', &quot;Email&quot;)</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
