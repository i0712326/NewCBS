$(function(){
	var nav     = $('#nav');
	var menu    = nav.find('#MENU');
	//var admin   = nav.find('#ADMIN');
	//var setting = nav.find('#SETTING');
	var fname   = $('#FUNCNAME');
	var goButt  = $('#GOBUTT');
	var aarofat = $('#AAROFAT');
	var carofat = $('#CAROFAT');
	var varofat = $('#VAROFAT');
	var marofat = $('#MAROFAT');
	var cupedc  = $('#CUPEDC');
	
	var errorDialog = $('#ERROR');
	var alertDialog = $('#ALERT');
	var proceDialog = $('#PROCESSING');
	
	var aarCont = aarofat.find('#CONTENT');
	var carCont = carofat.find('#CONTENT');
	var varCont = varofat.find('#CONTENT');
	var marCont = marofat.find('#CONTENT');
	var cupCont = cupedc.find('#CONTENT');
	
	var aarParam = {
		title:'AAROFAT',
		autoOpen:false,
		width:1400,
		height:650,
		position:[30,100],
		buttons:{
			'Exit':function(){
				$(this).dialog('close');
			}
		},
		open:function(){
			aarCont.load('aarofat/aarofat.html');
		}
	};
	
	var carParam = {
			title : 'CAROFAT',
			autoOpen:false,
			width:1400,
			height:650,
			position:[30,100],
			buttons:{
				'Exit':function(){
					$(this).dialog('close');
				}
			},
			open:function(){
				carCont.load('carofat/carofat.html');
			}
	};
	
	var varParam = {
			title:'VAROFAT',
			autoOpen:false,
			width:1400,
			height:650,
			position:[30,100],
			buttons:{
				'Exit':function(){
					$(this).dialog('close');
				}
			},
			open:function(){
				varCont.load('varofat/varofat.html');
			}
	};
	
	var marParam = {
			title:'MAROFAT',
			autoOpen:false,
			width:1400,
			height:650,
			position:[30,100],
			buttons:{
				'Exit':function(){
					$(this).dialog('close');
				}
			},
			open:function(){
				marCont.load('marofat/marofat.html');
			}
	};
	
	var cupParam = {
			title:'CUPEDC',
			autoOpen:false,
			width:1400,
			height:650,
			position:[30,100],
			buttons:{
				'Exit':function(){
					$(this).dialog('close');
				}
			},
			open:function(){
				cupCont.load('cupedc/cupedc.html');
			}	
	};
	
	var errorParam = {
			title:'ERROR',
			autoOpen:false,
			width:400,
			height:200,
			modal:true,
			position:[200,200],
			buttons:{
				'Exit':function(){
					$(this).dialog('close');
				}
			}
	};
	
	var alertParam = {
		title:'ALERT',
		autoOpen:false,
		width:400,
		height:200,
		position:[200, 200],
		modal:true,
		buttons:{
			'Exit':function(){
				$(this).dialog('close');
			}
		}
	};
	
	var proceParam = {
			title:'PROCESSING',
			autoOpen:false,
			width:400,
			height:200,
			position:[200,200],
			modal:true,
			buttons:{
				'Exit':function(){
					$(this).dialog('close');
				}
			},
			open:function(){
				$('#PROCESSING #CONTENT').load('processing.html');
			}
	};
	
	// view
	
	nav.accordion();
	
	aarCont.dialog(aarParam);
	carCont.dialog(carParam);
	varCont.dialog(varParam);
	marCont.dialog(marParam);
	cupCont.dialog(cupParam);
	
	errorDialog.dialog(errorParam);
	alertDialog.dialog(alertParam);
	proceDialog.dialog(proceParam);
	// action
	menu.load('navigation/menu.html');
	//admin.load('navigation/admin.html');
	//setting.load('navigation/setting.html');
	fname.bind('keypress',function(event){
		if(event.keyCode==13){
			goFunction();
		}
	});
	
	goButt.click(function(){
		goFunction();
	});
	
	// logic for function
	function goFunction(){
		var func = fname.val().trim();
		var f = func.toUpperCase();
		fname.val(f);
		if(f=='AAROFAT'){
			aarCont.dialog('open');
		}
		else if(f=='CAROFAT'){
			carCont.dialog('open');
		}
		else if(f=='VAROFAT'){
			varCont.dialog('open');
		}
		else if(f=='MAROFAT'){
			marCont.dialog('open');
		}
		else if(f=='CUPEDC'){
			cupCont.dialog('open');
		}
		else{
			var error='<div class="ui-state-error"><ul><li> <strong> Error : </strong> Invalid Function</li></ul></div>';
			$('#ERROR #CONTENT').html(error);
			errorDialog.dialog('open');
		}
	}
	
	function startProcessing(){
		proceDialog.dialog('open');
	}
});
