$(document).ready(function()
{
    var d = document;
    var product_row_count = 0;
    var retailer_row_count = 0;
    
    ajaxProductGetAll();
    
    
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
        disable_radio_product();
        $('#insert_button').html('');
        $('#insert_button2').html('');
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

    
    function disable_buttons_retailer()
    {
        $('#new_retailer').attr('disabled', true);
        $('#delete_retailer').attr('disabled', true);
        $('#modify_retailer').attr('disabled', true);
    }

    function enable_buttons_retailer()
    {
        $('#new_retailer').attr('disabled', false);
        $('#delete_retailer').attr('disabled', false);
        $('#modify_retailer').attr('disabled', false);
    }

    function disable_radio_retailer()
    {
        $('.select_retailer').each(function()
        {
            $(this).attr('disabled', true);
            $(this).attr('checked', false);
        });
    }

    function enable_radio_retailer()
    {
        $('.select_retailer').each(function()
        {
            $(this).attr('disabled', false);
        });
    }
    

});