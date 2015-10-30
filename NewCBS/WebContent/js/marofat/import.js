$(function(){
	var $rec     = $('#MAROFAT-IMPORT-FORM #MIMREC');
	var $date    = $('#MAROFAT-IMPORT-FORM #MIMDATE');
	var $option  = $('#MAROFAT-IMPORT-FORM #MIMOPTION');
	var $file    = $('#MAROFAT-IMPORT-FORM #MIMFILE');
	var $submit  = $('#MAROFAT-IMPORT-FORM #MIMSUBMIT');
	var $confirm = $('#MAROFAT-IMPORT-FORM #MIMCONFIRM');
	var $miTabs  = $('#MIRET');
	
	var $miSeTable = $('#MIRET #MISETABLE');
	var $miSePager = $('#MIRET #MISEPAGER');
	var $miStTable = $('#MIRET #MISTTABLE');
	var $miStPager = $('#MIRET #MISTPAGER');
	
	$date.datepicker({
		 showOn: "button",
		 buttonImage: "css/img/calendar.gif",
		 buttonImageOnly: true
	});
	$submit.button();
	$confirm.button();
	$confirm.hide();
	$miTabs.tabs();
	
	$miSeTable.jqGrid(getMiParam(miColNames,miColModels,$miSePager,miReader,'SETTLEMENT'))
	.navGrid('#MICRPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#MICRPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	
	$miStTable.jqGrid(getMiParam(miColNames,miColModels,$miStPager,miReader,'STATEMENT'))
	.navGrid('#MIDBPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#MIDBPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	
	// action
	$submit.click(function(){
		var allFields = [$rec,$date,$option,$file];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
		}
	});
}); 