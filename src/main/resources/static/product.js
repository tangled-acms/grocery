$(document).ready(function()
{
    var d = document;
    var product_row_count = 0;
    
    ajaxProductGetAll();
    /*var options = {
    	      title: "Notification to restock!",
    	      options: {
    	        body: "The following products are running low, please restock!",
    	        icon: "icon.png",
    	        lang: 'pt-BR',
    	      }
    	    };

    	$("#easyNotify").easyNotify(options);*/
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
    
    function ajaxProductGetAll()
    {
    	//alert("Get all called!");
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/product/getAllAvailable",
    		success : function(result)
    		{
    			//alert(result);
    			
    			display_prod_table(result);
    			
    			disable_radio_product();
    			
    			$("#get_button").html('<button id="display_all_prod" class="button">Display unavailable Products</button>');
    		}
    	
    	});
    	
    }
    
    
    $("#display_all_prod").live('click', function()
    {
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/product/getAll",
    		success : function(result)
    		{
    			//alert(result);
    			
    			display_prod_table(result);
    			
    			disable_radio_product();
    			$("#get_button").html('<button id="display_prod" class="button">Display available Products</button>');
    		}
    	
    	});
    });
    
    $("#display_prod").live('click', function()
    {
    	ajaxProductGetAll();
    });

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
    
    
    //ajax call to update existing product row
    $('#product_modify_done').live('click', function()
	{    	
    	//alert(product_row_count);
    	
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
    	$('#insert_button').html('');
    	enable_buttons_product();
    	disable_radio_product();
	});

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
    
    $("#display_graph").click(function()
    {
    	
    	var product_select = "<label>Select Product ID : </label>" + 
    						"<select id='product_dropdown'>";
    	
    	$.ajax({
    		
    		type : "GET",
    		contentType : "application/json",
    		url : "/analytics/getAll",
    		success : function(result)
    		{
    			console.log(result);
    			
    			var data = display_graph(result);
    			
    				
    				console.log('Data for chart = ');
    				console.log(data.chartData);
    				
    				var chart = new CanvasJS.Chart("chartContainer", {
    					title:{
        			        text: "Products Sold"              
        			      },
        			      data: data.chartData
    				});
    				
    				chart.render();
    				
    				create_dropdown(data.dropdownData);    				
    				
    				$("#product_dropdown").change(function(){
    		            var selected_pid = $(this).children("option:selected").val();
    		            alert("You have selected - " + selected_pid);
    		            
    		            display_pid_graph(selected_pid);
    		        });
    			
    		}
    	});
    
	
    });
    
    function display_pid_graph(selected_pid)
    {
    	//alert('display pid function called');
    	
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/analytics/getById/" + selected_pid,
    		success : function(result)
    		{
    			var data = display_graph(result);
    			
    			console.log('Data for chart = ');
				console.log(data.chartData);
				
				var chart = new CanvasJS.Chart("chartContainer", {
					title:{
    			        text: "Sales for product - " + selected_pid              
    			      },
    			      data: data.chartData
				});
				
				chart.render();
    		}
    		
    	});
    	
    }
    
    function display_graph(product_Statistics)
    {
    	
    	alert('inside display graph');
    	
    	console.log(product_Statistics);
    	
    	var dataPts = [];
    	
		$(product_Statistics).each(function(i, product) 
		{
			var datenew = new Date((product.timeStamp)*1000);
			console.log(datenew);
			dataPts.push({label : datenew.toDateString(), y : product.sold, productId : product.productId});
		});
		
		console.log('Altered result = ');
		console.log( dataPts);
		
		var grouped = _.groupBy(dataPts, function(product) {
			const makeValue = product.productId;
		    delete product.productId;
		    return makeValue;
			});

			console.log('grouped = ');
			console.log(grouped);
			
			var productIdList = Object.keys(grouped);
			
			//create_dropdown(productIdList);
			
			console.log('Key list = ' + productIdList);
			
			var data = [];
			
			$(productIdList).each(function(i, product)
			{
				data.push({type : "line", name : product, showInLegend: true, dataPoints : grouped[product]});
				
			});
			
			var respose_data = {chartData : data, dropdownData : productIdList};
			
			return respose_data;			
    }
    
    function create_dropdown(productIdList)
    {
    	var product_select = "<label>Select Product ID : </label>" + 
		"<select id='product_dropdown'>";
    	
    	$(productIdList).each(function(i, product)
		{
			product_select += "<option value='" + product + "'>" + product + "</option>";
			
		});
    	product_select += "</select>";
    	
    	$("#graph_input").html(product_select);
    }
    

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