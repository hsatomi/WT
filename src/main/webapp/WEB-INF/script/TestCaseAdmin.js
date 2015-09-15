window.onload = function () {
    // ページ読み込み時に実行したい処理
    document.getElementById("statistics_information").style.width = window.parent.screen.width * 0.4 + "px";
    document.getElementById("statistics_information").style.height = window.parent.screen.height * 0.8 + "px";

    document.getElementById("parameter_pattern").style.width = window.parent.screen.width * 0.5 + "px";
    document.getElementById("parameter_pattern").style.height = window.parent.screen.height * 0.8 + "px";
};

function move_InputParameters(id) {
	location.href = "InputParameters?id=" + id;
	return;

//    switch (id) {
//        case "1": location.href = "InputParameters?id=" + id; break;
//        default: break;
//    }
}

function generate(_id){

	//TODO:選択されているテストケース管理idとテストケースNoを送信する
//	var data = {"id":id
//			,input_ids:[1,2,3]
//	};
	var data = {"id":_id,"input_ids":[1]};

    $.ajax({
        type:"post",
        url:"http://localhost:8080/souya/api/generateTestCase",
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	alert("生成成功");
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("生成失敗");
        }

    });


}