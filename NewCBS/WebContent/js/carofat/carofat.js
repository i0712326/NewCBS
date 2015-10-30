$(function(){
	var $carofatTabs = $('#CAROFAT-FORM-CONT');
	var $carofatImp  = $('#carofat-import #content');
	var $carofatRec  = $('#carofat-reconcile #content');
	var $carofatAdj  = $('#carofat-adjustment #content');
	var $carofatHis  = $('#carofat-history #content');
	
	$carofatTabs.tabs();
	$('#CAROFAT-FORM-CONT .ui-tabs-nav').appendTo('#CAROFAT-FORM-CONT');
	$carofatImp.load('carofat/content/import.html');
	$carofatRec.load('carofat/content/reconcile.html');
	$carofatAdj.load('carofat/content/adjustment.html');
	$carofatHis.load('carofat/content/history.html');
});

var carColNames = ['id','date','card','trace','time','amount','fee','net','terminal','refer','type','status'];

var carId		= {name:'id',		index:'id',			xmlmap:'entity > id', 		width:15, 	align:'left'};
var carDate     = {name:'date', 	index:'date', 		xmlmap:'entity > date', 	width:40, 	align:'left'};
var carCard     = {name:'card',		index:'card',		xmlmap:'entity > card', 	width:50, 	align:'left'};
var carTrace    = {name:'trace',	index:'trace',		xmlmap:'entity > trace', 	width:30,	align:'left'};
var carTime     = {name:'time', 	index:'time',		xmlmap:'entity > time', 	width:30, 	align:'left'};
var carAmt      = {name:'amount',	index:'amount',		xmlmap:'entity > amount', 	width:50, 	align:'right'};
var carFee	    = {name:'fee',		index:'fee',		xmlmap:'entity > fee',		width:30,	align:'right'};
var carNet	    = {name:'net',		index:'net',		xmlmap:'entity > net',		width:50,	align:'right'};
var carTerm	    = {name:'terminal',	index:'terminal',	xmlmap:'entity > terminal',	width:40,	align:'left'};
var carRef	    = {name:'refer',	index:'refer',		xmlmap:'entity > refer',	width:30,	align:'left'};
var carType	    = {name:'type',		index:'type',		xmlmap:'entity > type', 	width:30,	align:'left'};
var carStatus   = {name:'status',	index:'status',		xmlmap:'entity > status', 	width:30,	align:'left'};

var carColModel = [carId,carDate,carCard,carTrace,carTime,carAmt,carFee,carNet,carTerm,carRef,carType,carStatus];

var carReader = { 
        root:'data', 
        row:'entity',
        page:'extend > page', 
        total:'extend > total', 
        records:'extend > records',
        repeatitems:false,
        id : 'id'
    };

function carofatTableParam(p, cap){
	var param = {
			datatype:'xml',
			colNames:carColNames,
			colModel:carColModel,
			width:1200,
			height:230,
			rowNum:10,
			page:1,
			pager:p,	
			rowList:[10,50,100],
			viewrecords: true,
		    xmlReader:carReader,
			caption: cap
		};
	return param;
}
// edit able table
var caropts = {value:"N:N;Y:Y"};
var carEditId		= {name:'id',       index:'id',			xmlmap:'entity > id', 		width:15, 	align:'left', 	editable: false};
var carEditDate     = {name:'date',     index:'date', 		xmlmap:'entity > date', 	width:40, 	align:'left', 	editable: true, edittype:'text'};
var carEditCard     = {name:'card',     index:'card',		xmlmap:'entity > card',	 	width:50, 	align:'left', 	editable: true, edittype:'text'};
var carEditTrace    = {name:'trace',    index:'trace',		xmlmap:'entity > trace', 	width:30,	align:'left', 	editable: true, edittype:'text'};
var carEditTime     = {name:'time',     index:'time',		xmlmap:'entity > time', 	width:30, 	align:'left', 	editable: true, edittype:'text'};
var carEditAmt      = {name:'amount',   index:'amount',		xmlmap:'entity > amount', 	width:50, 	align:'right', 	editable: true, edittype:'text'};
var carEditFee	    = {name:'fee',	    index:'fee',		xmlmap:'entity > fee',		width:30,	align:'right', 	editable: true, edittype:'text'};
var carEditNet	    = {name:'net',	    index:'net',		xmlmap:'entity > net',		width:50,	align:'right', 	editable: true, edittype:'text'};
var carEditTerm	    = {name:'terminal', index:'terminal',	xmlmap:'entity > terminal',	width:40,	align:'left', 	editable: true, edittype:'text'};
var carEditRef	    = {name:'refer',    index:'refer',		xmlmap:'entity > refer',	width:30,	align:'left', 	editable: true, edittype:'text'};
var carEditType	    = {name:'type',     index:'type',		xmlmap:'entity > type', 	width:30,	align:'left', 	editable: true, edittype:'text'};
var carEditStatus   = {name:'status',   index:'status',		xmlmap:'entity > status', 	width:30,	align:'left', 	editable: true, edittype:"select", editoptions:caropts};

var carEditModel = [carEditId,
                    carEditDate,
                    carEditCard,
                    carEditTrace,
                    carEditTime,
                    carEditAmt,
                    carEditFee,
                    carEditNet,
                    carEditTerm,
                    carEditRef,
                    carEditType,
                    carEditStatus];

function carofatEditTableParam(p,cap){
	var param = {
			datatype:'xml',
			colNames:carColNames,
			colModel:carEditModel,
			width:1300,
			height:230,
			rowNum:10,
			page:1,
			pager:p,	
			rowList:[10,50,100],
			multiselect:true,
		    xmlReader:carReader,
			caption: cap,
			loadComplete: function () {
		        var $this = $(this), ids = $this.jqGrid('getDataIDs'), i, l = ids.length;
		        for (i = 0; i < l; i++) {
		            $this.jqGrid('editRow', ids[i], true);
		        }
		    }
		};
	return param;
}

function CarObj(date,card,trace,time,amount,fee,net,terminal,refer,type,status){
	this.date = date;
	this.card = card;
	this.trace = trace;
	this.time = time;
	this.amount = amount;
	this.fee = fee;
	this.net = net;
	this.terminal = terminal;
	this.refer = refer;
	this.type = type;
	this.status = status;
}

function cupRemoteUsAtmXml(objs){
	var xmlDoc = '<?xml version="1.0" encoding="UTF-8" ?>';
	var len = objs.length;
	xmlDoc = xmlDoc+'<cup>';
	xmlDoc = xmlDoc+'<header>';
	xmlDoc = xmlDoc+'<records>'+len+'</records>';
	xmlDoc = xmlDoc+'</header>';
	xmlDoc = xmlDoc+'<data>';
	for(var i=0;i<len;i++){
		var obj      = objs[i];
		var date     = obj.date;
		var card     = obj.card;
		var time	 = obj.time;
		var trace    = obj.trace;
		var amount   = obj.amount;
		var fee      = obj.fee;
		var net		 = obj.net;
		var terminal = obj.terminal;
		var refer  	 = obj.refer;
		var type 	 = obj.type;
		var status 	 = obj.status;
		var index    = i+1;
		var entity = '<entity id="' +index+'">';
		entity = entity+'<date>'	+date+'</date>';
		entity = entity+'<card>'	+card+'</card>';
		entity = entity+'<time>'	+time+'</time>';
		entity = entity+'<trace>'	+trace+'</trace>';
		entity = entity+'<amount>'	+amount+'</amount>';
		entity = entity+'<fee>'		+fee+'</fee>';
		entity = entity+'<net>'		+net+'</net>';
		entity = entity+'<terminal>'+terminal+'</terminal>';
		entity = entity+'<refer>'	+refer+'</refer>';
		entity = entity+'<type>'	+type+'</type>';
		entity = entity+'<status>'	+status+'</status>';
		entity = entity+'</entity>';
		xmlDoc = xmlDoc+entity;
	}
	xmlDoc = xmlDoc+'</data>';
	xmlDoc = xmlDoc+'</cup>';
	console.log(xmlDoc);
	return xmlDoc;
}

function submitValues(url, params) {
    var form = [ '<form method="POST" action="', url, '">' ];
    for(var key in params) 
        form.push('<input type="hidden" name="', key, '" value="', params[key], '"/>');
    form.push('</form>');
    $(form.join('')).appendTo('body').submit();
    return false;
}