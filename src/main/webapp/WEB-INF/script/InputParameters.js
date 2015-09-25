window.onload = function () {
    // ページ読み込み時に実行したい処理
    document.getElementById("statistics_information").style.width = window.parent.screen.width * 0.4 + "px";
    document.getElementById("statistics_information").style.height = window.parent.screen.height * 0.8 + "px";

    document.getElementById("project_list").style.width = window.parent.screen.width * 0.5 + "px";
    document.getElementById("project_list").style.height = window.parent.screen.height * 0.8 + "px";
};

function move(mode) {
    switch (mode) {
        case "transition_list": location.href = "transition_list.jsp"; break;
        default: break;
    }
}

function doRegist(){
	var _id = document.getElementById("_id").value;
	var _no = document.getElementById("_no").value;
	var _テストケース管理id = document.getElementById("_テストケース管理id").value;
	var _備考 = document.getElementById("_備考").value;
	var _入力パターン名 = document.getElementById("_入力パターン名").value;
//	var _id = document.getElementById("input_id").value;

	var data_inputPattern = {
			"id":_id
			,"no":_no
			,"テストケース管理id":_テストケース管理id
			,"備考":_備考
			,"入力パターン名":_入力パターン名
			};
	var data_list = [];

	var i=0;
	var borderList = document.getElementsByClassName("list");
	for(i=0;i<borderList.length;i++){
		var row = borderList[i];

		var _実行順 = row.cells[0].firstElementChild.value;
		var _項目名 = row.cells[1].firstElementChild.value;
		var _エレメント型 = row.cells[2].firstElementChild.value;
		var _エレメント名 = row.cells[3].firstElementChild.value;
		var _アクション = row.cells[4].firstElementChild.value;
		var _型 = row.cells[5].firstElementChild.value;
		var _値 = row.cells[6].firstElementChild.value;
		var _備考 = row.cells[7].firstElementChild.value;
		var _id = row.cells[8].firstElementChild.value;

		var data_row = {
				"id":_id
				,"実行順":_実行順
				,"項目名":_項目名
				,"エレメント型":_エレメント型
				,"エレメント名":_エレメント名
				,"アクション":_アクション
				,"型":_型
				,"値":_値
				,"備考":_備考
		};
		data_list.push(data_row);
	}

	var data = {
			"inputPattern":data_inputPattern
			,"list":data_list
	};

    $.ajax({
        type:"post",
        url:URL_API_BASE+"/InputParameters/api/update",
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


