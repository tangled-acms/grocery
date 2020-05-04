$(document).ready(function()
{
    var d = document;
    var product_row_count = 0;
    var retailer_row_count = 0;
    
    ajaxProductGetAll();
    
	ajaxGetAllRetailer();
	
	disable_radio_retailer();
    
    //disable_radio_product();
    //disable_radio_retailer();
    //disable_radio_prod_ret();
    
    $('#new_prod').click(function()
    {
        var i, cell, column_count = 0;

        product_row_count = $('#Product_table tr').length;
        //var column_count = $("#Product_table").find("tr:first td");

        $('#Product_table tr:first').children().each(function(){
            column_count++;
        });

        alert('new button clicked - ' + column_count + ' - ' + product_row_count);

        $('#Product_table').append('<tr id="row_' + (product_row_count) + '">' +
                                    '<th><input type="radio" class="select_product" name="select_product" id="' + (product_row_count) + '"/></th>' +
                                    '<th class="sl_no">' + product_row_count + '</th>' +
                                    '<td id="p_id_' + product_row_count + '"><input type= "text" required id="p_id_' + product_row_count + '_inp"></td>' +
                                    '<td id="p_name' + product_row_count + '"><input type= "text" required id="p_name' + product_row_count + '_inp"></td>' +
                                    '<td id="p_descrp' + product_row_count + '"><input type= "text" required id="p_descrp' + product_row_count + '_inp"></td>' +
                                    '<td id="qty' + product_row_count + '"><input type="number" id="qty' + product_row_count + '_inp"></td>' +
                                    '<td id="cost' + product_row_count + '"><input type="number" id="cost' + product_row_count + '_inp"></td>' +
                                    '<td id="prom' + product_row_count + '"><input type="number" id="prom' + product_row_count + '_inp"></td>' +
                                    '</tr>');
        
        disable_buttons_product();

        $('#insert_button').html('<button id="product_add_done">DONE</button>');
    });       

    $('#product_add_done').live('click', function()
    {
        //alert('Done clicked');
    	
    	ajaxProductPost();

        $('input[type!=radio]').each(function () 
        {
            //need to send data to database also
            var value = $(this).val();
            $(this).replaceWith(value);
        });

        enable_buttons_product();
        disable_radio_product();
        $('#insert_button').html('');
    });
    
    function ajaxProductPost()
    {
    	var ProductData = {
    			productId : $('#p_id_' + product_row_count).val(),
    			name : $('#p_name' + product_row_count).val(),
    			description : $('#p_descrp' + product_row_count).val(),
    			quantity : $('#qty' + product_row_count).val(),
    			MRP : $('#cost' + product_row_count).val(),
    			promotion : $('#prom' + product_row_count).val()
    	}
    	
    	alert(ProductData.productId + ' ' + ProductData.MRP);
    	
    	$.ajax({
    		type : "POST",
    		contentType : "application/json",
    		url : "product/save",
    		data : JSON.stringify(ProductData),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Data successfully added - " + result.productId);
    		}
    		
    	});
    }
    
    function ajaxProductGetAll()
    {
    	alert("Get all called!");
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/product/getAll",
    		success : function(result)
    		{
    			//alert(result);
    			
    			$("#Product_table").empty();
    			$("#Product_table").append('<tr id="prod_tab_heading">' + 
    									'<th></th>' +
    									'<th>Sl. No</th>' +
    									'<th>Product ID</th>' +
    									'<th>Product Name</th>' + 
    									'<th>Description</th>' +
    									'<th>Quantity Available</th>' +
    									'<th>Cost per unit</th>' +
            							'<th>Promotion(%)</th>' +
    									'</tr>');
    			$(result).each(function(i, product)
    					{
    						product_row_count++;
    				
    						var Product_row = '<tr id="row_' + (product_row_count) + '">' +
                            '<th><input type="radio" class="select_product" name="select_product" id="' + (product_row_count) + '"/></th>' +
                            '<th class="sl_no">' + product_row_count + '</th>' +
                            '<td id="p_id_' + (product_row_count) + '">' + product.productId + '</td>' +
                            '<td id="p_name' + (product_row_count) + '">' + product.name + '</td>' +
                            '<td id="p_descrp' + (product_row_count) + '">' + product.description + '</td>' +
                            '<td id="qty' + (product_row_count) + '">' + product.quantity + '</td>' +
                            '<td id="cost' + (product_row_count) + '">' + product.MRP + '</td>' +
                            '<td id="prom' + (product_row_count) + '">' + product.promotion + '</td>' +
                            '</tr>';
    						
    						$("#Product_table").append(Product_row);
    					});
    		}
    	
    		/*error : function(e)
    		{
    			alert("Error!!");
    		}*/
    	});
    }

    $('#modify_prod').click(function()
    {
        disable_buttons_product();
        enable_radio_product();

        $('#Product_table input').on('change', function()
        {
            var radioValue = $("input[name='select_product']:checked").attr('id');
            if(radioValue){
                alert("Selected row - " + radioValue);
                product_row_count = radioValue;
                
                $(this).parents('tr').find('td').each(function()
                {
                    var OriginalContent = $(this).text();
                    var elementId = $(this).attr('id');
                    
                    alert(elementId);
                    
                    if($.isNumeric(OriginalContent))
                        $(this).html('<input type= "number" required id="' + elementId + '_inp" value="' + OriginalContent + '">');
                    else
                        $(this).html('<input type= "text" required id="' + elementId + '_inp" value="' + OriginalContent + '">');
                });

                $('#insert_button').html('<button id="product_modify_done">DONE</button>');
            }
        });

    });
    
    
    //ajax call to update existing product row
    $('#product_modify_done').live('click', function()
	{    	
    	//alert(product_row_count);
    	
    	var ProductData = {
    			productId : $('#p_id_' + product_row_count + '_inp').val(),
    			name : $('#p_name' + product_row_count + '_inp').val(),
    			description : $('#p_descrp' + product_row_count + '_inp').val(),
    			quantity : $('#qty' + product_row_count + '_inp').val(),
    			MRP : $('#cost' + product_row_count + '_inp').val(),
    			promotion : $('#prom' + product_row_count + '_inp').val()
    	}
    	
    	alert(ProductData.productId + ' ' + ProductData.MRP);
    	
    	$.ajax({
    		type : "PUT",
    		contentType : "application/json",
    		url : "/product/update/" + ProductData.productId,
    		data : JSON.stringify(ProductData),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Data successfully updated - " + result.productId);
    		}
    		
    	});
    	
    	$('input[type!=radio]').each(function () 
    	        {
    	            var value = $(this).val();
    	            $(this).replaceWith(value);
    	        });
    	$('#insert_button').html('');
    	enable_buttons_product();
    	disable_radio_product();
	});

    $('#delete_prod').click(function(){
        alert("Delete Product is clicked");
        
        disable_buttons_product();
		enable_radio_product();
		$('#Product_table').on('change', function()
		{
			var radioValue = $("input[name='select_product']:checked").parents('tr').attr('id');
			if(radioValue){
				var delete_confirm = confirm('Are you sure you want to delete this product?');
                if(delete_confirm == true)
                	{
                		ajaxDeleteProduct(radioValue);
                	}
                    
                else
                    enable_product_radio();
			}
		$('#insert_button').html('<button id="delete_done">DONE</button>');	
		});
    });
    
    function ajaxDeleteProduct(row_id)
    {
    	var length = row_id.length;
		var row_no = row_id.substring(length-1, length);
		alert(row_no);
    	var Id = $('#p_id_' + row_no).text();
    	alert(Id);
    	
    	$.ajax({
    		
    		type : "DELETE",
    		contentType : "application/json",
    		url : "/product/delete/" + Id,
    		success : function(result)
    		{
    			alert("Data successfully updated - " + result);
    			$('#' + row_id).remove();
    		}
    		
    	});
    }
    
	$('#delete_done').live('click', function(){
        enable_buttons_product();
        enable_buttons_retailer();
        disable_radio_product();
        disable_radio_retailer();
        $('#insert_button').html('');
        $('#insert_button2').html('');
    });
	
	
	
	function ajaxGetAllRetailer()
	{
		//alert("Retailer Get all called!");
		
		$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/retailer/getAll",
    		dataType : "json",
    		success : function(result)
    		{
    			//alert(result);
    			console.log(result);
    			
    			$("#Retailer_table").empty();
    			$("#Retailer_table").append('<tr id="retailer_tab_heading">' + 
    		            					'<th></th>' +
    		            					'<th>Sl. No</th>' +
    		            					'<th>Retailer ID</th>' +
    		            					'<th>Retailer Name</th>' + 
    		            					'<th>Address 1</th>' +
    		            					'<th>Address 2</th>' +
    		            					'<th>Address 3</th>' +
    		            					'<th>Contact No 1</th>' +
    		            					'<th>Contact No 2</th>' +
    		            					'<th>Contact No 3</th>' +
    		        						'</tr>');
    			$(result).each(function(i, retailer)
    					{
    						retailer_row_count++;
    				
    						var Retailer_row = '<tr id="ret_row_' + (retailer_row_count) + '">' +
                            '<th><input type="radio" class="select_retailer" name="select_retailer" id="' + (retailer_row_count) + '"/></th>' +
                            '<th class="sl_no">' + retailer_row_count + '</th>' +
                            '<td id="r_id_' + (retailer_row_count) + '">' + retailer.retailerId + '</td>' +
                            '<td id="r_name' + (retailer_row_count) + '">' + retailer.name + '</td>' +
                            '<td id="r_addr1' + (retailer_row_count) + '">' + retailer.address1 + '</td>' +
                            '<td id="r_addr2' + (retailer_row_count) + '">' + retailer.address2 + '</td>' +
                            '<td id="r_addr3' + (retailer_row_count) + '">' + retailer.address3 + '</td>' +
                            '<td id="r_cont1' + (retailer_row_count) + '">' + retailer.contact1 + '</td>' +
                            '<td id="r_cont2' + (retailer_row_count) + '">' + retailer.contact2 + '</td>' +
                            '<td id="r_cont3' + (retailer_row_count) + '">' + retailer.contact3 + '</td>' +
                            '</tr>';
    						
    						$("#Retailer_table").append(Retailer_row);
    					});
    		}
    	});

	}
	
	
	function disable_radio_retailer()
    {
        $('.select_retailer').each(function()
        {
            $(this).attr('disabled', true);
            $(this).attr('checked', false);
        });
    }
	

    $('#new_retailer').click(function()
    {
        var i, cell, column_count = 0;

        var row_count = $('#Retailer_table tr').length;
        //var column_count = $("#Product_table").find("tr:first td");

        $('#Retailer_table tr:first').children().each(function(){
            column_count++;
        });

        alert('new button clicked - ' + column_count + ' - ' + row_count);

        $('#Retailer_table').append('<tr id="row_' + (row_count-1) + '">' +
                                    '<td><input type="radio" class="select_retailer" name="select_retailer" id="' + (row_count-1) + '"/></td>' +
                                    '<td class="sl_no">' + row_count + '</th>' +
                                    '<td><input type= "text" required class="r_id"></td>' +
                                    '<td><input type= "text" required class="r_name"></td>' +
                                    '<td><input type= "number" required class="r_cont_1"></td>' +
                                    '<td><input type= "number" required class="r_cont_2"></td>' +
                                    '<td><input type= "number" required class="r_cont_3"></td>' +
                                    '<td><input type= "text" required class="r_addr_1"></td>' +
                                    '<td><input type= "text" required class="r_addr_1"></td>' +
                                    '<td><input type= "text" required class="r_addr_1"></td>' +
                                    '</tr>');
        
        disable_buttons_retailer();

        $('#insert_button2').html('<button id="retailer_done">DONE</button>');
    });

    $('#retailer_done').live('click', function()
    {
        //alert('Done clicked');

        $('input[type!=radio]').each(function () 
        {
            //need to send data to database also
            var value = $(this).val();
            $(this).replaceWith(value);
        });

        enable_buttons_retailer();
        disable_radio_retailer();
        $('#insert_button2').html('');
    });

    $('#modify_retailer').click(function()
    {
        disable_buttons_retailer();
        enable_radio_retailer();

        $('#Retailer_table input').on('change', function()
        {
            var radioValue = $("input[name='select_retailer']:checked").attr('id');
            if(radioValue){
                alert("Selected row - " + radioValue);
                $(this).parents('tr').find('td').each(function()
                {
                    var OriginalContent = $(this).text();
                    //alert(jQuery.type(OriginalContent) + ' - ' + $.isNumeric(OriginalContent));

                    if($.isNumeric(OriginalContent))
                        $(this).html("<input type='number' value='" + OriginalContent + "' />")
                    else
                        $(this).html("<input type='text' value='" + OriginalContent + "' />");
                });

                $('#insert_button2').html('<button id="retailer_done">DONE</button>');
            }
        });

    });

    $('#delete_retailer').click(function(){
        alert("Delete Retailer is clicked");
        
        disable_buttons_retailer();
		enable_radio_retailer();
		$('#Retailer_table').on('change', function()
		{
			var radioValue = $("input[name='select_retailer']:checked").parents('tr').attr('id');
			if(radioValue){
				var delete_confirm = confirm("Are you sure you want to delete this retailer's details? " + radioValue);
                if(delete_confirm == true)
                    $('#' + radioValue).remove();
                else
                    disable_radio_retailer();
			}
		$('#insert_button2').html('<button id="delete_done">DONE</button>');	
		});
	});

    $('#new_prod_ret').click(function()
    {
        var column_count = 0;
        var row_count = $('#Prod_Ret_table tr').length;

        $('#Prod_Ret_table tr:first').children().each(function(){
            column_count++;
        });

        alert('new button clicked - ' + column_count + ' - ' + row_count);

        $('#Prod_Ret_table').append('<tr id="row_' + (row_count-1) + '">' +
                                    '<th><input type="radio" class="select_prod_ret" name="select_prod_ret" id="' + (row_count-1) + '"/></th>' +
                                    '<td class="sl_no">' + row_count + '</th>' +
                                    '<td><input type= "text" required class="p_id"></td>' +
                                    '<td><input type= "text" required class="r_id"></td>' +
                                    '<td><input type= "datetime-local" required class="time_s"></td>' +
                                    '<td><input type= "number" required class="qty"></td>' +
                                    '<td><input type= "number" required class="cost"></td>' +
                                    '</tr>');
        
        disable_buttons_prod_ret();

        $('#insert_button3').html('<button id="prod_ret_done">DONE</button>');
    });

    $('#prod_ret_done').live('click', function()
    {
        alert('Done clicked');

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
            var radioValue = $("input[name='select_prod_ret']:checked").attr('id');
            if(radioValue){
                alert("Selected row - " + radioValue);
                $(this).parents('tr').find('td').each(function()
                {
                    var OriginalContent = $(this).text();
                    alert(jQuery.type(OriginalContent) + ' - ' + $.isNumeric(OriginalContent));
                    
                    if($.isNumeric(OriginalContent))
                        $(this).html("<input type='number' value='" + OriginalContent + "' />")
                    else
                        $(this).html("<input type='text' value='" + OriginalContent + "' />");
                });

                $('#insert_button3').html('<button id="prod_ret_done">DONE</button>');
            }
        });   
    });

    function disable_buttons_product()
    {
        $('#new_prod').attr('disabled', true);
        $('#delete_prod').attr('disabled', true);
        $('#modify_prod').attr('disabled', true);
    }

    function enable_buttons_product()
    {
        $('#new_prod').attr('disabled', false);
        $('#delete_prod').attr('disabled', false);
        $('#modify_prod').attr('disabled', false);
    }

    function disable_radio_product()
    {
        $('.select_product').each(function()
        {
            $(this).attr('disabled', true);
            $(this).attr('checked', false);
        });
    }

    function enable_radio_product()
    {
        $('.select_product').each(function()
        {
            $(this).attr('disabled', false);
        });
    }

    

    

});