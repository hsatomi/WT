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

		var _id = row.cells[0].firstElementChild.value;
		var _画面管理id = row.cells[1].firstElementChild.value;
		var _遷移パターン名 = row.cells[2].firstElementChild.value;
		var _備考 = row.cells[3].firstElementChild.value;

		var data_row = {
				"id":_id
				,"画面管理id":_画面管理id
				,"遷移パターン名":_遷移パターン名
				,"備考":_備考
		};
		data_list.push(data_row);
	}

	var data = {
			"list":data_list
	};

    $.ajax({
        type:"post",
        url:URL_API_BASE+"/MovePatternAdmin/api/update",
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("登録しました");
            	location.reload();
            	unselectAll();
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
