$(document).ready(function(){
	var $varofatImp = $('#VAROFAT-IMPORT');
	var $import  = $varofatImp.find('#IMPORT');
	var $confirm = $varofatImp.find('#CONFIRM');
	var $idate   = $varofatImp.find('#IDATE');
	var $record  = $varofatImp.find('#REC');
	var $option  = $varofatImp.find('#OPTION');
	var $file    = $varofatImp.find('#FILE');
	var $tab	 = $('#VIRET');
	
	var $viCrTable = $('#VICRTABLE');
	var $viCrPager = $('#VICRPAGER');
	
	var $viDbTable = $('#VIDBTABLE');
	var $viDbPager = $('#VIDBPAGER');
	
	var $viRvTable = $('#VIRVTABLE');
	var $viRvPager = $('#VIRVPAGER');
	
	var $viCbTable = $('#VICBTABLE');
	var $viCbPager = $('#VICBPAGER');
	
	var $viRpTable = $('#VIRPTABLE');
	var $viRpPager = $('#VIRPPAGER');
	
	var $viAdTable = $('#VIADTABLE');
	var $viAdPager = $('#VIADPAGER');
	
	$tab.tabs();
	$import.button();
	$confirm.button();
	$idate.datepicker({
		showOn: "button",
	    buttonImage: "css/img/calendar.gif",
	    buttonImageOnly: true
	});
	$confirm.hide();
	// table
	$viCrTable.jqGrid(getTableParam($viCrPager,'Credit'))
	.navGrid('#VICRPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#VICRPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	
	$viDbTable.jqGrid(getTableParam($viDbPager,'Debit'))
	.navGrid('#VIDBPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#VIDBPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	
	$viRvTable.jqGrid(getTableParam($viRvPager,'Reversal'))
	.navGrid('#VIRVPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#VIRVPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	$viCbTable.jqGrid(getTableParam($viCbPager,'Chargeback'))
	.navGrid('#VICBPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#VICBPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	$viRpTable.jqGrid(getTableParam($viRpPager,'Represent'))
	.navGrid('#VIRPPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#VIRPPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	$viAdTable.jqGrid(getTableParam($viAdPager,'Adjustment'))
	.navGrid('#VIADPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#VIADPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	// action
	$import.click(function(){
		var allFields = [$idate,$record,$option,$file];
		var check = dataValidation(allFields);
		if(check==true){
			startProcessing();
		}
	});
});