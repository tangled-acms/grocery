
/**
 * All functions that depend upon the Product Table
 * Get all data,
 * Add new Product, 
 * Modify existing Product,
 * delete existing Product
 */

$(document).ready(function()
{
    var d = document;
    var product_row_count = 0;
    
    //Display all available products when the web page loads
    ajaxProductGetAll();
    
    /**
     * Called when the user clicks 'Add Product' Button
     * Adds a new row with editable cells
     * User enters details of new Product
     * 
     * Upon completion, he clicks the 'DONE' button
     */
    
    $('#new_prod').click(function()
    {
        var i, cell, column_count = 0;

        product_row_count = $('#Product_table tr').length;
        //var column_count = $("#Product_table").find("tr:first td");

        $('#Product_table tr:first').children().each(function(){
            column_count++;
        });

        alert('new button clicked - ' + column_count + ' - ' + product_row_count);

        $('#Product_table').append('<tr id="row_' + (product_row_count) + '" class="row">' +
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

        $('#insert_button').html('<button id="product_add_done" class="done button">DONE</button>');
    });       

    /**
     * Replaces all editable cells with non editable ones when the 'DONE' button is clicked
     */
    
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
    
    /**
     * Sends a request to ProductController to add the request object to the Product Table
     * METHOD = POST
     * 
     *  @request Object of type Product containing all details of product
     *  @return Product object successfully added to Table
     */
    
    function ajaxProductPost()
    {
    	var ProductData = {
    			productId : $('#p_id_' + product_row_count + '_inp').val(),
    			name : $('#p_name' + product_row_count + '_inp').val(),
    			description : $('#p_descrp' + product_row_count + '_inp').val(),
    			quantity : $('#qty' + product_row_count + '_inp').val(),
    			mrp : $('#cost' + product_row_count + '_inp').val(),
    			promotion : $('#prom' + product_row_count + '_inp').val()
    	}
    	
    	alert(ProductData.productId + ' ' + ProductData.mrp);
    	
    	$.ajax({
    		type : "POST",
    		contentType : "application/json",
    		url : "product/save",
    		data : JSON.stringify(ProductData),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Data successfully added - " + result.productId);
    			ajaxProductGetAll();
    		}
    		
    	});
    }
    
    /**
	 * Sends a request to ProductController to retrieve all available products from the Product table
	 * METHOD = GET
	 * 
	 * @param null.
	 * @return List of objects from Product Table that have have quantity > 0.
	 */
    
    function ajaxProductGetAll()
    {

    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/product/getAllAvailable",
    		success : function(result)
    		{    			
    			display_prod_table(result);
    			disable_radio_product();
    			
    			$("#get_button").html('<button id="display_all_prod" class="button">Display unavailable Products</button>');
    		}
    	
    	});
    	
    }
    
    /**
	 * Sends a request to ProductController to retrieve all products from Product Table including those with quantity = 0
	 * Implemented when 'Display unavailable Products' is clicked
	 * METHOD = Get.
	 * 
	 * @param null.
	 * @return List of objects from Product Table including out of stock products.
	 */
    
    $("#display_all_prod").live('click', function()
    {
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/product/getAll",
    		success : function(result)
    		{
    			display_prod_table(result);
    			
    			disable_radio_product();
    			$("#get_button").html('<button id="display_prod" class="button">Display available Products</button>');
    		}
    	
    	});
    });
    
    /**
	 * Calls the ajaxProductGetAll function to display only available products 
	 * when the 'Display available Products' button is clicked.
	 *
	 */
    
    $("#display_prod").live('click', function()
    {
    	ajaxProductGetAll();
    });
    
    /**
     * On clicking 'Modify Product' the radio button corresponding to each Product get activated.
     * On selecting a specific product, the cells change to the editable state.
     * 
     *  Upon updating the product details, the user clicks the 'DONE' button to update in the database.
     */

    $('#modify_prod').click(function()
    {
        disable_buttons_product();
        enable_radio_product();

        $('#Product_table input').on('click', function()
        {
            var radioValue = $("input[name='select_product']:checked").attr('id');
            if(radioValue){
                alert("Selected row - " + radioValue);
                product_row_count = radioValue;
                
                $(this).parents('tr').find('td').each(function()
                {
                    var OriginalContent = $(this).text();
                    var elementId = $(this).attr('id');
                    
                    console.log(OriginalContent);
                    
                    if($.isNumeric(OriginalContent))
                        $(this).html('<input type= "number" required id="' + elementId + '_inp" value="' + OriginalContent + '">');
                    else
                        $(this).html('<input type= "text" required id="' + elementId + '_inp" value="' + OriginalContent + '">');
                });

                $('#insert_button').html('<button id="product_modify_done" class="done button">DONE</button>');
            }
        });

    });
    
    /**
     * Called when a Product's details need to be updated in the Product Table.
     * Called when 'DONE' is clicked 
     * 
     * METHOD = PUT
     * 
     * @param Object of Product type containing all product details.
     * @return updated object details.
     */
    
    $('#product_modify_done').live('click', function()
	{    	
    	
    	//Object with all product details
    	var ProductData = {
    			productId : $('#p_id_' + product_row_count + '_inp').val(),
    			name : $('#p_name' + product_row_count + '_inp').val(),
    			description : $('#p_descrp' + product_row_count + '_inp').val(),
    			quantity : $('#qty' + product_row_count + '_inp').val(),
    			mrp : $('#cost' + product_row_count + '_inp').val(),
    			promotion : $('#prom' + product_row_count + '_inp').val()
    	}
    	
    	alert(ProductData.productId + ' ' + ProductData.mrp);
    	console.log(ProductData);
    	
    	$.ajax({
    		type : "PUT",
    		contentType : "application/json",
    		url : "/product/update/" + ProductData.productId,
    		data : JSON.stringify(ProductData),
    		dataType : "json",
    		success : function(result)
    		{
    			alert("Data successfully updated - " + result.productId);
    			//ajaxProductGetAll();
    			$("input[name='select_product']").unbind('click');
    		}
    		
    	});
    	
    	$('input[type!=radio]').each(function () 
    	        {
    	            var value = $(this).val();
    	            $(this).replaceWith(value);
    	        });
    	//remove 'DONE' button and enable buttons for other operations
    	$('#insert_button').html('');
    	enable_buttons_product();
    	disable_radio_product();
	});
    
    /**
     * Called when the 'Delete Product' button is clicked.
     * On click, the radio buttons corresponding to each product get activated.
     * 
     * When a product is selected, the user receives a confirmation message.
     * Based on the user input, appropriate actions are taken
     */

    $('#delete_prod').click(function(){
        alert("Delete Product is clicked");
        
        disable_buttons_product();
		enable_radio_product();
		$('#Product_table').on('click', function()
		{
			var radioValue = $("input[name='select_product']:checked").parents('tr').attr('id');
			if(radioValue){
				var delete_confirm = confirm('Are you sure you want to delete this product?');
                if(delete_confirm == true)
                	{
                		ajaxDeleteProduct(radioValue);
                	}
                    
                else
                	enable_radio_product();
			}
		$('#insert_button').html('<button id="delete_done" class="done button">DONE</button>');	
		});
    });
    
    /**
     * Function called when user gives affirmative to delete product
     * METHOD = DELETE
     * 
     * @param Product ID is passed as a part of the url
     * @return Product ID on successful deletion of Product from database
     */
    
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
    			
    			$("input[name='select_product']").unbind('click');
    		}
    		
    	});
    }
    
    
    /**
	 * Functions to 
	 * Enable retailer buttons,
	 * Disable retailer buttons,
	 * Enable radio buttons of Retailer Table,
	 * Disable radio buttons of Retailer Table.
	 */

    function disable_buttons_product()
    {
        $('.button').removeClass('enable_button');
        $('.button').addClass('disable_button');
    }

    function enable_buttons_product()
    {
        $('.button').removeClass('disable_button');
        $('.button').addClass('enable_button');
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
            $(this).attr('checked', false);
        });

    }
    
    /**
     * Function to display array of products as a table 
     */
    
    function display_prod_table(row_list)
    {
    	product_row_count = 0;
		$("#Product_table").empty();
		$("#Product_table").append('<tr id="prod_tab_heading" class="row header">' + 
								'<th></th>' +
								'<th>Sl. No</th>' +
								'<th>Product ID</th>' +
								'<th>Product Name</th>' + 
								'<th>Description</th>' +
								'<th>Quantity Available</th>' +
								'<th>Cost per unit</th>' +
    							'<th>Promotion(%)</th>' +
								'</tr>');
		
		$(row_list).each(function(i, product)
				{
					product_row_count++;
			
					var Product_row = '<tr id="row_' + (product_row_count) + '" class="row">' +
                    '<th><input type="radio" class="select_product" name="select_product" id="' + (product_row_count) + '"/></th>' +
                    '<th class="sl_no">' + product_row_count + '</th>' +
                    '<td id="p_id_' + (product_row_count) + '">' + product.productId + '</td>' +
                    '<td id="p_name' + (product_row_count) + '">' + product.name + '</td>' +
                    '<td id="p_descrp' + (product_row_count) + '">' + product.description + '</td>' +
                    '<td id="qty' + (product_row_count) + '">' + product.quantity + '</td>' +
                    '<td id="cost' + (product_row_count) + '">' + product.mrp + '</td>' +
                    '<td id="prom' + (product_row_count) + '">' + product.promotion + '</td>' +
                    '</tr>';
					
					$("#Product_table").append(Product_row);
				});
    }
    

});