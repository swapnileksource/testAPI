<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Delete - Copy - Copy</name>
   <tag></tag>
   <elementGuidId>6e27d7ac-34ff-43b7-a3bd-365c9f7219d1</elementGuidId>
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
      <value>Bearer eyJraWQiOiJOQlwvZnZ4b1lESzZKaVRUZUNkU2lFXC9QMUFQWkc4SloyaEhFWHN3MUZxYWc9IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiI4YjhmNzE5NC04Y2E4LTQ2MTgtODQ5MC1iMzUzZmQyMzA4NjUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiY3VzdG9tOmxhc3ROYW1lIjoiYWRhbXMiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV9SZmUwcFA5UDUiLCJjb2duaXRvOnVzZXJuYW1lIjoiOGI4ZjcxOTQtOGNhOC00NjE4LTg0OTAtYjM1M2ZkMjMwODY1IiwiYXVkIjoiMWRuN29qaGNrdmhoMjBtYjFnNTg5MTVxcnAiLCJldmVudF9pZCI6IjA3ZDU4N2Y2LTg1ZGItNDljNi05OTAwLTA2YjhlMzgwZTM5MiIsImN1c3RvbTpmaXJzdE5hbWUiOiJiZW4iLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTU3NDgzNzc3MSwiZXhwIjoxNTc0ODQxMzcxLCJpYXQiOjE1NzQ4Mzc3NzEsImVtYWlsIjoiY2lvbW9udGVuYTIwMUBtYWlsaW5hdG9yLmNvbSJ9.gyVsKLFSc8iflJb89nPLE6g6P3FyyhUOkZ5rfnCfN3yxSWd3jsyweJ1EYNrS8u2sk2VPpF-U6AQnbWegmpM1N4S5xeG4qWqrgu-I3wuE-mOJCOHEknJzorGQVqii92vlLt3YBjoe1ZR9Q5s8KcV1JOV0D1GYsyX78NxJoQyOOevjOFq42fWoe1MgkHIX_2mCOmIpK-88PNBbHgHl-1hjGB_mBLjQq1T50xKkQRYlXZdNEOBuRY5WQJVaam32CxrhCad6XiRZCNy4cRAoZmrLMw5GmrvsncaEgrH76W7ZNCUfq9dgEoGzmwvXhELmEJ3FKqvpwQyipKBCKfHb9_KsiQ</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>DELETE</restRequestMethod>
   <restUrl>https://iu3awmrec8.execute-api.us-east-1.amazonaws.com/Prod/api/v1/Contacts/${ContactID}</restUrl>
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
