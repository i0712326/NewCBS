$(document).ready(function(){
	
	var CupEdcImpObj = function(){
		
		var $cupedcImp = $('#CUPEDC-IMPORT');
		var $record     = $cupedcImp.find('#RECORD');
		var $date		= $cupedcImp.find('#IDATE');
		var $submit		= $cupedcImp.find('#CUPBUTTON');
		var $confirm	= $cupedcImp.find('#CONBUTTON');
		var $cancel		= $cupedcImp.find('#CANBUTTON');
		var $ciret		= $('#CERET');
		var $ciTable	= $ciret.find('#CETABLE');
		var $ciPager	= $ciret.find('#CEPAGER');
		var $ciTab		= $ciret.find('#CETAB');
		var $ciPag		= $ciret.find('#CEPAG');
		
		return {
			decorate:function(){
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
			},
			getCiTable:function(){
				return  $ciTable;
			},
			getCiPager : function(){
				return $ciPager;
			},
			getCiTab:function(){
				return $ciTab;
			},
			getCiPag : function(){
				return $ciPag;
			}
		}
		
	}
	
	// create object
	var cupEdcImpObj = new CupEdcImpObj();
	var cupEdcObj = new CupEdcObj();
	
	cupEdcImpObj.decorate();
	
	cupEdcImpObj.getCiTable().jqGrid(cupEdcObj.cupOffusTableParam(cupEdcImpObj.getCiPager(),'GL'))
	.navGrid('#CIPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#CIPAGER',{
	   caption:'pdf',
	   buttonicon:"ui-icon-arrowstop-1-s", 
	   position:"last"
	});
	
	cupEdcImpObj.getCiTab().jqGrid(cupEdcObj.cupDetailTableParam(cupEdcImpObj.getCiPag(),'RECONCILIATION'))
	.navGrid('#CIPAG',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#CIPAG',{
	   caption:'pdf',
	   buttonicon:"ui-icon-arrowstop-1-s", 
	   position:"last"
	});
	// action
	/*
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
	*/
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
