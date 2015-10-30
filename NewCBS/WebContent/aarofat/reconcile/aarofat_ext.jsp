<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<link href="css/flexigrid/flexigrid.css" rel="stylesheet">
<style>
#aux_list td div {
	cursor : pointer;
	color : BLUE;
}

</style>
<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
<script type="text/javascript">
<!--
	
	$('#AUDIT-FETCH').button();
	$('#AUDIT-FETCH').click(function(){
		var id   = $('#ATMID-EXT-DIALOG #atmId').val().trim();
		var name = $('#ATMID-EXT-DIALOG #atmName').val().trim();
		$('#aux_list').flexOptions({
			atmId:id,
			atmName:name
		}).flexReload();
	});
	
	$('#aux_list').flexigrid({
		url : 'aarofat/reconcile/getAtmById.action',
		dataType : 'xml',
		colModel : [ {
			display : 'No.',
			name : 'no.',
			width : 40,
			sortable : true,
			align : 'center'
		}, {
			display : 'ID',
			name : 'id',
			width : 50,
			sortable : true,
			align : 'left'
		}, {
			display : 'Name',
			name : 'name',
			width : 160,
			sortable : true,
			align : 'left'
		}, {
			display : 'IP',
			name : 'ip',
			width : 80,
			sortable : true,
			align : 'left',
		}, {
			display : 'Location',
			name : 'location',
			width : 180,
			sortable : true,
			align : 'left'
		} ],
		usepager : true,
		page : 1,
		title : 'Atm Information',
		useRp : true,
		rp : 10,
		rpOptions: [10, 15, 20, 30, 50, 100],
		atmId:'%',
		atmName:'%',
		showTableToggleBtn : true,
		width : 600,
		height : 260
	});
	
	$("#aux_list tr").live("click", function(){
		var atmId= $(this).find("td:eq(1)").text().trim()+"00";
		$('#AAROFAT-CONTENT #atmId').val(atmId);
		$('#ATMID-EXT-DIALOG').dialog('close');
	});
	
//-->
</script>
<!-- ATM id help input dialog -->
<div id="ATMID-EXT-DIALOG">
	<div id="" style="">
		<label>ATM ID : </label> <input type="text" value="%" size="8" id="atmId"/>
		and <label>ATM Name : </label> <input type="text" value="%" size="8" id="atmName"/>
	</div>
	<div style="text-align: right; margin:5px;">
		<button id="AUDIT-FETCH">Fetch</button>
	</div>
	<div id="ATMID-CONTENT" style="text-align:center;">
		<div id="aux">
			<div id="aux_list"></div>
		</div>
	</div>
</div>
