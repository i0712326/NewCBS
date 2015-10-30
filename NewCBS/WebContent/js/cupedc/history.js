$(function(){
	var $carofatHis = $('#CAROFAT-HISTORY');
	var $date		= $carofatHis.find('#CHDATE');
	var $card		= $carofatHis.find('#CHCARD');
	var $trace		= $carofatHis.find('#CHTRACE');
	var $search		= $carofatHis.find('#CHSEARCH');
	var $chTab		= $('#CHTAB');
	
	var $chStTable 	= $chTab.find('#CHSTTABLE');
	var $chStPager	= $chTab.find('#CHSTPAGER');
	
	var $chSeTable  = $chTab.find('#CHSETABLE');
	var $chSePager  = $chTab.find('#CHSEPAGER');
	
	$date.datepicker({
		showOn:'button',
		 buttonImage: 'css/img/calendar.gif',
		 buttonImageOnly: true
	});
	$search.button();
	$chTab.tabs();
	// state table
	$chStTable.jqGrid(carofatTableParam($chStPager,'HISTORY-STATE'));
	
	// settle table
	$chSeTable.jqGrid(carofatTableParam($chSePager,'HISTORY-SETTLE'));
	
	// action
	$search.click(function(){
		var allFields = [$date,$card,$trace];
		var check	  = dataValidation(allFields);
		if(check==true){
			var date 	= $date.val().trim();
			var card 	= $card.val().trim();
			var trace 	= $trace.val().trim();
			
			var stateurl="carofat/carofat.history.getstates.action?date="+date+"&card="+card+"&trace="+trace;
			var stateact=encodeURI(stateurl);
			$chStTable.jqGrid('setGridParam',{url:stateact});
			$chStTable.trigger('reloadGrid');
			
			var settleurl="carofat/carofat.history.getsettles.action?date="+date+"&card="+card+"&trace="+trace;
			var settleact=encodeURI(settleurl);
			$chSeTable.jqGrid('setGridParam',{url:settleact});
			$chSeTable.trigger('reloadGrid');
		}
	});
});