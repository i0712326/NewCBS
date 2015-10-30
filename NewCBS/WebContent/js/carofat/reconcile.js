$(function(){
	
	var CarofatRecObj = function(){
		
		var $carofatRec = $('#CAROFAT-RECONCILE');
		var $date		= $carofatRec.find('#RDATE');
		var $submit		= $carofatRec.find('#SUBBUTTON');
		var $confirm 	= $carofatRec.find('#CONBUTTON');
		var $cancel		= $carofatRec.find('#CANBUTTON');
		var $crret		= $('#CRRET');
		var $crStTable  = $crret.find('#CRSTTABLE');
		var $crSeTable	= $crret.find('#CRSETABLE');
		var $crStPager	= $crret.find('#CRSTPAGER');
		var $crSePager	= $crret.find('#CRSEPAGER');
		

		return {
			decorate : function() {
				$date.datepicker({
					showOn : 'button',
					buttonImage : 'css/img/calendar.gif',
					buttonImageOnly : true
				});
				
				$submit.button();
				$confirm.button();
				$confirm.hide();
				$cancel.button();
				$cancel.hide();

				$crret.tabs();
			},
			getCrStTable : function() {
				return $crStTable;
			},
			getCrSeTable : function() {
				return $crSeTable;
			},
			getCrStPager : function() {
				return $crStPager;
			},
			getCrSePager : function() {
				return $crSePager;
			}
		}
		
	};
	
	var carofatRecObj = new CarofatRecObj();
	
	carofatRecObj.decorate();
	
	carofatRecObj.getCrStTable().jqGrid(carofatTableParam(carofatRecObj.getCrStPager(),'STATE'))
	.navGrid('#CRSTPAGER',{edit:false,add:false,del:false,search:false});
	
	carofatRecObj.getCrSeTable().jqGrid(carofatTableParam(carofatRecObj.getCrSePager(),'SETTLE'))
	.navGrid('#CRSEPAGER',{edit:false,add:false,del:false,search:false});
	
});