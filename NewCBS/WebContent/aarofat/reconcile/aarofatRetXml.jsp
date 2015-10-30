<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<rows>
<!-- header detail -->
<atmId><s:property value="atmId"/></atmId>
<start><s:date name="start" format="MMM dd, yyyy"/></start>
<end><s:date name="end" format="MMM dd, yyyy"/></end>	
<!-- no debit gl -->
<no-gl-rec><s:property value="smRecord"/></no-gl-rec>
<no-gl-amt>
	<s:text name="format.money">
		<s:param name="value" value="smAmount" />
	</s:text>
</no-gl-amt>
<!-- atm no cash dispense -->
<no-dispense-rec><s:property value="ejRecord"/></no-dispense-rec>
<no-dispense-amt>
	<s:text name="format.money">
		<s:param name="value" value="ejAmount" />
	</s:text>
</no-dispense-amt>
<no-gl-row>
<s:iterator value="smReports" status="stat">
	<row id="<s:property value="#stat.count"/>">
		<count><s:property value="#stat.count"/></count>
		<date><s:date name="date" format="MMM dd, yyyy"/></date>
		<cardnumber><s:property value="cardNumber"/></cardnumber>
		<tsq><s:property value="tsq"/></tsq>
		<amount>
			<s:text name="format.money">
				<s:param name="value" value="amount" />
			</s:text>
		</amount>
		<account>
			<s:property value="accountNumber"/>
		</account>
		<notice><s:property value="notice"/></notice>
		<type><s:property value="type"/></type>
	</row>
</s:iterator>
</no-gl-row>
<no-dispense-row>
<s:iterator value="ejReports" status="stat">
	<row id="<s:property value="#stat.count"/>">
		<count><s:property value="#stat.count"/></count>
		<date><s:date name="date" format="MMM dd, yyyy"/></date>
		<cardnumber><s:property value="cardNumber"/></cardnumber>
		<tsq><s:property value="tsq"/></tsq>
		<amount>
			<s:text name="format.money">
				<s:param name="value" value="amount" />
			</s:text>
		</amount>
		<account>
			<s:property value="accountNumber"/>
		</account>
		<notice><s:property value="notice"/></notice>
		<type><s:property value="type"/></type>
	</row>
</s:iterator>
</no-dispense-row>
</rows>
