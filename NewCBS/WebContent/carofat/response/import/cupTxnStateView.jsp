<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<data>
	<header>
		<result><s:property value="result"/></result>
		<message><s:property value="message"/></message>
		<extend>
			<page><s:property value="page"/></page>
			<total><s:property value="total"/></total>
			<records><s:property value="records"/></records>
		</extend>
	</header>
	<entities>
		<s:iterator value="states" status="stat">
			<entity>
				<id><s:property value="#stat.count"/></id>
				<date><s:date name="date" format="MM/dd/yyyy"/></date>
				<time><s:property value="time"/></time>
				<card><s:property value="card"/></card>
				<trace><s:property value="trace"/></trace>
				<refer><s:property value="refer"/></refer>
				<amount><s:text name="format.money"><s:param name="value" value="amount"/></s:text></amount>
				<fee><s:text name="format.money"><s:param name="value" value="fee"/></s:text></fee>
				<net><s:text name="format.money"><s:param name="value" value="net"/></s:text></net>
				<type><s:property value="type"/></type>
				<terminal><s:property value="terminalId"/></terminal>
				<status><s:property value="status"/></status>
			</entity>
		</s:iterator>
	</entities>
</data>