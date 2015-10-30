$(function(){
	
	var CupEdcRecObj = function(){
		
		var $carofatRec = $('#CUPEDC-RECONCILE');
		var $date		= $carofatRec.find('#RDATE');
		var $submit		= $carofatRec.find('#SUBBUTTON');
		var $confirm 	= $carofatRec.find('#CONBUTTON');
		var $cancel		= $carofatRec.find('#CANBUTTON');
		
		var $crret		= $('#CERRET');
		var $crStTable  = $crret.find('#CRSTTABLE');
		var $crSeTable	= $crret.find('#CRSETABLE');
		var $crStPager	= $crret.find('#CRSTPAGER');
		var $crSePager	= $crret.find('#CRSEPAGER');
		
		return {
			decorate : function(){
				$date.datepicker({
					 showOn:'button',
					 buttonImage: 'css/img/calendar.gif',
					 buttonImageOnly: true
				});
				$submit.button();
				$confirm.button();
				$confirm.hide();
				$cancel.button();
				$cancel.hide();
				
				$crret.tabs();
			},
			getCrStTable:function(){
				return $crStTable;
			},
			getCrSeTable:function(){
				return $crSeTable;
			},
			getCrStPager:function(){
				return $crStPager
			},
			getCrSePager:function(){
				return $crSePager;
			}
		}
	
	};
	
	var cupEdcRecObj = new CupEdcRecObj();
	var cupEdcObj = new CupEdcObj();
	
	cupEdcRecObj.decorate();
	
	cupEdcRecObj.getCrStTable().jqGrid(cupEdcObj.cupDetailTableParam(cupEdcRecObj.getCrStPager(),'STATE'))
	.navGrid('#CRSTPAGER',{edit:false,add:false,del:false,search:false});
	
	cupEdcRecObj.getCrSeTable().jqGrid(cupEdcObj.cupDetailTableParam(cupEdcRecObj.getCrSePager(),'SETTLE'))
	.navGrid('#CRSEPAGER',{edit:false,add:false,del:false,search:false});
	
	//
	
	$submit.click(function(){
		var allFields = [$date];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
			var url = "carofat/carofat.reconcile.result.action";
			var params = {date:$date.val().trim()};
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
					var messages=[message];
					openAlertResult(messages);
					$submit.hide();
					$confirm.show();
					$cancel.show();
					// trigger table
					var act1 = encodeURI('carofat/carofat.getstate.reconcile.action?date='+$date.val().trim());
					var act2 = encodeURI('carofat/carofat.getsettle.reconcile.action?date='+$date.val().trim());
					$crStTable.jqGrid('setGridParam',{url:act1});
					$crSeTable.jqGrid('setGridParam',{url:act2});
					$crStTable.trigger('reloadGrid');
					$crSeTable.trigger('reloadGrid');
				}
			});
			
		}
	});
	
	$confirm.click(function(){
		var allFields = [$date];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
			var url='carofat/carofat.reconcile.confirm.action';
			var params={'date':$date.val().trim()};
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
					var messages=[message];
					openAlertResult(messages);
					$submit.show();
					$confirm.hide();
					$cancel.hide();
					// trigger tableRECONCILE_CUP_COMFIRM
					var act1 = encodeURI('carofat/carofat.reconcile.getreconstate.action?date='+$date.val().trim());
					var act2 = encodeURI('carofat/carofat.reconcile.getreconsettle.action?date='+$date.val().trim());
					$crStTable.jqGrid('setGridParam',{url:act1});
					$crSeTable.jqGrid('setGridParam',{url:act2});
					$crStTable.trigger('reloadGrid');
					$crSeTable.trigger('reloadGrid');
					// add download button for settlement
					$crSeTable.navButtonAdd('#CRSEPAGER',{
						   caption:'pdf',
						   buttonicon:"ui-icon-arrowstop-1-s", 
						   onClickButton: function(){ 
							    var format = 'pdf';
								var date = $date.val().trim();
								var option = "settle";
								var params = {'date':date,'param.format':format,'option':option};
								var url = '#';
								if(option=='state')
									url = 'carofat/carofat.reconcile.get.temp.states.action';
								else
									url='carofat/carofat.reconcile.get.temp.settles.action';
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
					// add download button for statement
					$crStTable.navButtonAdd('#CRSTPAGER',{
						   caption:'pdf',
						   buttonicon:"ui-icon-arrowstop-1-s", 
						   onClickButton: function(){ 
							    var format = 'pdf';
								var date = $date.val().trim();
								var option = "state";
								var params = {'date':date,'param.format':format,'option':option};
								var url = '#';
								if(option=='state')
									url = 'carofat/carofat.reconcile.get.temp.states.action';
								else
									url='carofat/carofat.reconcile.get.temp.settles.action';
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
								 
						   }, 
						   position:"last"
						});
				}
			});
		}
	});
	
	$cancel.click(function(){
		var allFields = [$date];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
			var url="carofat/carofat.reconcile.cancel.action";
			$.post(url,function(data){
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
					// trigger tableRECONCILE_CUP_COMFIRM
					var act1 = encodeURI('carofat/carofat.getstate.reconcile.action?date='+$date.val().trim());
					var act2 = encodeURI('carofat/carofat.getsettle.reconcile.action?date='+$date.val().trim());
					$crStTable.jqGrid('setGridParam',{url:act1});
					$crSeTable.jqGrid('setGridParam',{url:act2});
					$crStTable.trigger('reloadGrid');
					$crSeTable.trigger('reloadGrid');
				}
			});
		}
	});
});