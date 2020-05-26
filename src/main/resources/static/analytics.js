/**
 * All functions to display graphs depicting analysis of sales
 */

$(document).ready(function()
{
	/**
	 * Sends a request to AnalyticsController when the 'Display Graph' button is clicked.
	 * METHOD = GET
	 * 
	 * @param null
	 * @returns Array of objects containing product sales data
	 */
	
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
	
	/**
	 * Sends a request to retrieve sales data of a specific Product
	 * METHOD = GET
	 * 
	 * @param Product ID as a part of the url
	 * @returns Array of objects containing sales data of requested Product
	 */
    
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
    
    /**
     * Function that converts the response data into a line graph
     */
    
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

			console.log('Key list = ' + productIdList);
			
			var data = [];
			
			$(productIdList).each(function(i, product)
			{
				data.push({type : "line", name : product, showInLegend: true, dataPoints : grouped[product]});
				
			});
			
			var respose_data = {chartData : data, dropdownData : productIdList};
			
			return respose_data;			
    }
    
    /**
     * Function to display all available product IDs as a drop down list.
     * User can select a specific Product to see the sales graph for it.
     */
    
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
});