<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%><?xml version="1.0" encoding="utf-8"?><%@ taglib prefix="s" uri="/struts-tags" %>
<rows>
	<page>
		<s:property value="page"/>
	</page>
	<total>
		<s:property value="total"/>
	</total>
	<s:iterator value="atmList" status="stat">
	<row id='<s:property value="#stat.count"/>'>
		<cell>
			<![CDATA[<s:property value="#stat.count"/>]]>
		</cell>
		<cell>
			<![CDATA[${atmId}]]>
		</cell>	
		<cell>
			<![CDATA[${atmName}]]>
		</cell>
		<cell>
			<![CDATA[${atmIp}]]>
		</cell>
		<cell>
			<![CDATA[${atmLocation}]]>
		</cell>
	</row>
	</s:iterator>
</rows>