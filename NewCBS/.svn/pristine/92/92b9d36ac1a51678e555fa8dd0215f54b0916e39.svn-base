var data1;
var data2;

$("#tabs").tabs();

// fix the classes
$("#tabs .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *")
.removeClass("ui-corner-all ui-corner-top")
.addClass("ui-corner-bottom");

// move the nav to the bottom
$("#tabs .ui-tabs-nav").appendTo("#tabs");

// date picker
$('#AAROFAT-AUDIT').button();

$("#from-date").datepicker({
	showOn : "button",
	buttonImage : "css/img/calendar.gif",
	buttonImageOnly : true,
	defaultDate : "+1w",
	changeMonth : true,
	changeYear : true,
	numberOfMonths : 1,
	onClose : function(selectedDate) {
		$("#to-date").datepicker("option", "minDate", selectedDate);
	}
});

$("#to-date").datepicker({
	showOn : "button",
	buttonImage : "css/img/calendar.gif",
	buttonImageOnly : true,
	defaultDate : "+1w",
	changeMonth : true,
	changeYear : true,
	numberOfMonths : 1,
	onClose : function(selectedDate) {
		$("#from-date").datepicker("option", "maxDate", selectedDate);
	}
});

// dailog for external link

$('#atmId-ext').live('click', function() {
	$('#ATMID-EXT-DIALOG').dialog('open');
	$('#AAROFAT-FORM').block({
		message : null
	});
});

$('#ATMID-EXT-DIALOG').dialog({
	title : 'ATM ID',
	autoOpen : false,
	width : 630,
	hieght : 380,
	position : [ 0, 0 ],
	buttons : {
		'Exit' : function() {
			$(this).dialog('close');
			$('#AAROFAT-FORM').unblock();
		}
	},
	open : function() {
		$.get('aarofat/reconcile/aarofat_ext.jsp', function(data) {
			$('#ATMID-EXT-DIALOG-CONTENT').html(data);
		});
	},
	close : function() {
		$('#AAROFAT-FORM').unblock();
	}
});

// processing dailog

$('#AAROFAT-PROCESS').dialog({
	autoOpen : false,
	width : 500,
	height : 380,
	position : [ 270, 50 ],
	modal : true,
	buttons : {
		'Exit' : function() {
			$(this).dialog('close');
		}
	},
	open : function() {
		$('#AAROFAT-PROCESS-CONTENT').load('processing.html');
	},
	close : function() {
		$('#AAROFAT-PROCESS-CONTENT').empty();
	}
});
// process result
$('#AAROFAT-RESULT-NO-GL').dialog(
		{
			title : 'NO-GL',
			autoOpen : false,
			modal : true,
			position : [ 250, 80 ],
			width : 1000,
			height : 450,
			buttons : {
				'Exit' : function() {
					$(this).dialog('close');
				}
			},
			close : function() {
				$('#AAROFAT-RESULT-NO-GL #TXTALERT-LABEL').html('');
				$('#AAROFAT-RESULT-NO-GL #TXTALERT-LABEL').removeClass(
						'ui-state-error');
			}
		});

$('#AAROFAT-RESULT-NO-DISPENSE').dialog(
		{
			title : 'NO-DISPENSE',
			autoOpen : false,
			modal : true,
			position : [ 250, 80 ],
			width : 1000,
			height : 450,
			buttons : {
				'Exit' : function() {
					$(this).dialog('close');
				}
			},
			close : function() {
				$('#AAROFAT-RESULT-NO-DISPENSE #TXTALERT-LABEL').html('');
				$('#AAROFAT-RESULT-NO-DISPENSE #TXTALERT-LABEL').removeClass(
						'ui-state-error');
			}
		});

// summary result dialog
$('#AAROFAT-SUMMSG').dialog({
	title : 'AAROFAT-SUMMARY',
	autoOpen : false,
	position : [ 250, 80 ],
	width : 800,
	height : 290,
	buttons : {
		'Exit' : function() {
			$(this).dialog('close');
		}
	},
	open : function() {
		$('#AAROFAT-FORM').block({
			message : null
		});
	},
	close : function() {
		$('#AAROFAT-FORM').unblock();
	}
});
// button for summary page
// button click feature
$('#no-gl-view').live('click', function() {
	$('#AAROFAT-RESULT-NO-GL').dialog('open');
	var dataList = data1;
	if (dataList != null) {
		$("#NO-GL-TABLE").jqGrid("clearGridData", true);
		$('#NO-GL-TABLE').setGridParam({
			datatype : 'local'
		}).trigger("reloadGrid");

		for ( var i = 0; i < dataList.length; i++)
			$('#NO-GL-TABLE').jqGrid('addRowData', i + 1, dataList[i]);
	}
});
// feature when click the button
$('#no-dispense-view').live('click', function() {
	$('#AAROFAT-RESULT-NO-DISPENSE').dialog('open');
	var dataList = data2;
	if (dataList != null) {
		$("#NO-DISPENSE-TABLE").jqGrid("clearGridData", true);
		$('#NO-DISPENSE-TABLE').setGridParam({
			datatype : 'local',
		}).trigger("reloadGrid");

		for ( var i = 0; i < dataList.length; i++)
			$('#NO-DISPENSE-TABLE').jqGrid('addRowData', i + 1, dataList[i]);
	}

});

// data table

$('#NO-GL-TABLE').jqGrid(
		{
			colNames : [ 'No', 'Date', 'Card', 'Tsq', 'Amount', 'Account',
					'Status', 'Notes', 'Type' ],
			colModel : [ {
				name : 'id',
				index : 'id',
				width : 8,
				align : 'right'
			}, {
				name : 'date',
				index : 'date',
				width : 25
			}, {
				name : 'card',
				index : 'card',
				width : 35
			}, {
				name : 'tsq',
				index : 'tsq',
				width : 15
			}, {
				name : 'amount',
				index : 'amount',
				width : 25,
				align : 'right'
			}, {
				name : 'account',
				index : 'account',
				width : 30
			}, {
				name : 'status',
				index : 'status',
				width : 25,
				editable : true,
				edittype : "select",
				editoptions : {
					value : ":select;UNPAID:UNPAID;PAID:PAID"
				}
			}, {
				name : 'notice',
				index : 'notice',
				width : 35,
				sortable : false
			}, {
				name : 'type',
				index : 'type',
				width : 15
			} ],
			autoheight : true,
			width : 970,
			sortname : 'card',
			viewrecords : true,
			multiselect : true,
			onSelectRow : function(data) {
				$('#NO-GL-TABLE').jqGrid('editRow', data, true, null);
			},
			sortorder : "desc",
			caption : "Suspended Transaction for no debit gl"
		});

// table for data of no cash dispense

$('#NO-DISPENSE-TABLE').jqGrid(
		{
			colNames : [ 'No', 'Date', 'Card', 'Tsq', 'Amount', 'Account',
					'Status', 'Notes', 'Type' ],
			colModel : [ {
				name : 'id',
				index : 'id',
				width : 8,
				align : 'right'
			}, {
				name : 'date',
				index : 'date',
				width : 25
			}, {
				name : 'card',
				index : 'card',
				width : 35
			}, {
				name : 'tsq',
				index : 'tsq',
				width : 15
			}, {
				name : 'amount',
				index : 'amount',
				width : 25,
				align : 'right'
			}, {
				name : 'account',
				index : 'account',
				width : 30
			}, {
				name : 'status',
				index : 'status',
				width : 25,
				editable : true,
				edittype : "select",
				editoptions : {
					value : ":select;UNPAID:UNPAID;PAID:PAID"
				}
			}, {
				name : 'notice',
				index : 'notice',
				width : 35,
				sortable : false
			}, {
				name : 'type',
				index : 'type',
				width : 15
			} ],
			autoheight : true,
			width : 970,
			sortname : 'card',
			viewrecords : true,
			multiselect : true,
			onSelectRow : function(data) {
				$('#NO-DISPENSE-TABLE').jqGrid('editRow', data, true, null);
			},
			sortorder : "desc",
			caption : "Suspended Transaction for no cash dispense"
		});

// button on table
$('#no-gl-button').button();
// button action
$('#no-gl-button')
		.live(
				'click',
				function() {
					var data = [];
					var check = true;
					$('#NO-GL-TABLE tr').each(
							function() {
								var test = $(this).find(
										'td input[type=checkbox]');
								if (test.is(':checked')) {
									var date = $(this).find('td:eq(2)').text()
											.trim();
									var card = $(this).find('td:eq(3)').text()
											.trim();
									var tsq = $(this).find('td:eq(4)').text()
											.trim();
									var amount = $(this).find('td:eq(5)')
											.text().trim();
									var account = $(this).find('td:eq(6)')
											.text().trim();
									var status = $(this).find('td select')
											.val().trim();
									var notice = $(this).find('td:eq(8)')
											.text().trim();
									var type = $(this).find('td:eq(9)').text()
											.trim();
									var stat = $(this).find('td select');
									// validate
									check = check && validateSelect(stat);

									var errorTxn = new ErrorTxn(date, card,
											tsq, amount, status, account,
											notice, type);
									data.push(errorTxn);
								}
							});

					// data submit
					var dataXml = ErrorTxnXml(data);
					
					if (dataXml == null) {
						var tips = $('#AAROFAT-RESULT-NO-GL #TXTALERT-TIPS');
						var label = $('#AAROFAT-RESULT-NO-GL #TXTALERT-LABEL');
						updateTips("Item is not selected !", tips, label);
						check = false;
					}

					if (check == true) {
						$('#AAROFAT-RESULT-NO-GL #TXTALERT-LABEL').html('');
						$('#AAROFAT-RESULT-NO-GL').dialog('close');
						$('#AAROFAT-PROCESS').dialog('open');
						// send data to server to process request
						
						$.post('aarofat/reconcile/persistData.action',
										{
											dataXml : dataXml
										},
										function(response) {
											$('#AAROFAT-PROCESS').dialog(
													'close');
											var rec = $(response)
													.find('record').text()
													.trim();
											if (rec != '0') {
												var view = "<div class='ui-widget'>"
														+ "<div class='ui-state-highlight ui-corner-all' style='margin-top: 20px; padding: 0 .7em;'>"
														+ "<p><span class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></span>"
														+ "<strong>Successful !</strong> Persisted "
														+ rec
														+ " record(s).</p>"
														+ "</div>" + "</div>";
												$(
														'#AAROFAT-FINAL-RESULT #RESPON')
														.html(view);
											} else {
												var view = "<div class='ui-widget'>"
														+ "<div class='ui-state-error ui-corner-all' style='margin-top: 20px; padding: 0 .7em;'>"
														+ "<p><span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span>"
														+ "<strong>Error !</strong> Can't Persist data.</p>"
														+ "</div>" + "</div>";
												$('#AAROFAT-FINAL-RESULT #RESPON')
														.html(view);
											}

											$('#AAROFAT-FINAL-RESULT').dialog(
													'open');
										});

					}
				});
// no cash dispense button
$('#no-dispense-button').button();
// functional
$('#no-dispense-button')
		.live(
				'click',
				function() {
					var data = [];
					var check = true;
					var atmId = $('#AAROFAT-FORM-INFO #atmId').val().trim();
					$('#NO-DISPENSE-TABLE tr').each(
							function() {
								var test = $(this).find(
										'td input[type=checkbox]');
								if (test.is(':checked')) {
									var date = $(this).find('td:eq(2)').text()
											.trim();
									var card = $(this).find('td:eq(3)').text()
											.trim();
									var tsq = $(this).find('td:eq(4)').text()
											.trim();
									var amount = $(this).find('td:eq(5)')
											.text().trim();
									var account = $(this).find('td:eq(6)')
											.text().trim();
									var status = $(this).find('td select')
											.val().trim();
									var notice = $(this).find('td:eq(8)')
											.text().trim();
									var type = $(this).find('td:eq(9)').text()
											.trim();
									var stat = $(this).find('td select');
									// validate
									check = check && validateSelect(stat);

									var errorTxn = new ErrorTxn(date, card,
											tsq, amount, status, account,
											notice, atmId, type);
									data.push(errorTxn);
								}
							});

					// data submit
					var dataXml = ErrorTxnXml(data);

					if (dataXml == null) {
						var tips = $('#AAROFAT-RESULT-NO-DISPENSE #TXTALERT-TIPS');
						var label = $('#AAROFAT-RESULT-NO-DISPENSE #TXTALERT-LABEL');
						updateTips("Item is not selected !", tips, label);
						check = false;
					}

					if (check == true) {
						$('#AAROFAT-RESULT-NO-DISPENSE #TXTALERT-LABEL').html(
								'');
						$('#AAROFAT-PROCESS').dialog('open');
						// send data to server to process request
						$
								.post(
										'aarofat/reconcile/persistData.action',
										{
											dataXml : dataXml
										},
										function(response) {
											$('#AAROFAT-PROCESS').dialog(
													'close');
											var rec = $(response)
													.find('record').text()
													.trim();
											if (rec != '0') {
												var view = "<div class='ui-widget'>"
														+ "<div class='ui-state-highlight ui-corner-all' style='margin-top: 20px; padding: 0 .7em;'>"
														+ "<p><span class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></span>"
														+ "<strong>Successful !</strong> Persisted "
														+ rec
														+ " record(s).</p>"
														+ "</div>" + "</div>";
												$(
														'#AAROFAT-FINAL-RESULT #RESPON')
														.html(view);
											} else {
												var view = "<div class='ui-widget'>"
														+ "<div class='ui-state-error ui-corner-all' style='margin-top: 20px; padding: 0 .7em;'>"
														+ "<p><span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span>"
														+ "<strong>Error !</strong> Can't Persist data.</p>"
														+ "</div>" + "</div>";
												$(
														'#AAROFAT-FINAL-RESULT #RESPON')
														.html(view);
											}
											$('#AAROFAT-FINAL-RESULT').dialog(
													'open');
										});

					}
				});
$("#AAROFAT-FINAL-RESULT").dialog({
	title : '',
	autoOpen : false,
	width : 450,
	height : 250,
	position : [ 270, 50 ],
	modal : true,
	buttons : {
		'Exit' : function() {
			$(this).dialog('close');
		}
	}
});

$('#AAROFAT-AUDIT').live(
		'click',
		function() {

			// validation for submit form
			var tips = $('#AAROFAT-TIPS');
			var label = $('#AAROFAT-LABEL');
			var atmId = $('#AAROFAT-CONTENT #atmId');
			var start = $('#AAROFAT-CONTENT #from-date');
			var end = $('#AAROFAT-CONTENT #to-date');

			var statement = $('#AAROFAT-CONTENT #statement');
			var ejournal = $('#AAROFAT-CONTENT #ejournal');

			var allFields = $([]).add(atmId).add(start).add(end).add(statement)
					.add(ejournal).add(label);

			var bValid = true;

			allFields.removeClass("ui-state-error");
			label.text('');

			bValid = bValid && checkLength(atmId, "ATM ID", 8, 9, tips, label);
			bValid = bValid
					&& checkLength(start, "Start Date", 10, 19, tips, label);
			bValid = bValid
					&& checkLength(end, "End Date", 10, 19, tips, label);

			bValid = bValid
					&& checkLength(statement, "statement", 6, 80, tips, label);
			bValid = bValid
					&& checkLength(ejournal, "ejournal", 6, 80, tips, label);

			if (bValid) {
				$('#AAROFAT-PROCESS').dialog('open');
				var atmIdVal = atmId.val();
				var startVal = start.val();
				var endVal = end.val();
				var formAarofat = $('#AAROFAT-CONTENT').clone();
				$('#AAROFAT-CONTENT').upload(
						'aarofat/reconcile/reconcileFile.action',
						{
							atmId : atmIdVal,
							start : startVal,
							end : endVal
						},
						function(data) {
							// close dialog
							$('#AAROFAT-PROCESS').dialog('close');

							// get content back after submit form
							$('#AAROFAT-MAIN-CONTENT').empty();
							$('#AAROFAT-MAIN-CONTENT').append(formAarofat);

							// receive data and transform to object

							var noGlRec = $(data).find('no-gl-rec').text()
									.trim();
							var noGlAmt = $(data).find('no-gl-amt').text()
									.trim();

							var noDispenseRec = $(data).find('no-dispense-rec')
									.text().trim();
							var noDispenseAmt = $(data).find('no-dispense-amt')
									.text().trim();

							var noGlList = getNoGlList(data);
							var noDispenseList = getNoDispenseList(data);

							data1 = noGlList;
							data2 = noDispenseList;

							// save data in session storage

							// insert into form
							$('#no-gl-record').val(noGlRec);
							$('#no-gl-amount').val(noGlAmt);

							if (noGlRec == '0')
								$('#no-gl-view').hide();
							else
								$('#no-gl-view').show();

							$('#no-dispense-record').val(noDispenseRec);
							$('#no-dispense-amount').val(noDispenseAmt);
							if (noDispenseRec == '0')
								$('#no-dispense-view').hide();
							else
								$('#no-dispense-view').show();

							// set header detail
							var atmId = $(data).find('atmId').text().trim();
							var start = $(data).find('start').text().trim();
							var end = $(data).find('end').text().trim();

							$('#AAROFAT-FORM-INFO #atmId').val(atmId);
							$('#AAROFAT-FORM-INFO #start').val(start);
							$('#AAROFAT-FORM-INFO #end').val(end);

							// open dialog
							$('#AAROFAT-SUMMSG').dialog('open');

						}, 'html');
			}
		});

/** ************* data process function **************** */

// function for process data
function getNoGlList(xmlData) {
	var $xmlDoc = $(xmlData);
	var list = [];
	$xmlDoc.find('no-gl-row row').each(
			function() {
				var $row = $(this);
				var id = $row.attr('id').trim();
				var date = $row.find('date').text().trim();
				var card = $row.find('cardnumber').text().trim();
				var tsq = $row.find('tsq').text().trim();
				var amount = $row.find('amount').text().trim();
				var status = "select";
				var account = $row.find('account').text().trim();
				var notice = $row.find('notice').text().trim();
				var type = $row.find('type').text().trim();
				var errorEntity = new ErrorEntity(id, date, card, tsq, amount,
						status, account, notice, type);
				list.push(errorEntity);
			});
	return list;
}

function getNoDispenseList(xmlData) {
	var $xmlDoc = $(xmlData);
	var list = [];
	$xmlDoc.find('no-dispense-row row').each(
			function() {
				var $row = $(this);
				var id = $row.attr('id').trim();
				var date = $row.find('date').text().trim();
				var card = $row.find('cardnumber').text().trim();
				var tsq = $row.find('tsq').text().trim();
				var amount = $row.find('amount').text().trim();
				var status = "select";
				var account = $row.find('account').text().trim();
				var notice = $row.find('notice').text().trim();
				var type = $row.find('type').text().trim();
				var errorEntity = new ErrorEntity(id, date, card, tsq, amount,
						status, account, notice, type);
				list.push(errorEntity);
			});
	return list;
}

function validateSelect(status) {
	var v = status.val().trim();
	if (v != "") {
		status.removeClass('ui-state-error');
		return true;
	} else {
		tips = $('#TXTALERT-TIPS');
		label = $('#TXTALERT-LABEL');
		updateTips("Status is not selected !", tips, label);
		status.addClass("ui-state-error");
		return false;
	}
}

function ErrorTxn(date, card, tsq, amount, status, account, notice, atmId, type) {
	this.date = date;
	this.card = card;
	this.tsq = tsq;
	this.amount = amount;
	this.status = status;
	this.account = account;
	this.notice = notice;
	this.atmId = atmId;
	this.type = type;
}

function ErrorEntity(id, date, card, tsq, amount, status, account, notice, type) {
	this.id = id;
	this.date = date;
	this.card = card;
	this.tsq = tsq;
	this.amount = amount;
	this.status = status;
	this.account = account;
	this.notice = notice;
	this.type = type;
}

function ErrorTxnXml(data) {
	if (data.length > 0) {
		var xmlRecord = "<?xml version='1.0' encoding='utf-8'?>";
		xmlRecord += "<records>";
		var i;
		for (i = 0; i < data.length; i++) {
			xmlRecord += "<record>";
			var date = data[i].date;
			var card = data[i].card;
			var tsq = data[i].tsq;
			var amount = data[i].amount;
			var status = data[i].status;
			var account = data[i].account;
			var notice = data[i].notice;
			var atmId = data[i].atmId;
			var type = data[i].type;
			xmlRecord += "<date>" + date + "</date>";
			xmlRecord += "<card>" + card + "</card>";
			xmlRecord += "<tsq>" + tsq + "</tsq>";
			xmlRecord += "<amount>" + amount + "</amount>";
			xmlRecord += "<status>" + status + "</status>";
			xmlRecord += "<account>" + account + "</account>";
			xmlRecord += "<notice>" + notice + "</notice>";
			xmlRecord += "<atmId>" + atmId + "</atmId>";
			xmlRecord += "<type>" + type + "</type>";
			xmlRecord += "</record>";
		}
		xmlRecord += "</records>";
		return xmlRecord;
	}
	return null;
}

// xml to Array for jqgrid

function xmlToArray(data) {
	var $xmlDoc = $(data);
	var list = [];
	$xmlDoc.find('row').each(
			function() {
				var $row = $(this);
				var id = $row.attr('id').trim();
				var date = $row.find('date').text().trim();
				var card = $row.find('cardnumber').text().trim();
				var tsq = $row.find('tsq').text().trim();
				var amount = $row.find('amount').text().trim();
				var status = "select";
				var account = $row.find('account').text().trim();
				var notice = $row.find('notice').text().trim();
				var type = $row.find('type').text().trim();
				var errorEntity = new ErrorEntity(id, date, card, tsq, amount,
						status, account, notice, type);
				list.push(errorEntity);
			});
	return list;
}