// AAROFAT java script action
$(function(){
		$('#AAROFAT').click(function(){
			$('#AAROFAT-FORM').dialog('open');	
		});
		
		$('#AAROFAT-FORM').dialog({
			title   : 'AAROFAT',
			autoOpen:false,
			height: 420,
            width : 800,
            position:[270,50],
            buttons:{
            	'Exit':function() {
                    $(this).dialog('close');
                }
            },
            open:function(){
            	$.get('aarofat/reconcile/aarofat.jsp',function(data){
            		$('#AAROFAT-FORM-CONTENT').html(data);
            	});
            },
            close:function(){
            	$('#AAROFAT-FORM-CONTENT').empty();
            }
		});
});