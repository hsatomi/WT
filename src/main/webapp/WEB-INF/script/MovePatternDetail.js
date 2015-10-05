window.onload = function () {
    // ページ読み込み時に実行したい処理
    document.getElementById("statistics_information").style.width = window.parent.screen.width * 0.4 + "px";
    document.getElementById("statistics_information").style.height = window.parent.screen.height * 0.8 + "px";

    document.getElementById("parameter_pattern").style.width = window.parent.screen.width * 0.5 + "px";
    document.getElementById("parameter_pattern").style.height = window.parent.screen.height * 0.8 + "px";
};

//登録
function doRegist(){

	var data_list = [];

	var i=0;
	var borderList = document.getElementsByClassName("list");
	for(i=0;i<borderList.length;i++){
		var row = borderList[i];

		var idx=0;
		var _id = row.cells[idx++].firstElementChild.value;
		var _url = row.cells[idx++].firstElementChild.value;
		var _画面タイトル = row.cells[idx++].firstElementChild.value;
		var _遷移パターン管理id = row.cells[idx++].firstElementChild.value;
		var _遷移順 = row.cells[idx++].firstElementChild.value;
		var _入力パターンid = row.cells[idx++].firstElementChild.value;

		var data_row = {
				"id":_id
				,"url":_url
				,"画面タイトル":_画面タイトル
				,"遷移パターン管理id":_遷移パターン管理id
				,"遷移順":_遷移順
				,"入力パターンid":_入力パターンid
		};
		data_list.push(data_row);
	}

	var data = {
			"list":data_list
	};

    $.ajax({
        type:"post",
        url:URL_API_BASE+"/MovePatternDetail/api/update",
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("登録しました");
//            	location.reload();
            	location.href = location.href;
        	}else{
            	alert("登録に失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗");
        }
    });

}

//明細削除
function doDelete(_id){

	var data = {
			"id":_id
	};

    $.ajax({
        type:"post",
        url:URL_API_BASE+"/MovePatternDetail/api/delete",
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("削除しました");
//            	location.reload();
            	location.href = location.href;
        	}else{
            	alert("削除に失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗");
        }
    });

}

//明細追加
function doAdd(_id){

	var data = {
			"id":_id
	};

    $.ajax({
        type:"post",
        url:URL_API_BASE+"/MovePatternDetail/api/add",
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("登録しました");
//            	location.reload();
            	location.href = location.href;
        	}else{
            	alert("登録に失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗");
        }
    });

}
