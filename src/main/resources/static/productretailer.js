$(document).ready(function()
{
	var prod_ret_row_count = 0, column_count = 0;
	ajaxGetAllProductRetailer();
	
	function ajaxGetAllProductRetailer()
    {
    	alert("ProdRet Get all called!");
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/productretailer/getAll",
    		success : function(result)
    		{
    			//alert(result);
    			
    			$("#Prod_Ret_table").empty();
    			$("#Prod_Ret_table").append('<tr id="prodret_tab_heading">' + 
    									'<th></th>' +
    									'<th>Sl. No</th>' +
    									'<th>Product ID</th>' +
    									'<th>Retailer ID</th>' + 
    									'<th>Quantity</th>' +
    									'<th>Cost per unit</th>' +
    									'</tr>');
    			$(result).each(function(i, product)
    					{
    						prod_ret_row_count++;
    				
    						var Prod_ret_row = '<tr id="row_' + (prod_ret_row_count) + '">' +
                            '<th><input type="radio" class="select_product" name="select_product" id="' + (prod_ret_row_count) + '"/></th>' +
                            '<th class="sl_no">' + prod_ret_row_count + '</th>' +
                            '<td id="pr_pid_' + (prod_ret_row_count) + '">' + product.productId + '</td>' +
                            '<td id="pr_rid' + (prod_ret_row_count) + '">' + product.retailerId + '</td>' +
                            '<td id="pr_qty' + (prod_ret_row_count) + '">' + product.quantity + '</td>' +
                            '<td id="pr_cost' + (prod_ret_row_count) + '">' + product.cost + '</td>' +
                            '</tr>';
    						
    						$("#Prod_Ret_table").append(Prod_ret_row);
    					});
    		}
    	
    	});
    }
	
	$("#new_prod_ret").click(function()
	{
		prod_ret_row_count = $('#Prod_Ret_table tr').length;
		
		$('#Prod_Ret_table tr:first').children().each(function(){
            column_count++;
        });
		
		$('#Prod_Ret_table').append('<tr id="row_' + (prod_ret_row_count) + '">' +
                '<th><input type="radio" class="select_prod_ret" name="select_prodret" id="pr_' + (prod_ret_row_count) + '"/></th>' +
                '<th class="sl_no">' + prod_ret_row_count + '</th>' +
                '<td id="pr_pid_' + prod_ret_row_count + '"><input type= "text" required id="pr_pid_' + prod_ret_row_count + '_inp"/></td>' +
                '<td id="pr_rid' + prod_ret_row_count + '"><input type= "text" required id="pr_rid' + prod_ret_row_count + '_inp"/></td>' +
                '<td id="pr_qty' + prod_ret_row_count + '"><input type="number" id="pr_qty' + prod_ret_row_count + '_inp"/></td>' +
                '<td id="pr_cost' + prod_ret_row_count + '"><input type="number" id="pr_cost' + prod_ret_row_count + '_inp"/></td>' +
                '</tr>');
		
		disable_buttons_prod_ret();
		
		 $('#insert_button3').html('<button id="prodret_add_done">DONE</button>');
	});
	
	$("#prodret_add_done").live('click', function()
	{
		alert("ADD clicked");
		
		var ProductRetailerData = {
				productId : $('#pr_pid_' + prod_ret_row_count + '_inp').val(),
				retailerId : $('#pr_rid' + prod_ret_row_count + '_inp').val(),
    			quantity : $('#pr_qty' + prod_ret_row_count + '_inp').val(),
    			cost : $('#pr_cost' + prod_ret_row_count + '_inp').val()
    	}
    	
    	alert(ProductRetailerData.productId + ' ' + ProductRetailerData.cost);
		console.log(ProductRetailerData);
    	
    	$.ajax({
    		type : "POST",
    		contentType : "application/json",
    		url : "/productretailer/save",
    		data : JSON.stringify(ProductRetailerData),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Data successfully added to prodret - " + result.productId);
    		}
    		
    	});
    	
    	$('input[type!=radio]').each(function () 
    	        {
    	            //need to send data to database also
    	            var value = $(this).val();
    	            $(this).replaceWith(value);
    	        });

    	        enable_buttons_prod_ret();
    	        disable_radio_prod_ret();
    	        $('#insert_button3').html('');
	});
	
	$('#modify_prod_ret').click(function()
    {
        disable_buttons_prod_ret();
        enable_radio_prod_ret();

        $('#Prod_Ret_table input').on('change', function()
        {
        	alert("change detected");
            var radioValue = $("input[name='select_prodret']:checked").attr('id');
            alert("Selected row - " + radioValue);
            if(radioValue){
                alert("Selected row - " + radioValue);
                $(this).parents('tr').find('td').each(function()
                {
                    var OriginalContent = $(this).text();
                    //alert(jQuery.type(OriginalContent) + ' - ' + $.isNumeric(OriginalContent));
                    
                    if($.isNumeric(OriginalContent))
                        $(this).html("<input type='number' value='" + OriginalContent + "' required id='" + elementId + "_inp' />")
                    else
                        $(this).html("<input type='text' value='" + OriginalContent + "' required id='" + elementId + "_inp' />");
                });

                $('#insert_button3').html('<button id="prodret_modify_done">DONE</button>');
            }
        });   
   });
	
	$("#prodret_modify_done").live('click', function()
	{
		var ProdRetData = {
				productId : $('#pr_pid_' + prod_ret_row_count + '_inp').val(),
				retailerId : $('#pr_rid' + prod_ret_row_count + '_inp').val(),
    			quantity : $('#pr_qty' + prod_ret_row_count + '_inp').val(),
    			cost : $('#pr_cost' + prod_ret_row_count + '_inp').val()
    	}
		
		console.log(ProdRetData);
	});
	
	function disable_buttons_prod_ret()
    {
        $('#new_prod_ret').attr('disabled', true);
        $('#modify_prod_ret').attr('disabled', true);
    }

    function enable_buttons_prod_ret()
    {
        $('#new_prod_ret').attr('disabled', false);
        $('#modify_prod_ret').attr('disabled', false);
    }

    function disable_radio_prod_ret()
    {
        $('.select_prodret').each(function()
        {
            $(this).attr('disabled', true);
            $(this).attr('checked', false);
        });
    }

    function enable_radio_prod_ret()
    {
        $('.select_prodret').each(function()
        {
            $(this).attr('disabled', false);
        });
    }
});