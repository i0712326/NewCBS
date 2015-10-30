$(function(){
	var varofat = $('#VAROFAT-ADJUST');
	var $date   = varofat.find("#SETTLEDATE");
	var $option = varofat.find('#OPTION');
	var $ref    = varofat.find('#REFNO');
	var $card   = varofat.find('#CARDNO');
	var $search = varofat.find('#SEARCH');
	var $update = varofat.find('#UPDATE');
	var tips    = varofat.find('#TIPS');
	var $vaTable  = $('#VATABLE');
	var $aPager   = $('#VAPAGER');
	// result dialog
	
	$date.datepicker({
		  showOn: "button",
	      buttonImage: "css/img/calendar.gif",
	      buttonImageOnly: true
	});
	
	$search.button();
	$update.button();
	$update.hide();
	tips.dialog({
		title:'VAROFAT-ALERT',
		autoOpen:false,
		position:[210,50],
		width:400,
		height:200,
		modal:true,
		buttons:{
			'Exit':function(){
				$(this).dialog('close');
			}
		}
	});
	
	// action for search
	
	$search.click(function(){
		var allFields = [$date,$option,$ref,$card];
		bValid = dataValidation(allFields);
		if(bValid){
			$update.show();
			var date = $date.val().trim();
			var type = $option.val().trim();
			var ref  = $ref.val().trim();
			var card = $card.val().trim();
			
			var urlValue = "varofat/adjustment.action?date="+date+"&type="+type+"&ref="+ref+"&card="+card;
			var uri  = encodeURI(urlValue);
			$vaTable.trigger('clearGrid');
			$vaTable.setGridParam({url:uri,rowNum:10,page:1});
			$vaTable.trigger('reloadGrid');
		}
	});
	// action for update
	$update.click(function(){
		var options = [];
		
		$('tbody tr',$vaTable).each(function(){
			var $row = $(this);
			var check = $row.find('input:checkbox');
			if(check.is(':checked')){
				var stat=$row.find('select').val().trim();
				var cas =$row.find('input:text').val().trim();
				var opt = new Opt(stat,cas);
				options.push(opt);
			}
		});
		
		 var selRowIds = $vaTable.jqGrid('getGridParam', 'selarrrow');
		 var len = selRowIds.length;
		 var selRows = [];
		 for(var i = 0; i<len;i++){
			 var rowId = selRowIds[i];
			 var rowData = $vaTable.jqGrid ('getRowData', rowId);
			 selRows.push(rowData);
		 }
		 var xmlData = visaAtmXml(selRows,options);
		 var action = "varofat/update.action";
		 //var action="#";
		 // open loading alert dialog box
		 var box = $('#VAPDIALOG');
		 var dial =  processDialog(box);
		 var param = {'xmlData':xmlData};
		 dial.dialog('open');
		 
		 $.post(action,param,function(data){
				// close loading alert
				dial.dialog('close');
			 	// dialog box pop result
				
				//grid reload
			 	$vaTable.trigger('clearGrid');
				$vaTable.setGridParam({url:uri,rowNum:10,page:1});
				$vaTable.trigger('reloadGrid');
				
		 });
		 
	});
	
	// table data
	

	$vaTable.jqGrid(getEditTableParam($aPager,'ADJUSTMENT'))
	.navGrid('#VAPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#VAPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-newwin", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
});