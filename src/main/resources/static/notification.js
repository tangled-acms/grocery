/**
 * 
 */

$(document).ready(function()
{
	if (Notification.permission !== "granted")   
    {  
        Notification.requestPermission();  
        
    }  
	console.log(Notification.permission);
	
	function customnotify(title,desc,url)   
	{  
	  
	  if (Notification.permission !== "granted")  
	  {  
	   Notification.requestPermission();  
	  }  
	  else  
	  {  
	   var notification = new Notification(title, {  
	   
	   body: desc,  
	   });  
	  
	   /* Remove the notification from Notification Center when clicked.*/  
	   notification.onclick = function () {  
	   window.open(url);   
	   };  
	  
	   /* Callback function when the notification is closed. */  
	   notification.onclose = function () {  
	   console.log('Notification closed');  
	   };  
	  
	  }  
	}  
});