$(document).ready(function(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 

	today = yyyy+'-'+mm+'-'+dd;
	
	$('input[id=startCertificate]').attr("min", today);
	$('input[id=endCertificate]').attr("min", today);
	
	$('input[id=startCertificate]').on('change', function(){
		
		var datum = $('input[id=startCertificate]').val();
		$('input[id=endCertificate]').attr("min", datum);
		
		var date_start = new Date(datum);
		var date_end = new Date($('input[id=endCertificate]').val());
		
		if(date_end < date_start)
		{
			$('input[id=endCertificate]').val(datum);
		}
	});
	
});
