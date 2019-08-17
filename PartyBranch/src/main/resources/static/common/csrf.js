/**
 * 
 */
function CsrfToken(){
	  /* $.get(contextPath+"/csrf",function(data){
			   $("meta[name='_csrf']").remove();
	           $("meta[name='_csrf_header']").remove();
	            $("head").append(data);
	            var token = $("meta[name='_csrf']").attr("content");
	            var header = $("meta[name='_csrf_header']").attr("content");
	            $.ajaxSetup({
	                 beforeSend: function (xhr) {
	                  if(header && token ){
	                      xhr.setRequestHeader(header, token);
	                  }
	             }
	          });
	     });*/
	
			 var token = $("meta[name='_csrf']").attr("content");
		     var header = $("meta[name='_csrf_header']").attr("content");
		     $.ajaxSetup({
		          beforeSend: function (xhr) {
		           if(header && token ){
		               xhr.setRequestHeader(header, token);
		           }
		      }
		   });
	   }
(function(){
	new CsrfToken();
})()