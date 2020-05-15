/**
 * 
 */

$(document).ready(function()
{
	if (Notification.permission !== "granted")   
    {  
        Notification.requestPermission();  
        alert('permission granted');
        
    }  
	console.log(Notification.permission);
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/product/getAllRestock",
		success : function(result)
		{
			console.log(result);
			
			//var msg = "You are running low on - " + result.productId + " - " + result.name + "\nQuantity = " + result.quantity;
			
			var options = {
				      title: "ProductRestock",
				      options: {
				        body: "You are running low on - " + result.productId + " - " + result.name + "\nQuantity = " + result.quantity,
				        lang: 'pt-BR',
				      }
				    };

				$("#easyNotify").easyNotify();
		}
	});
});