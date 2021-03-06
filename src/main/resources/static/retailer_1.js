
/**
 * All functions that depend on the Retailer Table
 * 
 * Get all data,
 * Add new Retailer,
 * Modify data of existing Retailer,
 * Delete existing Retailer
 * 
 */

$(document).ready(function()	
{
	 var retailer_row_count = 0;
	 disable_radio_retailer();
	
	/**
	 * Display all available retailers when the web page loads
	 */
	 
	ajaxRetailerGetAll();
	
	/**
	 * Called when the 'Add retailer' button is licked.
	 * Adds a new row of editable cells where the user enters retailer data
	 * 
	 * User clicks 'DONE' button upon completion
	 */
	
    $('#new_retailer').click(function()
    {
        var j, cell, column_count = 0;

          retailer_row_count = $('#Retailer_table tr').length;
        

        $('#Retailer_table tr:first').children().each(function(){
            column_count++;
        });

        alert('new retailer button clicked - ' + column_count + ' - ' + retailer_row_count);

        $('#Retailer_table').append('<tr id="row_' + (retailer_row_count) + '" class="row">' +
                                    '<th><input type="radio" class="select_retailer" name="select_retailer" id="' + (retailer_row_count) + '"/></th>' +
                                    '<th class="sl_no">' + retailer_row_count + '</th>' +
                                    '<td><input type= "text" required id="r_id'+ retailer_row_count +'_inp"></td>' +
                                    '<td><input type= "text" required id="r_name'+ retailer_row_count +'_inp"></td>' +
                                    '<td><input type= "number" required id="r_cont1'+ retailer_row_count +'_inp"></td>' +
                                    '<td><input type= "number" required id="r_cont2'+ retailer_row_count +'_inp"></td>' +
                                    '<td><input type= "number" required id="r_cont3'+ retailer_row_count +'_inp"></td>' +
                                    '<td><input type= "text" required id="r_addr1'+ retailer_row_count +'_inp"></td>' +
                                    '<td><input type= "text" required id="r_addr2'+ retailer_row_count +'_inp"></td>' +
                                    '<td><input type= "text" required id="r_addr3'+ retailer_row_count + '_inp"></td>' +
                                    '</tr>');
        
        disable_buttons_retailer();

        $('#insert_button2').html('<button id="retailer_done" class="done button">DONE</button>');
    });
    
    /**
     * Calls the function to send all data to Retailer Table
     * 
     * Replaces all editable cells with non editable ones.
     * Activates all buttons for further operations
     */

    $('#retailer_done').live('click', function()
    {
        //alert('Done clicked');
    	ajaxRetailerPost();

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
    
    /**
     * Sends a request to RetailerController to add data to Retailer Table.
     * METHOD = POST
     * 
     * @param Object of retailer type containing all data
     * @returns Object containing all retailer data upon success
     */

	function ajaxRetailerPost()
    {
    	var RetailerData = {
    			retailerId : $('#r_id' + retailer_row_count + '_inp').val(),
    			name : $('#r_name' + retailer_row_count + '_inp').val(),
    			contact1 : $('#r_cont1' + retailer_row_count + '_inp').val(),
				contact2 : $('#r_cont2' + retailer_row_count + '_inp').val(),
				contact3 : $('#r_cont3' + retailer_row_count + '_inp').val(),
    			address1 : $('#r_addr1' + retailer_row_count + '_inp').val(),
				address2 : $('#r_addr2' + retailer_row_count + '_inp').val(),
				address3 : $('#r_addr3' + retailer_row_count + '_inp').val()
				
    	}
    	
    	alert(RetailerData.retailerId + ' ' + RetailerData.name);
    	console.log(RetailerData);
    	$.ajax({
    		type : "POST",
    		contentType : "application/json",
    		url : "retailer/save",
    		data : JSON.stringify(RetailerData),
    		dataType : "json",
    		success : function(retailer_result)
    		{
    			alert("Data successfully added - " + retailer_result.retailerId);
    		}
    		
    	});
    }
	
	/**
	 * Sends request to RetailerController to retrieve all active retailers from Retailer Table.
	 * METHOD = GET
	 * 
	 *  @param null
	 *  @returns Array of objects of Retailer type containing all retailer information
	 */
    
    function ajaxRetailerGetAll()
    {
    	//alert("Get all retailer called!");
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/retailer/getAllActive",
    		success : function(retailer_result)
    		{
    			display_retailer_details(retailer_result)
    			
    			disable_radio_retailer();
    			$("#get_ret_button").html('<button id="display_all_retailer" class="button">Display inactive retailers </button>');
    		}
		});		
	}
    
    /**
     * Sends request to RetailerController to retrieve all active and inactive retailers from Retailer Table.
	 * METHOD = GET
	 * 
	 *  @param null
	 *  @returns Array of objects of Retailer type containing all retailer information
     */
    
    $("#display_all_retailer").live('click', function()
    {
    	$.ajax({
    		type : "GET",
    		contentType : "application/json",
    		url : "/retailer/getAll",
    		success : function(result)
    		{    			
    			display_retailer_details(result);
    			
    			disable_radio_retailer();
    			$("#get_ret_button").html('<button id="display_retailer" class="button">Display active retailers</button>');
    		}
    	
    	});
    });
    
    /**
     * Gets all active retailers' details when the 'Display Active retailer' button is clicked.
     */
    
    $("#display_retailer").live('click', function()
    {
    	ajaxRetailerGetAll();
    });
    
    /**
     * Called when the user clicks 'Modify Retailer' button.
     * Enables all radio buttons corresponding to each retailer row.
     * When user selects one retailer, all cells of the selected row change to editable state
     * 
     */

    $('#modify_retailer').click(function()
    {
        disable_buttons_retailer();
        enable_radio_retailer();

        $('#Retailer_table input').on('click', function()
        {
            var radioValue = $("input[name='select_retailer']:checked").attr('id');
            if(radioValue){
                alert("Selected row - " + radioValue);
                retailer_row_count = radioValue;
                
                $(this).parents('tr').find('td').each(function()
                {
                    var OriginalContent = $(this).text();
                    var elementId = $(this).attr('id');
                    alert(elementId);
                    //alert(jQuery.type(OriginalContent) + ' - ' + $.isNumeric(OriginalContent));

                    if($.isNumeric(OriginalContent))
                        $(this).html("<input type='number' id='" + elementId + "_inp' value='" + OriginalContent + "' />")
                    else
                        $(this).html("<input type='text' id='" + elementId + "_inp' value='" + OriginalContent + "' />");
                });

                $('#insert_button2').html('<button id="retailer_modify_done" class="done button">DONE</button>');
            }
        });

    });
    
    /**
     * Sends request to RetailerController when user clicks 'Done' after modifying user details
     * METHOD = PUT
     * 
     * @param request object of Retailer type containing all details of retailer
     * @returns Retailer object that was successfully updated to Retailer table 
     */
    
	//ajax call to update existing retailer row
    $('#retailer_modify_done').live('click', function()
	{    	
    	alert(retailer_row_count);
    	
    	var RetailerData = {
    			retailerId : $('#r_id_' + retailer_row_count + '_inp').val(),
    			name : $('#r_name' + retailer_row_count + '_inp').val(),
    			contact1 : $('#r_cont1' + retailer_row_count + '_inp').val(),
				contact2 : $('#r_cont2' + retailer_row_count + '_inp').val(),
				contact3 : $('#r_cont3' + retailer_row_count + '_inp').val(),
    			address1 : $('#r_addr1' + retailer_row_count + '_inp').val(),
				address2 : $('#r_addr2' + retailer_row_count + '_inp').val(),
				address3 : $('#r_addr3' + retailer_row_count + '_inp').val()
    	}
    	
    	alert(RetailerData.retailerId + ' ' + RetailerData.address1);
    	console.log(RetailerData);
    	
    	$.ajax({
    		type : "PUT",
    		contentType : "application/json",
    		url : "/retailer/update/" + RetailerData.retailerId,
    		data : JSON.stringify(RetailerData),
    		dataType : "json",
    		success : function(retailer_result)
    		{
    			alert("Data successfully updated - " + retailer_result.retailerId);
    			//ajaxRetailerGetAll();
    			$("input[name='select_retailer']").unbind('click');
    		}
    		
    	});
    	
    	$('input[type!=radio]').each(function () 
        {
            var value = $(this).val();
            $(this).replaceWith(value);
        });
    	
    	$('#insert_button2').html('');
    	enable_buttons_retailer();
    	disable_radio_retailer();
	});

    /**
     * Called when the user selects 'Delete Retailer' button
     * Radio buttons corresponding to each retailer get activated
     * User can select one retailer at a time to delete and then click 'Done'.
     */
    
    $('#delete_retailer').click(function(){
        alert("Delete Retailer is clicked");
        
        disable_buttons_retailer();
		enable_radio_retailer();
		$('#Retailer_table').on('click', function()
		{
			var radioValue = $("input[name='select_retailer']:checked").parents('tr').attr('id');
			if(radioValue){
				var delete_confirm = confirm("Are you sure you want to delete this retailer's details? " + radioValue);
				
                if(delete_confirm == true)
                    ajaxDeleteRetailer(radioValue);
                else
                    enable_radio_retailer();
			}
		$('#insert_button2').html('<button id="delete_done_retailer" class="done button">DONE</button>');	
		});
	});
    
    /**
     * If the user confirms the deletion of object, then request is sent to RetailerController to delete retailer details from Retailer Table.
     * METHOD = DELETE
     * 
     * @param Retailer ID passed as part of the url.
     * @returns Retailer ID if the operation was successful.
     */

    function ajaxDeleteRetailer(row_id)
    {
    	var length = row_id.length;
		var row_no = row_id.substring(length-1, length);
		alert(row_no);
    	var Id = $('#r_id_' + row_no).text();
    	alert(Id);
    	
    	$.ajax({
    		
    		type : "DELETE",
    		contentType : "application/json",
    		url : "/retailer/delete/" + Id,
    		success : function(retailer_result)
    		{
    			alert("Data successfully deleted - " + retailer_result);
    			$('#' + row_id).remove();
    			
    			$("input[name='select_retailer']").unbind('click');
    		}
    		
    	});
    }
    
	$('#delete_done_retailer').live('click', function()
	{
        enable_buttons_retailer();
        disable_radio_retailer();
        $('#insert_button2').html('');
    });
	
	/**
	 * Functions to 
	 * Enable retailer buttons,
	 * Disable retailer buttons,
	 * Enable radio buttons of Retailer Table,
	 * Disable radio buttons of Retailer Table.
	 */
	
    function disable_buttons_retailer()
    {
        
        $('.button').removeClass('enable_button');
        $('.button').addClass('disable_button');
    }

    function enable_buttons_retailer()
    {
        
        $('.button').removeClass('disable_button');
        $('.button').addClass('enable_button');
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
            $(this).attr('checked', false);
        });
    }
    
    /**
     * Function to display array of retailer objects as a table.
     */
    
    function display_retailer_details(retailer_result)
	{
		//alert(retailer_result);
		
		$("#Retailer_table").empty();
		$("#Retailer_table").append('<tr id="retailer_tab_heading" class="row header">' + 
								'<th></th>' +
								'<th>Sl. No</th>' +
								'<th>Retailer ID</th>' +
								'<th>Retailer Name</th>' + 
								'<th>Contact No 1</th>' +
								'<th>Contact No 2</th>' +
								'<th>Contact No 3</th>' +
								'<th>Address 1</th>' + 
								'<th>Address 2</th>' +
								'<th>Address 3</th>' + 
								 '</tr>');
		
		$(retailer_result).each(function(j, retailer)
		{
			retailer_row_count++;
	
			var Retailer_row = '<tr id="ret_row_' + (retailer_row_count) + '" class="row">' +
            '<th><input type="radio" class="select_retailer" name="select_retailer" id="' + (retailer_row_count) + '"/></th>' +
            '<th class="sl_no">' + retailer_row_count + '</th>' +
            '<td id="r_id_' + (retailer_row_count) + '">' + retailer.retailerId + '</td>' +
            '<td id="r_name' + (retailer_row_count) + '">' + retailer.name + '</td>' +
            '<td id="r_cont1' + (retailer_row_count) + '">' + retailer.contact1 + '</td>' +
            '<td id="r_cont2' + (retailer_row_count) + '">' + retailer.contact2 + '</td>' +
            '<td id="r_cont3' + (retailer_row_count) + '">' + retailer.contact3 + '</td>' +
            '<td id="r_addr1' + (retailer_row_count) + '">' + retailer.address1 + '</td>' +
            '<td id="r_addr2' + (retailer_row_count) + '">' + retailer.address2 + '</td>' +
            '<td id="r_addr3' + (retailer_row_count) + '">' + retailer.address3 + '</td>' +
            '</tr>';
			
			$("#Retailer_table").append(Retailer_row);
		});
	}
    
    
});