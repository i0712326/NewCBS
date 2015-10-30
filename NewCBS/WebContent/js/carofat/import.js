$(document).ready(function(){
	var $carofatImp = $('#CAROFAT-IMPORT');
	var $record     = $carofatImp.find('#RECORD');
	var $date		= $carofatImp.find('#IDATE');
	var $option		= $carofatImp.find('#OPTION');
	var $submit		= $carofatImp.find('#CUPBUTTON');
	var $confirm	= $carofatImp.find('#CONBUTTON');
	var $cancel		= $carofatImp.find('#CANBUTTON');
	var $ciret		= $('#CIRET');
	var $ciTable	= $ciret.find('#CITABLE');
	var $ciPager	= $ciret.find('#CIPAGER');
	$date.datepicker({
		showOn: "button",
	    buttonImage: "css/img/calendar.gif",
	    buttonImageOnly: true
	});
	
	$submit.button();
	$confirm.button();
	$confirm.hide();
	$cancel.button();
	$cancel.hide();
	
	$ciTable.jqGrid(carofatTableParam($ciPager,'IMPORT-RESULT'))
	.navGrid('#CIPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#CIPAGER',{
	   caption:'pdf',
	   buttonicon:"ui-icon-arrowstop-1-s", 
	   onClickButton: function(){ 
			var format = 'pdf';
			var date = $date.val().trim();
			var option = $option.val().trim();
			var params = {'date':date,'param.format':format,'option':option};
			var url='#';
			if(option=='state')
				url = 'carofat/carofat.import.get.temp.states.action';
			else
				url = 'carofat/carofat.import.get.temp.settles.action';
			startProcessing();
			$.post(url,params,function(data){
				endProcessing();
				var result = $(data).find('result').text().trim();
				if(result=='error'){
					var code = $(data).find('message').text().trim();
					var errors = [code];
					openErrorResult(errors);
				}
				else{
					var message = $(data).find('message').text().trim();
					var fileName = '/NewCBS/output/cupTxn/'+message;
					//openAlertResult(messages);
					var myWindow = window.open(fileName,'_blank','width=800,height=600');
					myWindow.focus();
				}
			});
			//submitValues(url,params); 
	   }, 
	   position:"last"
	});
	// action
	
	$submit.click(function(){
		var allFields = [$record,$date,$option];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
			var params = {'record':$record.val().trim(),'date':$date.val().trim(),'option':$option.val().trim()};
			$.post('carofat/carofat.import.action',params,function(data){
				endProcessing();
				var result = $(data).find('result').text().trim();
				if(result=='error'){
					var code = $(data).find('message').text().trim();
					var errors = [code];
					openErrorResult(errors);
				}
				else{
					var message = $(data).find('message').text().trim();
					var messages=[message];
					openAlertResult(messages);
					$submit.hide();
					$confirm.show();
					$cancel.show();
					// trigger refresh table
					var opt = $option.val().trim();
					var dat = $date.val().trim();
					if(opt=='settle'){
						var action = 'carofat/carofat.import.getsettle.action?option='+opt+'&date='+dat;
						var act=encodeURI(action);
						$ciTable.jqGrid('setGridParam',{url:act});
					}
					else{
						var action = 'carofat/carofat.import.getstate.action?option='+opt+'&date='+dat;
						var act=encodeURI(action);
						$ciTable.jqGrid('setGridParam',{url:act});
					}
					$ciTable.trigger('reloadGrid');
				}
			});
		}
	});
	
	$confirm.click(function(){
		var allFields = [$record,$date,$option];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
			var params = {'option':$option.val().trim()};
			var action = 'carofat/carofat.import.confirm.action';
			$.post(action,params,function(data){
				endProcessing();
				var result = $(data).find('result').text().trim();
				if(result=='error'){
					var code = $(data).find('message').text().trim();
					var errors = [code];
					openErrorResult(errors);
				}
				else{
					var message = $(data).find('message').text().trim();
					var messages=[message];
					openAlertResult(messages);
					$confirm.hide();
					$submit.show();
					$cancel.hide();
					var opt = $option.val().trim();
					var dat = $date.val().trim();
					if(opt=='settle'){
						var action = 'carofat/carofat.import.retreivesettle.action?option='+opt+'&date='+dat;
						var act=encodeURI(action);
						$ciTable.jqGrid('setGridParam',{url:act});
					}
					else{
						var action = 'carofat/carofat.import.retreivestate.action?option='+opt+'&date='+dat;
						var act=encodeURI(action);
						$ciTable.jqGrid('setGridParam',{url:act});
					}
					$ciTable.trigger('reloadGrid');
					
				}
			});
		}
	});
	
	$cancel.click(function(){
		var allFields = [$record,$date,$option];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
			var action = 'carofat/carofat.import.cancel.action';
			var opt = $option.val().trim();
			var dat = $date.val().trim();
			$.post(action,{option:opt,date:dat},function(data){
				endProcessing();
				var result = $(data).find('result').text().trim();
				if(result=='error'){
					var code = $(data).find('message').text().trim();
					var errors = [code];
					openErrorResult(errors);
				}
				else{
					var message = $(data).find('message').text().trim();
					var messages=[message];
					openAlertResult(messages);
					$submit.show();
					$confirm.hide();
					$cancel.hide();
					var opt = $option.val().trim();
					var dat = $date.val().trim();
					if(opt=='settle'){
						var action = 'carofat/carofat.import.getsettle.action?option='+opt+'&date='+dat;
						var act=encodeURI(action);
						$ciTable.jqGrid('setGridParam',{url:act});
					}
					else{
						var action = 'carofat/carofat.import.getstate.action?option='+opt+'&date='+dat;
						var act=encodeURI(action);
						$ciTable.jqGrid('setGridParam',{url:act});
					}
					$ciTable.trigger('reloadGrid');
				}
			});
		}
	});
	
});

/*

function submitValues(url, params) {
    var form = [ '<form method="POST" action="', url, '">' ];
    for(var key in params) 
        form.push('<input type="hidden" name="', key, '" value="', params[key], '"/>');
    form.push('</form>');
    $(form.join('')).appendTo('#CUPDOWNFRAME').submit();
}
*/
