/**
 * Functions to send notifications to user regarding product stock
 */

$(document).ready(function()
{
	alert('notification js file');
  	/**
  	* When the web page is refreshed at the beginning of the day, a stock inventory is made.
  	* All the products have low quantity are identified and displayed as a notification to the user. 
  	*/
	
  	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/product/getAllRestock",
		success : function(result)
		{
			//alert(result);
			
			var msg = "The following products are running low, please restock!\n";
			
			$(result).each(function(i, product)
			{
				msg += product.productId + " - " + product.name + " : quantity = " + product.quantity + " \n ";
			});
			
			alert(msg);
			
			var options = {
			  	      title: "Restock Alert",
			  	      options: {
			  	        body: msg,
			  	        icon: "icon.png",
			  	        lang: 'pt-BR',
			  	      }
			  	    };

			  	$("#easyNotify").easyNotify(options);
		}
  	});
});