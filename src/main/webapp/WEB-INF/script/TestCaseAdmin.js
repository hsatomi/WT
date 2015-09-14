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

function generate(){
//	var form = document.createElement("form");
//	var form = document.getElementById("input_parameter_pattern");

//	var form = document.forms[0];
//	form.action="http://localhost:8080/souya/TestCaseAdmin/generate";
//	form.method="post";
//	form.submit();

    $.ajax({
        type:"post",
        url:"http://localhost:8080/souya/api/generateTestCase",
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	alert("成功");
        },
        error: function(json_data2) {
              // 失敗時の処理
        	alert("失敗");
        }

    });


}