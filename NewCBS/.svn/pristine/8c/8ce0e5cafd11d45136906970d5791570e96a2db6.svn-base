$(function(){
	var $varofatTabs = $('#VAROFAT-FORM-CONT');
	var $varofatImp  = $('#varofat-import #content');
	var $varofatRec  = $('#varofat-reconcile #content');
	var $varofatAdj  = $('#varofat-adjustment #content');
	var $varofatHis  = $('#varofat-history #content');
	$varofatTabs.tabs();
	$("#VAROFAT-FORM-CONT .ui-tabs-nav").appendTo("#VAROFAT-FORM-CONT");
	// load content
	$varofatImp.load('varofat/content/import.html');
	$varofatRec.load('varofat/content/reconcile.html');
	$varofatAdj.load('varofat/content/adjustment.html');
	$varofatHis.load('varofat/content/history.html');
});

function Opt(stat,casenum){
	this.stat = stat;
	this.casenum = casenum;
}

function visaAtmXml(objs,opts){
	var xmlDoc = '<?xml version="1.0" encoding="UTF-8" ?>';
	var len = objs.length;
	xmlDoc = xmlDoc+'<visas>';
	xmlDoc = xmlDoc+'<header>';
	xmlDoc = xmlDoc+'<records>'+len+'</records>';
	xmlDoc = xmlDoc+'</header>';
	xmlDoc = xmlDoc+'<data>';
	for(var i=0;i<len;i++){
		var obj     = objs[i];
		var opt     = opts[i];
		var bat     = obj.bat;
		var txndate = obj.txnd;
		var txntime = obj.txnt;
		var card    = obj.card;
		var trace   = obj.trace;
		var retr    = obj.retr;
		var issuer  = obj.issuer;
		var atmid	= obj.atmid;
		var type    = obj.type;
		var status  = opt.stat;
		var casenum = opt.casenum;
		var index   = i+1;
		var entity = '<entity id="'+index+'">';
		entity = entity+'<batid>'+bat+'</batid>';
		entity = entity+'<txndate>'+txndate+'</txndate>';
		entity = entity+'<txntime>'+txntime+'</txntime>';
		entity = entity+'<card>'+card+'</card>';
		entity = entity+'<trace>'+trace+'</trace>';
		entity = entity+'<retr>'+retr+'</retr>';
		entity = entity+'<issuer>'+issuer+'</issuer>';
		entity = entity+'<atmid>'+atmid+'</atmid>';
		entity = entity+'<type>'+type+'</type>';
		entity = entity+'<status>'+status+'</status>';
		entity = entity+'<casenum>'+casenum+'</casenum>';
		
		entity = entity+'</entity>';
		xmlDoc = xmlDoc+entity;
	}
	xmlDoc = xmlDoc+'</data>';
	xmlDoc = xmlDoc+'</visas>';
	console.log(xmlDoc);
	return xmlDoc;
}

var cNames = ['id','bat','txnd','txnt','card','retr','trace','issuer','type','proc','rea',
              'res','atm','curr','set','cat','group','atmid','refer','date','stat','casenum'];

var option = {value:"ACC:ACC;DEC:DEC;REP:REP;RPS:RPS;ACP:ACP;DRP:DRP"};

var eModel = [ {name:'id',     index:'id',     xmlmap:'entity > id', 		width: 50, align:'left'  },
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
		       {name:'atmid',  index:'atmid',  xmlmap:'entity > atmid', 	width: 90, align:'left'  },
		       {name:'refer',  index:'refer',  xmlmap:'entity > reference', width:180, align:'left'  },
		       {name:'date',   index:'date',   xmlmap:'entity > settledate',width:100, align:'left'  },
		       {name:'stat',   index:'stat',   xmlmap:'entity > status',	width:150, align:'left', editable: true,edittype:"select",editoptions:option},
		       {name:'casenum',index:'casenum',xmlmap:'entity > case',  	width:200, align:'left', editable: true}];

var cModel = [ {name:'id',     index:'id',     xmlmap:'entity > id', 		width: 50, align:'left'  },
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
		       {name:'atmid',  index:'atmid',  xmlmap:'entity > atmid', 	width: 90, align:'left'  },
		       {name:'refer',  index:'refer',  xmlmap:'entity > reference', width:180, align:'left'  },
		       {name:'date',   index:'date',   xmlmap:'entity > settledate',width:100, align:'left'  },
		       {name:'stat',   index:'stat',   xmlmap:'entity > status',	width:150, align:'left'  },
		       {name:'casenum',index:'casenum',xmlmap:'entity > case',  	width:200, align:'left'  }];
var  reader={ 
        root:'data', 
        row:'entity',
        page:'extend > page', 
        total:"extend > total", 
        records:"extend > records",
        repeatitems:false,
        id : "id"
    };

function getTableParam(p,cap){
	var tableParam = {
			datatype:'xml',
			colNames:cNames,
			colModel:cModel,
			width:1300,
			height:240,
			rowNum:10,
			page:1,
			pager:p,	
			rowList:[10,50,100],
			viewrecords: true,
		    xmlReader:reader,
			caption: cap
		};
	return tableParam;
}

function getEditTableParam(p,cap){
	var tableParam = {
				datatype:'xml',
				colNames:cNames,
				colModel:eModel,
				width:1300,
				height:240,
			   	rowNum:10,
			   	page:1,
			   	pager:p,
			   	rowList:[10,50,100],
			    viewrecords: true,
			    multiselect:true,
			    multiboxonly:true,
			    xmlReader:reader,
			    caption: cap,
			    loadComplete: function () {
			        var $this = $(this), ids = $this.jqGrid('getDataIDs'), i, l = ids.length;
			        for (i = 0; i < l; i++) {
			            $this.jqGrid('editRow', ids[i], true);
			        }
			    }
		};
	return tableParam;
}

