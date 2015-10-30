$(function(){
	var $recon   = $('#VAROFAT-RECON');
	var $date    = $recon.find('#PROCESSDATE');
	var $process = $recon.find('#RECON');
	var $update	 = $recon.find('#UPDATE');
	var $tab     = $('#VRRET');
	
	var $vrSeTable = $tab.find('#VRSETABLE');
	var $vrSePager = $tab.find('#VRSEPAGER');
	
	var $vrStTable = $tab.find('#VRSTTABLE');
	var $vrStPager = $tab.find('#VRSTPAGER');
	
	$tab.tabs();
	$date.datepicker({
		showOn: "button",
	    buttonImage: "css/img/calendar.gif",
	    buttonImageOnly: true
	});
	
	$process.button();
	$update.button();
	$update.hide();
	
	$vrSeTable.jqGrid(getTableParam($vrSePager,'Settlement'));
	$vrStTable.jqGrid(getTableParam($vrStPager,'Statement'));
	
	$process.click(function(){
		var allFields = [$date];
		var check = dataValidation(allFields);
		if(check==true){
			//submit form
			alert('O.K');
		}
	});
	
});