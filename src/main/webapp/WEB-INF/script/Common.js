
//現在のURLをサーバにプッシュしてSESSIONに格納する
function url_push(){
	var data = {"strLocationHref":location.href};
    $.ajax({
        type:"post",
        url:URL_UPDATE_SESSION_URLGO,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        async: false
    });
}

//1個前のURLをサーバからポップする
function url_back(){
	var data = {"strLocationHref":location.href};
	var response =
    $.ajax({
        type:"post",
        url:URL_UPDATE_SESSION_URLBACK,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        async: false
    });

	if(response!=""){
		var responseText = response.responseText;
		if(responseText!=""){
		    location.href = responseText;
		}
	}
}
