$(document).ready(function()
{
	var bill_row_count = 0;
	var total = 0;
	
    $("#create_bill").click(function()
    {
        $("#bill_details").html("BILL ID : <input type='number' id='bill_id' class='bill_input'/>" +
                                "<button id='add_bill' class='button bill'>OK</button><br>" +
                                "PRODUCT ID : <input type='text' id='bill_p_id' class='bill_input'/><br>" +
                                "QUANTITY : <input type='number' id='bill_qty' class='bill_input'/>" +
                                "<button id='add_item' class='button bill'>ADD ITEM</button><br>" +
                                "<button id='make_bill' class='button done'>generate bill</button><br><br>");
    });
    
    $("#add_item").live('click', function()
    {
    	alert("new item has to be added");
    	
    	var billid = $("#bill_id").val();
    	var prodid = $("#bill_p_id").val();
    	var qty = $("#bill_qty").val();
    	
    	var BillDetails = {
    			billId : billid,
    			productId : prodid,
    			quantity : qty,
    			cost : 0
    	}
    	
    	console.log(BillDetails);
    	
    	$.ajax({
    		type : "POST",
    		contentType : "application/json",
    		url : "/billingdetails/save",
    		data : JSON.stringify(BillDetails),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Data successfully added - " + result);
    		}
    		
    	});
    });
    
    $("#add_bill").live('click', function()
    {
    	var billid = $("#bill_id").val();
    	
    	var Bill = {
    			billId : billid,
    			subTotal : 0
    	}
    	
    	console.log(Bill);
    	
    	$.ajax({
    		type : "POST",
    		contentType : "application/json",
    		url : "/billing/save",
    		data : JSON.stringify(Bill),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Data successfully added - " + result);
    		}
    		
    	});
    	
    });
    
    $("#make_bill").live('click', function()
    {
    	$("#Bill_table").empty();
    	$("#Bill_table").append('<tr id="prod_ret_tab_heading" class="row header bill">' +
            '<th></th>' +
            '<th>Sl. No</th>' +
            '<th>Product ID</th>' +
            '<th>Quantity</th>' +
            '<th>Cost</th>');
    	
    	var billid = $("#bill_id").val();
    	
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/billingdetails/getById/" + billid,
    		success : function(result)
    		{
    			bill_row_count++;
    			
    			console.log(result);
    			
    			$(result).each(function(i, bill_item)
    			{
    				$("#Bill_table").append('<tr id="row_"' + bill_row_count + ' class="row bill">' +
        		            '<th><input type="radio" class="select_bill_item" name="select_bill_item" id="' + (bill_row_count) + '"/></th>' +
        		            '<td>' + bill_row_count + '</td>' +
        		            '<td id="b_pid_' + bill_row_count + '">' + bill_item.productId + '</td>' +
        		            '<td id="b_qty_' + bill_row_count + '">' + bill_item.quantity + '</td>' +
        		            '<td id="b_cost_' + bill_row_count + '">' + bill_item.cost + '</td>');
    				
    				total = total + bill_item.cost;
    				
    				bill_row_count++;
    			});
    			
    			$("#bill_final_options").html('<button id="delete_bill_item" class="button bill">DELETE ITEM</button>' + 
    											'<p>Subtotal = ' + total + '</p>' +
    											'<p>PAYMENT OPTIONS : </p>' + 
    											'<input type="radio" id="cash" name="pay_method"/>CASH' +
    											'<input type="radio" id="card" name="pay_method"/>CARD<br>' +
    											'<button id="make_payment" class="button bill">MAKE PAYMENT</button>');
    		}
    	});
    		
    });
    
    $("#make_payment").live('click', function()
    {
    	alert("payment button clicked");
    	
    	var pay_mode = $("input[name='pay_method']:checked").attr('id');
    	var billid = $("#bill_id").val();
    	
    	var Bill = {
    			billId : billid,
    			subTotal : total,
    			paymentMode : pay_mode
    	}
    	
    	console.log(Bill);
    	
    	$.ajax({
    		type : "PUT",
    		contentType : "application/json",
    		url : "/billing/update/" + billid,
    		data : JSON.stringify(Bill),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Payment successful - " + result);
    		}
    		
    	});
    	
    });
    
    $("#delete_bill_item").live('click', function()
    {
    	$('#Bill_table').on('change', function()
    			{
    				var radioValue = $("input[name='select_bill_item']:checked").attr('id');
    				if(radioValue){
    					var delete_confirm = confirm('Are you sure you want to delete this item from bill?' + radioValue);
    	                if(delete_confirm == true)
    	                	{
    	                		console.log("deleting");
    	        
    	                    	var Id = $('#b_pid_' + radioValue).text();
    	                    	alert(Id);
    	                    	
    	                    	var BillDetails = {
    	                    			billId : $("#bill_id").val(),
    	                    			productId : Id
    	                    	}
    	                    	
    	                    	console.log(BillDetails);
    	                    	
    	                    	$.ajax({
    	                    		
    	                    		type : "DELETE",
    	                    		contentType : "application/json",
    	                    		url : "/billingdetails/delete",
    	                    		data: JSON.stringify(BillDetails),
    	                    		dataType : "json",
    	                    		success : function(result)
    	                    		{
    	                    			alert("Data deleted from bill - " + result);
    	                    			$('#row_' + radioValue).remove();
    	                    		}
    	                    		
    	                    	});
    	                	}
    				}
    			});
    });
});