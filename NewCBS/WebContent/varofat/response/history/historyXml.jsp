<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<history>
	<header>
		<result> 
			<s:property value="result"/>
		</result>
		<message>
			<s:property value="message"/>
		</message>
		<extend>
			<page>
				<s:property value="page"/>
			</page>
			<total>
				<s:property value="total"/>
			</total>
			<records>
				<s:property value="records"/>
			</records>
		</extend>
	</header>
	<data>
	<s:iterator value="visas" status="stat">
		<entity id='<s:property value="#stat.count"/>'>
			<id>
				<s:property value="#stat.count"/>
			</id>
			<batid>
				<s:property value="batId"/>
			</batid>
			<txndate>
				<s:property value="txnDate"/>
			</txndate>
			<txntime>
				<s:property value="txnTime"/>
			</txntime>
			<card>
				<s:property value="card"/>
			</card>
			<retrieval>
				<s:property value="retrieval"/>
			</retrieval>
			<trace>
				<s:property value="trace"/>
			</trace>
			<issuerid>
				<s:property value="issuerId"/>
			</issuerid>
			<type>
				<s:property value="type"/>
			</type>
			<process>
				<s:property value="process"/>
			</process>
			<reason>
				<s:property value="reason"/>
			</reason>
			<response>
				<s:property value="response"/>
			</response>
			<amt>
				<s:text name="format.money"><s:param name="value" value="txnAmt"/></s:text>
			</amt>
			<currency>
				<s:property value="currency"/>
			</currency>
			<settleamt>
				<s:text name="format.money"><s:param name="value" value="settleAmt"/></s:text>
			</settleamt>
			<category>
				<s:property value="category"/>
			</category>
			<group>
				<s:property value="group"/>
			</group>
			<atmid>
				<s:property value="atmId"/>
			</atmid>
			<reference>
				<s:property value="refer"/>
			</reference>
			<settledate>
				<s:date name="settleDate" format="MM/dd/yyyy"/>
			</settledate>
			<status>
				<s:property value="status"/>
			</status>
			<case>
				<s:property value="caseNumber"/>
			</case>
		</entity>
	</s:iterator>
	</data>
</history>