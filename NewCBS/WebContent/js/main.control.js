$(function() {

	$('#accordion').accordion({
		heightStyle : 'fill'
	});

	$("#navigation").treeview({
		collapsed : true
	});

	$("#menu_part").resizable();

	$('#PROCESSING-DIALOG').dialog({
		title : 'PROCESSING',
		autoOpen : false,
		width : 500,
		height : 400,
		position : [ 270, 50 ],
		modal : true,
		open : function() {
			$('#ALERT-DAILOG-RESULT').load('processing.html');
		}
	});

	$('#ALERT-DIALOG').dialog({
		title : 'ALERT',
		autoOpen : false,
		width : 500,
		height : 400,
		position : [ 270, 50 ],
		modal : true
	});

	$('#ADMIN-FUNC-FORM').dialog({
		title : 'ALERT',
		autoOpen : false,
		width : 500,
		height : 400,
		position : [ 270, 50 ],
		modal : true,
		buttons : {
			'Exit' : function() {
				$(this).dialog('close');
			}
		}
	});

	$('#ADMIN-PROFILE').dialog({
		title : 'USER PROFILE',
		autoOpen : false,
		width : 500,
		height : 200,
		position : [ 270, 50 ],
		modal : true,
		buttons : {
			'Exit' : function() {
				$(this).dialog('close');
			}
		}
	});
	$('#GLOBAL-ERROR').dialog({
		title : 'ERROR-MESSAGE',
		autoOpen:false,
		modal:true,
		width:500,
		height:200,
		buttons:{
			'Exit':function(){
				$(this).dialog('close');
			}
		},
		open:function(){
			
			var message = '<div class="ui-state-error" style="width:300px;hieght:100px;margin:30px 50px 10px 50px; font-size:13px;">'+
				'<span class="ui-icon ui-icon-info" style="display:inline-block;"></span>'+
				'<strong>Alert : </strong>INVALID FUNCTION NAME</div>';
			$(this).empty();
			$(this).append(message);
		}
	});
	
	$('#PROFILE-BUTT').click(function() {
		$('#ADMIN-PROFILE').dialog('open');
	});

	$('#GO-BUTT').click(function(){
		openFunc();
	});
	
	$('#funcId').bind('keypress',function(event){
		if (event.keyCode === 13){
		    openFunc();
		 }
	});
	
	$('#CPASSWD-BUTT').click(function(){
		$('#ADMIN-CONTROL-DASH').load('admin/admin.html');
	});
	
});

function openFunc() {
	var func = $('#funcId');
	var allFields = $([]).add(func);
	var funcName = func.val().toUpperCase();
	var bValid = true;
	allFields.removeClass("ui-state-error");
	$('#funcId').val(funcName);
	
	bValid = bValid
			&& checkLength(func, "invalid function", 1, 10, null,
					null);
	
	if (bValid) {
		
		if (funcName == 'AAROFAT')
			$('#AAROFAT-FORM').dialog('open');
		else if (funcName == 'CAROFAT')
			$('#CAROFAT-FORM').dialog('open');
		else if (funcName == 'VAROFAT')
			$('#VAROFAT-FORM').dialog('open');
		else if(funcName == 'MAROFAT')
			$('#MAROFAT-FORM').dialog('open');
		else if(funcName == 'CUPEDC')
			$('#CUPEDC-FORM').dialog('open');
		else
			$('#GLOBAL-ERROR').dialog('open');
	}
}
/*
function openErrorDialog(obj){
	obj.dialog({
		title : 'ERROR-MESSAGE',
		autoOpen:false,
		modal:true,
		width:400,
		height:300,
		buttons:{
			'Exit':function(){
				obj.dialog('close');
			}
		},
		open:function(){
			$(this).appendTo('<div class="ui-state-error"> INVALID FUNCTION NAME</div>');
		}
	});
	obj.dialog('open');
}
*/