$(function(){
	var $carofatAdj = $('#CAROFAT-ADJUSTMENT');
	var $date = $carofatAdj.find('#CADATE');
	var $option = $carofatAdj.find('#CAOPTION');
	var $search = $carofatAdj.find('#CASEARCH');
	var $update	= $carofatAdj.find('#CAUPDATE');
	var $card	= $carofatAdj.find('#CACARD');
	var $trace	= $carofatAdj.find('#CATRACE');
	var $caret  = $('#CARET');
	var $caTable= $caret.find('#CATABLE');
	var $caPager= $caret.find('#CAPAGER');
	
	$date.datepicker({
		showOn: "button",
	    buttonImage: "css/img/calendar.gif",
	    buttonImageOnly: true
	});
	$search.button();
	$update.button();
	
	$caTable.jqGrid(carofatEditTableParam($caPager,'VIEW-RESULT'));
	// action
	$search.click(function(){
		var allFields = [$date,$option,$card,$trace];
		var check = dataValidation(allFields);
		if(check==true){
			var option = $option.val().trim();
			var date   = $date.val().trim();
			var card   = $card.val().trim();
			var trace  = $trace.val().trim();
			var act = "#";
			if(option=='settle')
				act = "carofat/carofat.adjustment.search.settle.action?option="+option+"&date="+date+"&card="+card+"&trace="+trace;
			else
				act = "carofat/carofat.adjustment.search.state.action?option="+option+"&date="+date+"&card="+card+"&trace="+trace;;
			
			$caTable.jqGrid("setGridParam",{url:encodeURI(act)});
			$caTable.trigger("reloadGrid");
		}
	});
	
	$update.click(function(){
		var objs = [];
		$('tbody tr',$caTable).each(function(){
			var $row = $(this);
			var check = $row.find('input:checkbox');
			if(check.is(':checked')){
				var date  	 = $row.find('input[name=date]'    ).val();
				var card  	 = $row.find('input[name=card]'    ).val();
				var trace 	 = $row.find('input[name=trace]'   ).val();
				var time  	 = $row.find('input[name=time]'    ).val();
				var amount 	 = $row.find('input[name=amount]'  ).val();
				var fee	   	 = $row.find('input[name=fee]'     ).val();
				var net	   	 = $row.find('input[name=net]'     ).val();
				var terminal = $row.find('input[name=terminal]').val();
				var refer	 = $row.find('input[name=refer]'   ).val();
				var type	 = $row.find('input[name=type]'    ).val();
				var status   = $row.find('select[name=status]' ).val();
				var detail = date+","+card+","+trace+","+time+","+amount+","+fee+","+net+","+terminal+","+refer+","+type+","+status;
				console.debug(detail);
				var opt = new CarObj(date,card,trace,time,amount,fee,net,terminal,refer,type,status);
				objs.push(opt);
			}
		});
		
		if(objs.length!=0){
			var xmlData = cupRemoteUsAtmXml(objs);
			var allFields = [$date,$option,$card,$trace];
			var check = dataValidation(allFields);
			if(check==true){
				startProcessing();
				var url = "carofat/carofat.adjustment.update.action";
				var params = {'option':$option.val().trim(),'xmlData':xmlData};
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
					}
				});
			}
		}
		else{
			var error = ["0005"];
			openErrorResult(error);
		}
	});
});