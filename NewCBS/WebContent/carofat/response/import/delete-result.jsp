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
</data>