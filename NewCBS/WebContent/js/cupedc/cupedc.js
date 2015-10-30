$(function(){
	var $cupedcTabs = $('#CUPEDC-FORM-CONT');
	var $cupedcImp  = $('#cupedc-import #content');
	var $cupedcRec  = $('#cupedc-reconcile #content');
	var $cupedcAdj  = $('#cupedc-adjustment #content');
	var $cupedcHis  = $('#cupedc-history #content');
	
	$cupedcTabs.tabs();
	$('#CUPEDC-FORM-CONT .ui-tabs-nav').appendTo('#CUPEDC-FORM-CONT');
	$cupedcImp.load('cupedc/content/import.html');
	$cupedcRec.load('cupedc/content/reconcile.html');
	//$cupedcAdj.load('cupedc/content/adjustment.html');
	//$cupedcHis.load('cupedc/content/history.html');
});

var CupEdcObj = function(){
	/* cup off us GL table */
	var cupOffusNames = ['id','date','refer','mcId','name','amount','status'];

	var cupOffusId 		= {name:'id',		index:'id',			xmlmap:'entity > id', 		width:15, 	align:'left'};
	var cupOffusDate 	= {name:'date', 	index:'date', 		xmlmap:'entity > date', 	width:40, 	align:'left'};
	var cupOffusRef 	= {name:'refer',	index:'refer',		xmlmap:'entity > refer',	width:30,	align:'left'};
	var cupOffusMcId 	= {name:'mcId',		index:'mcId',		xmlmap:'entity > mcId',		width:30,	align:'left'};
	var cupOffusName	= {name:'name',		index:'name',		xmlmap:'entity > name',		width:30,	align:'left'};
	var cupOffusAmt		= {name:'amount',	index:'amount',		xmlmap:'entity > amount',	width:30,	align:'right'};
	var cupOffusStatus	= {name:'status',	index:'status',		xmlmap:'entity > status',	width:30,	align:'left'};

	var cupOffusModel = [cupOffusId, cupOffusDate, cupOffusRef, cupOffusMcId, cupOffusName, cupOffusAmt, cupOffusStatus];

	/* Reconciliation Detail transaction */

	var cupColNames = ['id','date','time','card','type','mid','acct','amt','curr','code','res','cup','mti','name'];

	var cupId 	= {name:'id',	index:'id',		xmlmap:'entity > id', 	width:15, 	align:'left'};
	var cupDate	= {name:'date',	index:'date',	xmlmap:'entity > date', width:15, 	align:'left'};
	var cupTime	= {name:'time',	index:'time',	xmlmap:'entity > time',	width:15, 	align:'left'};
	var cupCard = {name:'card',	index:'card',	xmlmap:'entity > card',	width:15, 	align:'left'};
	var cupType = {name:'type',	index:'type',	xmlmap:'entity > type', width:15, 	align:'left'};
	var cupMid	= {name:'mid',	index:'mid',	xmlmap:'entity > mid', 	width:15, 	align:'left'};
	var cupAcct = {name:'acct',	index:'acct',	xmlmap:'entity > acct', width:15, 	align:'left'};
	var cupAmt	= {name:'amt',	index:'amt',	xmlmap:'entity > amt', 	width:15, 	align:'right'};
	var cupCurr	= {name:'curr',	index:'curr',	xmlmap:'entity > curr', width:15, 	align:'left'};
	var cupCode	= {name:'code',	index:'code',	xmlmap:'entity > code', width:15, 	align:'left'};
	var cupRes	= {name:'res',	index:'res',	xmlmap:'entity > res', 	width:15, 	align:'left'};
	var cupCup	= {name:'cup',	index:'cup',	xmlmap:'entity > cup', 	width:15, 	align:'left'};
	var cupMti	= {name:'mti',	index:'mti',	xmlmap:'entity > mti', 	width:15, 	align:'left'};
	var cupName	= {name:'name',	index:'name',	xmlmap:'entity > name', width:15, 	align:'left'};

	var cupColModel = [cupId, cupDate, cupTime, cupCard, cupType, cupMid, cupAcct, cupAmt, cupCurr, cupCode, cupRes, cupCup, cupMti, cupName];

	// reader
	var cupOffusReader = { 
	        root:'entities', 
	        row:'entity',
	        page:'extend > page', 
	        total:'extend > total', 
	        records:'extend > records',
	        repeatitems:false,
	        id : 'id'
	    };

	
	
	return {
		cupOffusTableParam :function(p, cap){
			var param = {
					datatype:'xml',
					colNames:cupOffusNames,
					colModel:cupOffusModel,
					width:500,
					height:230,
					rowNum:10,
					page:1,
					pager:p,	
					rowList:[10,50,100],
					viewrecords: true,
				    xmlReader:cupOffusReader,
					caption: cap
				};
			return param;
		}, 
		cupDetailTableParam : function(p, cap){
			var param = {
					datatype:'xml',
					colNames:cupColNames,
					colModel:cupColModel,
					width:800,
					height:230,
					rowNum:10,
					page:1,
					pager:p,	
					rowList:[10,50,100],
					viewrecords: true,
				    xmlReader:cupOffusReader,
					caption: cap
				};
			return param;
		}
		
	};
}
