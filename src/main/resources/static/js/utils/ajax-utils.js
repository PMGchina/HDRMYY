$(function() {
	$.del = function(url,data,fn) {
		$.ajax({
			type : 'DELETE',
			url : url,
			data : data,
			dataType : 'json',
			success : function(data) {
				fn(data);
			},
			error : function(XMLHttpRequest,textStatus,errorThrown) {
				console.log(XMLHttpRequest.responseText);
			}
		});
	}
	
	$.call = function(url) {
		$.ajax({
	        url: url,
	        success: function(data) {
	        	return data;
	        }
	    });
	}

	$.posty = function(url, data, fn) {
		$.ajax({
			headers: {'Accept': 'application/json','Content-Type': 'application/json'},
		    cache: false,
	        async: false,
		    type: 'POST',
		    url: url,
		    data: JSON.stringify(data),
		    dataType: 'json',
		    error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.responseText);
			},
		    success: function(result) {
		    	if (fn) fn(result);
		    }
		});
	}
	
	$.gety = function(url, fn) {
		$.ajax({
			headers: {'Accept': 'application/json','Content-Type': 'application/json'},
		    cache: false,
	        async: false,
		    type: 'GET',
		    url: url,
		    dataType: 'json',
		    error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.responseText);
			},
		    success: function(result) {
		    	if (fn) fn(result);
		    }
		});
	}
});