<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#clickSubmit').click(function(){
			var vals = [];
			$('#jqTable tr').each(function(){
				var test = $(this).find('td input[type=checkbox]');
				if(test.is(':checked')){
				var index = $(this).find('td:eq(1)').text();
				var amount = $(this).find('td input[type=text]').val();
				var name = $(this).find('td:eq(3)').text();
				var item = $(this).find('td select').val();
				var notice = $(this).find('td:eq(5)').text();
				
				var record = new Record(index,amount,name,item,notice);
				vals.push(record);
				}
			});
			alert(recordXml(vals));
		});
	});
	
	function recordXml(records){
		if(records.length > 0){
			var xmlRecord = "<?xml version='1.0' encoding='utf-8'?>";
			xmlRecord +="<records>";
			var i;
			for(i=0;i<records.length;i++){
				xmlRecord+="<record>";
					var index  = records[i].index;
					var amount = records[i].amount;
					var name   = records[i].name;
					var item   = records[i].item;
					var notice = records[i].notice;
					
					xmlRecord+="<index>"+index+"</index>";
					xmlRecord+="<amount>"+amount+"</amount>";
					xmlRecord+="<name>"+name+"</name>";
					xmlRecord+="<item>"+item+"</item>";
					xmlRecord+="<notice>"+notice+"</notice>";
				xmlRecord+="</record>";
			}
			xmlRecord +="</records>";
			return xmlRecord;
		}
		return "";
	}
	
	function print(record){
		document.write('<p>');
		document.write(record.index);
		document.write(record.amount);
		document.write(record.name);
		document.write(record.item);
		document.write(record.notice);
		document.write('</p>');
	}
	
	function Record(index, amount, name, item, notice){
		this.index = index;
		this.amount = amount;
		this.name = name;
		this.item = item;
		this.notice = notice;
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<table id="jqTable">
		<tr>
			<td>
				<input type="checkbox"/>
			</td>
			<td>1</td>
			<td>
				<input type="text" value="1,500.00"/>
			</td>
			<td>
				name1
			</td>
			<td>
				<select>
					<option value="01">item01</option>
					<option value="02">item02</option>
					<option value="03" selected>item03</option>
					<option value="04">item04</option>
				</select>
			</td>
			<td>
				notice1
			</td>
		</tr>
		<!-- 3 -->
		<tr>
			<td>
				<input type="checkbox"/>
			</td>
			<td>2</td>
			<td>
				<input type="text" value="1,800.00"/>
			</td>
			<td>
				name2
			</td>
			<td>
				<select>
					<option value="01">item01</option>
					<option value="02">item02</option>
					<option value="03">item03</option>
					<option value="04">item04</option>
				</select>
			</td>
			<td>
				notice2
			</td>
		</tr>
		<!-- 3 -->
		<tr>
			<td>
				<input type="checkbox"/>
			</td>
			<td>3</td>
			<td>
				<input type="text" value="1,900.00"/>
			</td>
			<td>
				name3
			</td>
			<td>
				<select>
					<option value="01">item01</option>
					<option value="02">item02</option>
					<option value="03">item03</option>
					<option value="04">item04</option>
				</select>
			</td>
			<td>
				notice3
			</td>
		</tr>
	</table>
	<button id="clickSubmit">click</button>
</body>
</html>