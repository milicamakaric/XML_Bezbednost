/**
 *
	$.ajax({
		method:'GET',
		url: "/api/hoteli/all",
		success: function(lista){
			if(lista == null){
				console.log('Nema servise');
			}else{
				iscrtajHotele(lista);
			}
		}
	});
	
	 	$("#ispisiTabelu").append("<table class=\"table table-striped\" id=\"tabelaHotel\" ><tr><th> Name </th><th> City </th><th> Address </th><th>Grade</th><th></th><th></th></tr>");
		
 */

$(document).ready(function(){
 	$("#showSoftwares").empty();

	$.ajax({
		method:'GET',
		url: "/api/certificates/allDTO",
		success: function(list){
			if(list.size == 0){
				$("#showSoftwares").append("There is no softwares.");

				console.log('There is no sowtwares');
			}else{
				showSoftwares(list);
			}
		}
	});

});

function showSoftwares(list){
	console.log('prikaz softvera');
	$("#showSoftwares").append("<table class=\"table table-striped\" id=\"tableSoftwares\" ><tr><th> Name </th><th> Certified </th><th>Start date</th><th>End date</th><th>Revoked</th><th>Reason</th></tr>");
	$.each(list, function(index, pom) {
		if(pom.certified){
			var date1=pom.startDate.split('T')[0];
			var date2=pom.endDate.split('T')[0];
			
			if(pom.revoked){
				$("#tableSoftwares").append("<tr><td>"+pom.software+"</td><td>Yes</td><td>"+date1+"</td><td>"+date2+"</td><td>Yes</td><td>"+pom.reasonForRevokation+"</td></tr>");
					
			}else{
				$("#tableSoftwares").append("<tr><td>"+pom.software+"</td><td>Yes</td><td>"+date1+"</td><td>"+date2+"</td><td>No</td><td>-</td></tr>");
				
			}
					
		}else{
			$("#tableSoftwares").append("<tr><td>"+pom.software+"</td><td>No</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>");
			
		}
	});
	$("#showSoftwares").append("</table>");

	
}