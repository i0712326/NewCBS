// data validation
function dataValidation(fields) {
	var ret = true;
	var result = true;
	var errorList = [];
	for ( var i=0;i<fields.length;i++) {
		fields[i].removeClass('ui-state-error');
	}
	for ( var i=0;i<fields.length;i++) {
		var fieldVal = fields[i].val().trim();
		ret = checkLength(fieldVal);
		if (ret == false) {
			fields[i].addClass('ui-state-error');
			errorList.push(fields[i].prop("name"));
		}
		result=result&&ret;
	}
	
	if(result==false)
		openError(errorList);
	
	return result;
}

function checkLength(data) {
	if (data.length > 0)
		return true;
	else
		return false;
}

function openError(errors) {
	var errorHtml = '<div class="ui-state-error" style="vertical-align:middle;"><ul>';
	for ( var i=0;i<errors.length;i++) {
		var error = errors[i];
		errorHtml = errorHtml +'<li><p style="margin-left:5px;font-size:12px;"><strong>' + error + '<strong> : required </p></li>';
	}
	errorHtml = errorHtml + '</ul></div>';
	$('#ERROR #CONTENT').html(errorHtml);
	$('#ERROR').dialog('open');
}

function openErrorResult(errors) {
	var errorHtml = '<div class="ui-state-error" style="vertical-align:middle;"><ul>';
	for ( var i=0;i<errors.length;i++) {
		var error = errors[i];
		errorHtml = errorHtml +'<li><p style="margin-left:5px;font-size:12px;"><strong> ERROR <strong> : ' + error + ' </p></li>';
	}
	errorHtml = errorHtml + '</ul></div>';
	$('#ERROR #CONTENT').html(errorHtml);
	$('#ERROR').dialog('open');
}

function openAlertResult(alerts){
	var alertHtml = '<div class="ui-state-info" style="vertical-align:middle;"><ul>';
	for(var i=0;i<alerts.length;i++){
		var alert = alerts[i];
		alertHtml = alertHtml+'<li><p style="margin-left:5px;font-size:12px;"><strong> Alert </strong> : '+alert+'</p></li>';
	}
	alertHtml = alertHtml+'</ul></div>';
	$('#ALERT #CONTENT').html(alertHtml);
	$('#ALERT').dialog('open');
}

function openAlert(alerts) {
	var alertCont = $('#ALERT #CONTENT');
	for ( var alert in alerts) {
		var alertHtml = '<div class="ui-state-info">' + alert + '</div>';
		alertCont.appendTo(alertHtml);
	}
	alertDialog.dialog('open');
}

function startProcessing(){
	$('#PROCESSING').dialog('open');
}

function endProcessing(){
	$('#PROCESSING').dialog('close');
}