$(document).ready(function(){
	var $history   = $('#VAROFAT-HISTORY');
	var $date      = $history.find('#DATE');
	var $option    = $history.find('#OPT');
	var $retrieval = $history.find('#REF');
	var $card	   = $history.find('#CARD');
	var $search    = $history.find('#SEARCH');
	var $hTable	   = $('#VHTABLE');
	var $hPager    = $('#VHPAGER');
	
	$date.datepicker({
		showOn: "button",
	    buttonImage: "css/img/calendar.gif",
	    buttonImageOnly: true
	});
	
	$search.button();
	
	$hTable.jqGrid(getTableParam($hPager,'Visa History'));
	// action
	$search.click(function(){
		var allFields = [$date,$option,$retrieval,$card];
		bValid = dataValidation(allFields);
		if(bValid){
			var date = $date.val().trim();
			var option = $option.val().trim();
			var retrieval  = $retrieval.val().trim();
			var card = $card.val().trim();
			
			var urlValue = "varofat/history.action?date="+date+"&cate="+option+"&retr="+retrieval+"&card="+card;
			var uri  = encodeURI(urlValue);
			$hTable.trigger('clearGrid');
			$hTable.setGridParam({url:uri,rowNum:10,page:1});
			$hTable.trigger('reloadGrid');
		}
	});
});