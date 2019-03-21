$(document).ready(function()
{
	$.ajax({
        type: 'GET',
        url: '/api/softwares/getAll',
        success: function (softwares)
		{
        
			console.log('There are ' + softwares.length + ' softwares in memory.');
			for (let software of softwares) 
			{
				insertSoftware(software);
				
			}
			
		}
    });
});

function insertSoftware(software)
{
	if(software.certificated == false)
	{
		var li = $('<li><span>' + software.name +  ' - Dodaj sertifikat </span></li>');
	}
	else
	{
		var li = $('<li><span>' + software.name +  '</span></li>');
	}
	$('#lista').append(li);
}