<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/element-control-script.js"></script>
<script type="text/javascript" src="js/minimizable_dialog/jquery.dialogextend.1_0_1.js"></script>

<!-- jqgrid data -->
<link rel="stylesheet" href="css/jqgrid/css/ui.jqgrid.css" />

<script src="js/jqgrid/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jqgrid/jquery.jqGrid.src.js" type="text/javascript"></script>

<!-- The basic File Upload plugin -->
<script type="text/javascript" src="js/aarofat/reconcile/aarofat_reconcile.js"></script>
<div id="tabs" class="tabs-bottom">
			<ul>
				<li><a href="#RECONCILE">Reconcile</a></li>
				<li><a href="#tabs-2">Adjustment</a></li>
				<li><a href="#tabs-3">History</a></li>
			</ul>
			<div class="tabs-spacer"></div>
			<div id="RECONCILE" style="text-align:left;">
				<div id="AAROFAT-TIPS"></div>
				<div id="AAROFAT-LABEL"></div>
				<div id="AAROFAT-MAIN-CONTENT">
						<div id="AAROFAT-CONTENT">
						<p style="margin:15px;">
							<label><strong>ATM ID : </strong></label><input type="text" id="atmId" size="8"/>
							<a href="#" class="ui-icon ui-icon-extlink" style="display:inline-block;" id="atmId-ext" title="external link"></a>
							<label><strong>Date : </strong></label>
							<input type="text" id="from-date" size="10"/>
							<span style="margin-left:5px;margin-right:5px;"> - </span> 
							<input type="text" id="to-date" size="10"/>
						</p>
						<p style="margin:15px;">
							<label> <strong>Settlement : </strong></label><input type="file" id="statement" name="files" size="10"/>
							<label> <strong>Journal : </strong></label><input type="file" id="ejournal" name="files" size="10"/>
						</p>
						<button id="AAROFAT-AUDIT" style="margin-left:500px;">Process</button>
						<!-- fetch data for ATM Id -->
						<div id="ATMID-EXT-DIALOG">
							<div id="ATMID-EXT-DIALOG-CONTENT"></div>
						</div>
					</div>
				</div>
				<div id="AAROFAT-PROCESS" style="text-align:center;margin:50px;">
							<div id="AAROFAT-PROCESS-CONTENT"></div>
							<div id="AAROFAT-SUMMSG">
								<div id="AAROFAT-FORM-INFO" class="ui-state-highlight ui-corner-all" style="margin:8px;">
									<label for="atmId"><strong>ATM ID : </strong></label>
									<input name="atmId" id="atmId" style="text-align:right;" size="9" disabled/>,
									<label for="start"><strong>Date : </strong></label>
									<input name="start" id="start" style="text-align:right;" size="11" disabled/>
									<label for="end"><strong>-</strong></label>
									<input name="end" id="end" style="text-align:right;" size="11" disabled/>
								</div>
								<div id="NO-GL-SUMMARY" class="ui-state-highlight ui-corner-all" style="margin:8px;">
									<div style="margin: 3px;">
										<label for="no-gl-records" style="padding-left:55px;"><strong> No GL : </strong></label>
										<input name="no-gl-records" id="no-gl-record" style="text-align:right;" size="7" disabled/> Record(s),
										<label for="no-gl-amount"><strong>Amount : </strong></label>
										<input name="no-gl-amount" id="no-gl-amount" style="text-align:right;" size="15" disabled/> LAK
										<a href="#" class="ui-icon ui-icon-extlink" style="display:inline-block;" id="no-gl-view" title="external link"></a>
									</div>
									<div style="margin: 3px;">
									<label for="no-dispense-record"><strong>No Dispense :</strong></label>
									<input name="no-dispense-record" id="no-dispense-record" style="text-align:right;" size="7" disabled/> Record(s),
									<label for="no-dispense-amount"><strong>Amount : </strong></label>
									<input name="no-dispense-amount" id="no-dispense-amount"  style="text-align:right;" size="15" disabled/> LAK
									<a href="#" class="ui-icon ui-icon-extlink" style="display:inline-block;" id="no-dispense-view" title="external link"></a>
									</div>
								</div>
							</div>
							<div id="AAROFAT-RESULT-NO-GL">
								<div id="TXTALERT-TIPS"></div>
								<div id="TXTALERT-LABEL"></div>
								<table id="NO-GL-TABLE"></table>
								<button id="no-gl-button" style="margin:15px 0px 0px 850px;">CONFIRM</button>
							</div>
							<div id="AAROFAT-RESULT-NO-DISPENSE">
								<div id="TXTALERT-TIPS"></div>
								<div id="TXTALERT-LABEL"></div>
								<table id="NO-DISPENSE-TABLE"></table>
								<button id="no-dispense-button" style="margin:15px 0px 0px 850px;">CONFIRM</button>
							</div>
							<div id="AAROFAT-FINAL-RESULT">
								<div id="RESPON"></div>
							</div>
				</div>
			</div>
			<div id="tabs-2">
				tab-2
			</div>
			<div id="tabs-3">
				tab-3
			</div>
		</div>