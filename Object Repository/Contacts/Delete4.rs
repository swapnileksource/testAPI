<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Delete4</name>
   <tag></tag>
   <elementGuidId>33cc84e3-b612-450f-a414-90e02deb2885</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
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
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer eyJraWQiOiJOQlwvZnZ4b1lESzZKaVRUZUNkU2lFXC9QMUFQWkc4SloyaEhFWHN3MUZxYWc9IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI4YjhmNzE5NC04Y2E4LTQ2MTgtODQ5MC1iMzUzZmQyMzA4NjUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiY3VzdG9tOmxhc3ROYW1lIjoiYWRhbXMiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV9SZmUwcFA5UDUiLCJjb2duaXRvOnVzZXJuYW1lIjoiOGI4ZjcxOTQtOGNhOC00NjE4LTg0OTAtYjM1M2ZkMjMwODY1IiwiYXVkIjoiMWRuN29qaGNrdmhoMjBtYjFnNTg5MTVxcnAiLCJldmVudF9pZCI6IjU4ZmQ5NzY5LTg0ZTktNGQ5Yi1iZGY5LWU5ZDhkOTFiMGU2OSIsImN1c3RvbTpmaXJzdE5hbWUiOiJiZW4iLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTU3NDg1MjA5MCwiZXhwIjoxNTc0ODU1NjkwLCJpYXQiOjE1NzQ4NTIwOTAsImVtYWlsIjoiY2lvbW9udGVuYTIwMUBtYWlsaW5hdG9yLmNvbSJ9.oLj5YaDAYtSjJtY3BMx-CVEmJjEAas1NnWM1rfUpg5ZExUrWfRu_q65NNOSGA_a3flG7re5uJvBaioUazIR2PmXHwj8t2I-Mr-TZJHgLMCMoaRxngoEL-Yph0V-fo-QtrrP62kh__WtegLRYcddLOis982oWTu15Q_DbnwsKqeMPw0hYX26dxXbNAhgPh_ovMFsBDeTv-R5pdR6GncR0iKAgoWP71Seo8eUmX09bfPL4y1cekjtuppM0vbsQxnw1nQbkZl_HnFLF7PtXGi8mJvmjhsT-zj08rgc2DRj0cMGV1OG95hsJTgGQuIjZe2STLq_4aGwT6poNpVL05YkvqA</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>DELETE</restRequestMethod>
   <restUrl>https://iu3awmrec8.execute-api.us-east-1.amazonaws.com/Prod/api/v1/Contacts/b326e938-9fbb-44e5-b22d-befac19bd4f8</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.ContactID</defaultValue>
      <description></description>
      <id>1eb25212-a17c-45b5-9dbd-3c29e24ee309</id>
      <masked>false</masked>
      <name>ContactID</name>
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
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
