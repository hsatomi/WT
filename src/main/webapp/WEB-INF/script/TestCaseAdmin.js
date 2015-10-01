window.onload = function () {
    // ページ読み込み時に実行したい処理
    document.getElementById("statistics_information").style.width = window.parent.screen.width * 0.4 + "px";
    document.getElementById("statistics_information").style.height = window.parent.screen.height * 0.8 + "px";

    document.getElementById("parameter_pattern").style.width = window.parent.screen.width * 0.5 + "px";
    document.getElementById("parameter_pattern").style.height = window.parent.screen.height * 0.8 + "px";
};

//画面遷移
function move_InputParameters(id,test_case_id,move_pattern_detail_id) {
	if (test_case_id === undefined) {
		test_case_id="";
	}
	if (move_pattern_detail_id === undefined) {
		move_pattern_detail_id="";
	}
	location.href = "InputParameters?id=" + id + "&test_case_id=" + test_case_id + "&move_pattern_detail_id=" + move_pattern_detail_id;
	return;
}

//画面遷移(確認画面)
function move_TestCaseAdmin(name,id,input_id) {

	switch (name) {
	case "htmlResult":
		location.href = "TestCaseAdmin/htmlResult?id=" + id + "&input_id=" + input_id;
		break;
	case "htmlDiff":
		location.href = "TestCaseAdmin/htmlDiff?id=" + id + "&input_id=" + input_id;
		break;
	case "htmlCorrect":
		location.href = "TestCaseAdmin/htmlCorrect?id=" + id + "&input_id=" + input_id;
		break;
	case "dbResult":
		location.href = "TestCaseAdmin/dbResult?id=" + id + "&input_id=" + input_id;
		break;
	case "dbDiff":
		location.href = "TestCaseAdmin/dbDiff?id=" + id + "&input_id=" + input_id;
		break;
	case "dbCorrect":
		location.href = "TestCaseAdmin/dbCorrect?id=" + id + "&input_id=" + input_id;
		break;
	default: break;
	}

	return;
}

//回数リセット
function reset(_id){
	var ids = getSelectedStr();
	if(ids.length==0){
		alert("ケースが未選択です");
		return;
	}

	var data = {"id":_id,"input_ids":ids};
    $.ajax({
        type:"post",
        url:URL_RESET,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("リセットしました");
            	location.reload();
//            	unselectAll();
        	}else{
            	alert("リセットに失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗");
        }
    });
}

//テストユニット自動生成
function generate(_id){
	var ids = getSelectedStr();
	if(ids.length==0){
		alert("ケースが未選択です");
		return;
	}
	var data = {"id":_id,"input_ids":ids};
    $.ajax({
        type:"post",
        url:URL_GENERATE,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("テストユニットを生成しました");
            	location.reload();
        	}else{
            	alert("テストユニットの生成に失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗");
        }
    });
}

//Jenkins実行登録
function execjenkins(_id){
	var ids = getSelectedStr();
	if(ids.length==0){
		alert("ケースが未選択です");
		return;
	}
	var data = {"id":_id,"input_ids":ids};
    $.ajax({
        type:"post",
        url:URL_EXECJENKINS,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("JOB実行を登録しました");
            	location.reload();
        	}else{
            	alert("JOB実行の登録に失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗");
        }
    });
}

//Jenkinsジョブポーリング
//TODO:ジョブ実行をポーリングして再表示したい・・・けど、うまくいかない
function polling(_id){
	var data = {"id":_id};
    $.ajax({
        type:"post",
        url:URL_POLLING,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	location.reload();
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("ポーリング失敗");
        }
    });
}

//全て選択
function selectAll(){
	var i;
	var chkboxes = document.getElementsByClassName("chkbox");
	for(i=0;i<chkboxes.length;i++){
		var chk = chkboxes[i];
		chk.checked = true;
	}
}

//NGだけ選択
function selectNG(){
	var i;
	var chkboxes = document.getElementsByClassName("chkbox");
	for(i=0;i<chkboxes.length;i++){
		var chk = chkboxes[i];
		chk.checked = true;
	}
}

//全て解除
function unselectAll(){
	var i;
	var chkboxes = document.getElementsByClassName("chkbox");
	for(i=0;i<chkboxes.length;i++){
		var chk = chkboxes[i];
		chk.checked = false;
	}
}

//選択されているidリストを取得 ex. "1,2,4"
function getSelectedStr(){
	var i;
	var list=[];
	var chkboxes = document.getElementsByClassName("chkbox");
	for(i=0;i<chkboxes.length;i++){
		var chk = chkboxes[i];
		if(chk.checked){
			var val = chk.value;
//			if(list==""){
//				list+=val;
//			}else{
//				list+=","+val;
//			}
			list.push(val);
		}
	}
	return list;
}

//遷移先ページ解析APIコール
function analyze(_id){
	var data = {"id":_id};
    $.ajax({
        type:"post",
        url:URL_ANALYZE,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("解析しました");
            	location.reload();
        	}else{
            	alert("解析に失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗");
        }
    });
}


//別窓でテキスト表示(未使用)
function HtmlWrite(strText){
	//ウィンドウサイズ600×400でスクロールバーONに設定する
var htmlsource = window.open("", "", "scrollbars=yes, width=600, height=400");
htmlsource.document.open();
htmlsource.document.write(strText);
htmlsource.document.close();
}