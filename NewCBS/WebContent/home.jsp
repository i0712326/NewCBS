<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="us">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/flick/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<!-- data Table css -->
<link href="css/dataTable/jquery.dataTables_themeroller.css" rel="stylesheet">
<!-- Flick Style Sheet -->
<link href="css/tree_list/jquery.treeview.css" rel="stylesheet">
<link href="css/jqgrid/css/ui.jqgrid.css" rel="stylesheet">
<link href="css/main.style.css" rel="stylesheet">

<!-- Flick javascript for control UI -->

<script src="js/jquery-1.8.3.js"></script>
<script src="js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="js/element-control-script.js"></script>
<script type="text/javascript" src="js/tree_list/jquery.treeview.js"></script>
<script type="text/javascript" src="js/tree_list/jquery.cookie.js"></script>
<script type="text/javascript" src="js/blockui/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/minimizable_dialog/jquery.dialogextend.1_0_1.js"></script>
<script type="text/javascript" src="js/fileUpload/jquery.upload-1.0.2.js"></script>
<script type="text/javascript" src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/jqgrid/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="js/jqgrid/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="js/aarofat/aarofat.js"></script>
<script type="text/javascript" src="js/carofat/carofat.js"></script>
<script type="text/javascript" src="js/varofat/varofat.js"></script>
<script type="text/javascript" src="js/marofat/marofat.js"></script>
<script type="text/javascript" src="js/main.control.js"></script>
<title>Home</title>
</head>
<body>
	<div id="header">
		<input type="text" name="fundId" id="funcId" size="10" class="tb_from"/>
		<div id="info_element" class="element_position">
			<a href="#" class="ui-state-default ui-corner-all element_att ui-icon ui-icon-unlocked" title="logout"></a>
			<a href="#" class="ui-state-default ui-corner-all element_att ui-icon ui-icon-search" title="search user"></a>
			<a href="#" class="ui-state-default ui-corner-all element_att ui-icon ui-icon-gear" title="update profile"></a>
			<a href="#" id="CPASSWD-BUTT" class="ui-state-default ui-corner-all element_att ui-icon ui-icon-key" title="change password"></a>
			<a href="#" id="PROFILE-BUTT" class="ui-state-default ui-corner-all element_att ui-icon ui-icon-person" title="profile"></a>
			
			<a href="#" id="GO-BUTT" class="ui-state-default ui-corner-all ui-icon ui-icon-arrowthick-1-e element_att" title="Go"></a>
		</div>
	</div>
	<div id="menu_part" class="ui-widget-content">
		<div id="accordion">
			<jsp:include page="navigation/menu-accounting.jsp"/>
			<h3>Customer Care</h3>
			<div>Phasellus mattis tincidunt nibh.</div>
			<h3>Administrator</h3>
			<div>Nam dui erat, auctor a, dignissim quis.</div>
		</div>
	</div>
	<!-- Content of each form -->
	
	<div id="AAROFAT-FORM">
		<div id="AAROFAT-FORM-CONTENT"></div>
	</div>
	<div id="CAROFAT-FORM">
		<div id="CAROFAT-FORM-CONTENT"></div>
	</div>
	<div id="VAROFAT-FORM">
		<div id="VAROFAT-FORM-CONTENT"></div>
	</div>
	<div id="MAROFAT-FORM">
		<div id="MAROFAT-FORM-CONTENT"></div>
	</div>
	<div id="CUPEDC-FORM">
		<div id="CUPEDC-FORM-CONTENT"></div>
	</div>
	
	<!-- Dailog Box for Alert Dailog processiong dialog -->
	<div id="GLOBAL-ERROR"></div>
	
	<div id="PROCESSING-DIALOG">
		<div id="PROCESS-DIALOG-CONTENT"></div>
	</div>
	
	<div id="NAVI-DIALOG">
		<div id="NAVI-DIALOG-CONTENT"></div>
	</div>
	
	<div id="ALERT-DIALOG">
		<div id="ALERT-DIALOG-RESULT"></div>
		<div id="ALERT-DIALOG-DETAIL" style="text-align:center; text-style:italic;font-weight:bold;"></div>
	</div>
	
	<div id="ADMIN-PROFILE">
		<div id="ADMIN-PROFILE-CONTENT">
			<p>
				<label for="USRID">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong> USER ID : </strong>
				</label>
				<span id="USRID" class="label_content">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</span>
				,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label for="ROLE">
					<strong>
						ROLE : 
					</strong>
				</label>
				<span id="ROLE" class="label_content">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</span>
			</p>
			<p>
				<label for="FIRSTNAME">
					<strong> FIRST NAME : </strong>
				</label>
				<span id="FIRSTNAME" class="label_content">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</span>
				,&nbsp;&nbsp;
				<label for="LASTNAME">
					<strong> LAST NAME : </strong>
				</label>
				<span id="LASTNAME" class="label_content">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</span>
			</p>
		</div>
	</div>
	
	<div id="ADMIN-CONTROL-DASH">
		<div id="ADMIN-CONTROL-CONTENT"></div>
	</div>
</body>
</html>