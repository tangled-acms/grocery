$(document).ready(function()
{
	var prod_ret_row_count = 0, column_count = 0;
	ajaxGetAllProductRetailer();
	
	function ajaxGetAllProductRetailer()
    {
    	//alert("ProdRet Get all called!");
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/productretailer/getAll",
    		success : function(result)
    		{
    			//alert(result);
    			display_prod_ret_table(result);
    			
    		}
    	
    	});
    }
	
	$("#new_prod_ret.button").click(function()
	{
		prod_ret_row_count = $('#Prod_Ret_table tr').length;
		
		$('#Prod_Ret_table tr:first').children().each(function(){
            column_count++;
        });
		
		$('#Prod_Ret_table').append('<tr id="row_' + (prod_ret_row_count) + '" class="row">' +
                '<th><input type="radio" class="select_prod_ret" name="select_prod_ret" id="pr_' + (prod_ret_row_count) + '"/></th>' +
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
    			ajaxGetAllProductRetailer();
    		}
    		
    	});
    	
    	        enable_buttons_prod_ret();
    	        disable_radio_prod_ret();
    	        $('#insert_button3').html('');
	});
	
	
	$('#modify_prod_ret.button').click(function()
    {
        disable_buttons_prod_ret();
        enable_radio_prod_ret();

        $('#Prod_Ret_table input').on('change', function()
        {
        	alert("change detected");
            var radioValue = $("input[name='select_prod_ret']:checked").attr('id');
            alert("Selected row - " + radioValue);
            prod_ret_row_count = radioValue;
            if(radioValue){
                //alert("Selected row - " + radioValue);
                $(this).parents('tr').find('td').each(function()
                {
                    var OriginalContent = $(this).text();
                    var elementId = $(this).attr('id');
                    //alert(jQuery.type(OriginalContent) + ' - ' + $.isNumeric(OriginalContent));
                    console.log(elementId + ' - ' + OriginalContent);
                    
                    if($.isNumeric(OriginalContent))
                        $(this).html("<input type='number' value='" + OriginalContent + "' required id='" + elementId + "_inp' />");
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
		
		$.ajax({
    		type : "PUT",
    		contentType : "application/json",
    		url : "/productretailer/update",
    		data : JSON.stringify(ProdRetData),
    		dataType : "json",
    		success : function(retailer_result)
    		{
    			alert("Data successfully updated - " + retailer_result.retailerId);
    			ajaxGetAllProductRetailer();
    		}
    		
    	});

    	$('#insert_button3').html('');
    	
    	enable_buttons_prod_ret();
    	disable_radio_prod_ret();
	});
	
	$("#find_retailer.button").click(function()
	{
		alert("button clicked");
		$("#insert_button3").html("<input type='text' required placeholder='Enter product ID' id='find_by_pid_inp'/><br>" +
									"<input type='text' required placeholder='Enter retailer ID' id='find_by_rid_inp'/><br>" +
									"<button id='find_by_pid'>SEARCH</button>");
	});
	
	$("#find_by_pid").live('click', function()
	{
		var pid = $("#find_by_pid_inp").val();
		if(pid)
		{
			/*var ProdRetData = {
					productId : pid
			}*/
			
			$.ajax({
	    		type : "GET",
	    		contentType : "application/json",
	    		url : "/productretailer/getByProductId/" + pid,
	    		success : function(retailer_result)
	    		{
	    			console.log("Data successfully read - " + retailer_result);
	    			display_prod_ret_table(retailer_result);
	    			$("#find_by_pid_inp").html("<input type='text' required placeholder='Enter product ID' id='find_by_pid_inp'/><br>");
	    		}
	    		
	    	});
		}
			
		var rid = $("#find_by_rid_inp").val();
		if(rid)
		{
			
			console.log(rid);
			
			$.ajax({
	    		type : "GET",
	    		contentType : "application/json",
	    		url : "/productretailer/getByRetailerId/" + rid,
	    		success : function(retailer_result)
	    		{
	    			console.log("Data successfully read - " + retailer_result);
	    			display_prod_ret_table(retailer_result);
	    			
	    			$("#find_by_rid_inp").html("<input type='text' required placeholder='Enter retailer ID' id='find_by_rid_inp'/><br>");
	    			//ajaxGetAllProductRetailer();
	    		}
	    		
	    	});
		}
		
	});
	
	
	function display_prod_ret_table(result)
	{
		prod_ret_row_count = 0;
		$("#Prod_Ret_table").empty();
		$("#Prod_Ret_table").append('<tr id="prodret_tab_heading" class="row header">' + 
								'<th></th>' +
								'<th>Sl. No</th>' +
								'<th>Product ID</th>' +
								'<th>Retailer ID</th>' + 
								'<th>Quantity</th>' +
								'<th>Cost per unit</th>' +
								'</tr>');
		$(result).each(function(i, prod_ret)
				{
					prod_ret_row_count++;
			
					var Prod_ret_row = '<tr id="row_' + (prod_ret_row_count) + '" class="row">' +
                    '<th><input type="radio" class="select_prod_ret" name="select_prod_ret" id="' + (prod_ret_row_count) + '"/></th>' +
                    '<th class="sl_no">' + prod_ret_row_count + '</th>' +
                    '<td id="pr_pid_' + (prod_ret_row_count) + '">' + prod_ret.productId + '</td>' +
                    '<td id="pr_rid' + (prod_ret_row_count) + '">' + prod_ret.retailerId + '</td>' +
                    '<td id="pr_qty' + (prod_ret_row_count) + '">' + prod_ret.quantity + '</td>' +
                    '<td id="pr_cost' + (prod_ret_row_count) + '">' + prod_ret.cost + '</td>' +
                    '</tr>';
					
					$("#Prod_Ret_table").append(Prod_ret_row);
				});
	}
	
	$("#get_all.button").click(function()
	{
		ajaxGetAllProductRetailer();
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
        $('.select_prod_ret').each(function()
        {
            $(this).attr('disabled', true);
            $(this).attr('checked', false);
        });
    }

    function enable_radio_prod_ret()
    {
        $('.select_prod_ret').each(function()
        {
            $(this).attr('disabled', false);
            $(this).attr('checked', false);
        });
    }
});