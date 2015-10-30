$(function(){
	var $marofatTabs = $('#MAROFAT-FORM-CONT');
	var $marofatImp  = $('#marofat-import #content');
	var $marofatRec	 = $('#marofat-reconcile #content');
	var $marofatAdj	 = $('#marofat-adjustment #content');
	var $marofatHis  = $('#marofat-history #content');
	
	$marofatTabs.tabs();
	$('#MAROFAT-FORM-CONT .ui-tabs-nav').appendTo('#MAROFAT-FORM-CONT');
	
	$marofatImp.load('marofat/content/import.html');
	$marofatRec.load('marofat/content/reconcile.html');
	$marofatAdj.load('marofat/content/adjustment.html');
	$marofatHis.load('marofat/content/history.html');
});

var miColNames  = ['id','bat','txnd','txnt','card','retr','trace','issuer','type','proc','rea',
	              'res','atm','curr','set','cat','group','atmId','refer','date','stat','case'];
var miColModels =[ {name:'id',     index:'id',     xmlmap:'entity > id', 		width: 50, align:'left'  },
	               {name:'bat',    index:'bat',    xmlmap:'entity > batid',		width: 50, align:'left'  },
			       {name:'txnd',   index:'txnd',   xmlmap:'entity > txndate', 	width: 60, align:'left'  },
			       {name:'txnt',   index:'txnt',   xmlmap:'entity > txntime', 	widht: 50, align:'left'  },
			       {name:'card',   index:'card',   xmlmap:'entity > card', 		width:200, align:'left'  },
			       {name:'retr',   index:'retr',   xmlmap:'entity > retrieval', width:150, align:'left'  },
			       {name:'trace',  index:'trace',  xmlmap:'entity > trace', 	width: 90, align:'left'  },
			       {name:'issuer', index:'issuer', xmlmap:'entity > issuerid',  width: 90, align:'left'  },
			       {name:'type',   index:'type',   xmlmap:'entity > type', 		width: 60, align:'center'},
			       {name:'proc',   index:'proc',   xmlmap:'entity > process', 	width: 70, align:'center'},
			       {name:'rea',    index:'rea',    xmlmap:'entity > reason', 	width: 50, align:'center'},
			       {name:'res',    index:'res',    xmlmap:'entity > response', 	width: 50, align:'center'},
			       {name:'amt',    index:'amt',    xmlmap:'entity > amt', 		width:150, align:'right' },
			       {name:'curr',   index:'curr',   xmlmap:'entity > currency', 	width: 40, align:'left'  },
			       {name:'set',    index:'set',    xmlmap:'entity > settleamt',	width:100, align:'right' },
			       {name:'cat',    index:'cat',    xmlmap:'entity > category',	width: 50, align:'center'},
			       {name:'group',  index:'group',  xmlmap:'entity > group', 	width: 50, align:'center'},
			       {name:'atmId',  index:'atmId',  xmlmap:'entity > atmid', 	width: 90, align:'left'  },
			       {name:'refer',  index:'refer',  xmlmap:'entity > reference', width:180, align:'left'  },
			       {name:'date',   index:'date',   xmlmap:'entity > settledate',width:100, align:'left'  },
			       {name:'stat',   index:'stat',   xmlmap:'entity > status',	width:150, align:'left'  },
			       {name:'case',   index:'case',   xmlmap:'entity > case', 		width:200, align:'left'  }];
var miReader = {
		root:'data', 
		row:'entity',
		page:'extend > page',
		total:'extend > total',
		records:'extend > records',
		repeatitems:false,
		id : 'id'};
			
function getMiParam(colNames, colModels, pager,reader,cap){
	var miParam = {
			datatype:'xml',
			colNames:colNames,
			colModel:colModels,
			width:1300,
			height:240,
			rowNum:10,
			page:1,
			pager:pager,	
			rowList:[10,50,100],
			viewrecords: true,
		    xmlReader:reader,
			caption: cap
		};
	return miParam;
}