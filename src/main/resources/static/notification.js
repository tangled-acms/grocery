$(document).ready(function()
{
	alert('notification js file');
  	
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