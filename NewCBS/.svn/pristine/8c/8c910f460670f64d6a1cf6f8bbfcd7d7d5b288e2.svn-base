$(function() {
	// log in dailog
	$('#login_dialog').tabs();

	// button
	$('#logInButton').button();
});
// validation
function updateTips(t, tips, label) {
	tips.dialog({
		title:'Error Message',
		autoOpen:true,
		width:500,
		height:180,
		position:[270,50],
		modal:true,
		buttons:{
			'Exit':function(){
				$(this).dialog('close');
			}
		},
		close : function(){
			label.text(t).addClass("ui-state-error");
			label.css('margin','15px');
		}
	});
	tips.text(t).addClass("ui-state-error");
}
// check regular expression pattern
function checkRegexp(o, regexp, n, tips, label) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n, tips, label);
		return false;
	} else {
		return true;
	}
}
// check length
function checkLength(o, n, min, max, tips, label) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips("Length of " + n + " must be between " + min + " and " + max
				+ ".", tips, label);
		return false;
	} else {
		return true;
	}
}
