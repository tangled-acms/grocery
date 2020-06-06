/**
 * All functions that depend upon ProductRetailer Table to perform operations
 * 
 * Get all data,
 * Delete existing records,
 * Modify existing records.
 * 
 */

$(document).ready(function()
{
	var prod_ret_row_count = 0, column_count = 0;
	ajaxGetAllProductRetailer();
	
	/**
	 * Sends request to ProductRetailerController to retrieve all rows from the ProductRetailer Table.
	 * METHOD = GET
	 * 
	 * @param null
	 * @returns Array of objects of ProductRetailer type containing stock information
	 */
	
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
    			disable_radio_prod_ret();
    			
    		}
    	
    	});
    }
	
	/**
	 * Called when the user clicks 'Add new Row' button
	 * A new row with empty editable cells is added at the end of the table
	 * User enters valid data and clicks 'Done' button
	 */
	
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
		
		 $('#insert_button3').html('<button id="prodret_add_done" class="done button">DONE</button>');
	});
	
	/**
	 * Sends request to ProductRetailerController to add a new row to the ProductRetailer Table.
	 * METHOD = POST
	 * 
	 * @param Object of ProductRetailer type containing all information
	 * @returns Object that was successfully added to Table
	 */
	
	$("#prodret_add_done").live('click', function()
	{
		//alert("ADD clicked");
		
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
    			//ajaxGetAllProductRetailer();
    			
    			$('input[type!=radio]').each(function () 
    	        {
    	            var value = $(this).val();
    	            $(this).replaceWith(value);
    	        });
    			
    			ajaxProductGetAll();
    			alert("product get called");
    			
    	        enable_buttons_prod_ret();
    	        disable_radio_prod_ret();
    	        $('#insert_button3').html('');
    		}
    		
    	});
    	    	        
	});
	
	/**
	 * Called when the user clicks 'Modify Row' button.
	 * Radio buttons of the ProductRetailer Table get activated
	 * When user selects a specific row, cells of selected row change to editable form.
	 * User changes the cell data and clicks 'Done'.
	 */
	
	$('#modify_prod_ret').click(function()
    {
        disable_buttons_prod_ret();
        enable_radio_prod_ret();

        $('#Prod_Ret_table input').on('click', function()
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

                $('#insert_button3').html('<button id="prodret_modify_done" class="done button">DONE</button>');
            }
        });   
   });
	
	/**
	 * Sends request to ProductRetailerController to update row in Table.
	 * METHOD = PUT
	 * 
	 * @param Object of ProductRetailer type containing all information.
	 * @returns Object containing data that was successfully updated.
	 */
	
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
    			//ajaxGetAllProductRetailer();
    			
    			$("input[name='select_prod_ret']").unbind('click');
    			
    			$('input[type!=radio]').each(function () 
    	        {
    	            var value = $(this).val();
    	            $(this).replaceWith(value);
    	        });
    		}
    		
    	});

    	$('#insert_button3').html('');
    	
    	enable_buttons_prod_ret();
    	disable_radio_prod_ret();
	});
	
	/**
	 * Displays to text boxes for user to search the Table for details of specific Product or retailer.
	 */
	
	$("#find_retailer").click(function()
	{
		alert("button clicked");
		$("#insert_button3").html("<input type='text' required placeholder='Enter product ID' id='find_by_pid_inp' class='search_inp'/><br>" +
									"<input type='text' required placeholder='Enter retailer ID' id='find_by_rid_inp' class='search_inp'/><br>" +
									"<button id='find_by_pid' class='button done'>SEARCH</button>");
	});
	
	/**
	 * Sends request to ProductRetailerController to get details of requested ProductID or retailer ID.
	 * METHOD = GET
	 * 
	 * @param Product ID / Retailer ID as a part of the url
	 * @returns array of objects matching a requested Product ID
	 * 								or
	 * 			array of objects matching a requested Retailer ID 
	 */
	
	$("#find_by_pid").live('click', function()
	{
		var pid = $("#find_by_pid_inp").val();
		if(pid)
		{
			
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
	    			
	    			$("#find_by_rid_inp").html("<input type='text' required placeholder='Enter retailer ID' id='find_by_rid_inp' class='search_inp'/><br>");
	    			//ajaxGetAllProductRetailer();
	    		}
	    		
	    	});
		}
		
	});
	
	/**
	 * Function to display array of objects as a Table.
	 */
	
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
	
	/**
	 * Calls function to get all rows from ProductRetailer table.
	 */
	
	$("#get_all").click(function()
	{
		ajaxGetAllProductRetailer();
	});
	
	/**
	 * Function to perform operations
	 * Disable product retailer buttons,
	 * Enable product retailer buttons,
	 * Disable product retailer radio buttons,
	 * Enable product retailer radio buttons
	 */
	
	function disable_buttons_prod_ret()
    {
        $('#new_prod_ret').attr('disabled', true);
        $('#modify_prod_ret').attr('disabled', true);
        
        $('.button').removeClass('enable_button');
        $('.button').addClass('disable_button');
    }

    function enable_buttons_prod_ret()
    {
        $('#new_prod_ret').attr('disabled', false);
        $('#modify_prod_ret').attr('disabled', false);

        $('.button').removeClass('disable_button');
        $('.button').addClass('enable_button');
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