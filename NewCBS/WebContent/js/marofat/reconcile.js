$(function(){
	var $rdate   = $('p #RDATE');
	var $process = $('p #PROCESS');
	var $confirm = $('p #CONFIRM');
	var $tabs    = $('#MRRET');
	var $mrCrTable = $('#MRCRTABLE');
	var $mrCrPager = $('#MRCRPAGER');
	var $mrDbTable = $('#MRDBTABLE');
	var $mrDbPager = $('#MRDBPAGER');
	
	$rdate.datepicker({
		showOn: "button",
	    buttonImage: "css/img/calendar.gif",
	    buttonImageOnly: true
	});
	$process.button();
	$confirm.button();
	$tabs.tabs();
	
	$mrCrTable.jqGrid(getMiParam(miColNames,miColModels,$mrCrPager,miReader,'Credit'))
	.navGrid('#MRCRPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#MRCRPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
	$mrDbTable.jqGrid(getMiParam(miColNames,miColModels,$mrDbPager,miReader,'Debit'))
	.navGrid('#MRDBPAGER',{edit:false,add:false,del:false,search:false})
	.navButtonAdd('#MRDBPAGER',{
		   caption:'',
		   buttonicon:"ui-icon-arrowstop-1-s", 
		   onClickButton: function(){ 
		     alert("Adding Row");
		   }, 
		   position:"last"
		});
});