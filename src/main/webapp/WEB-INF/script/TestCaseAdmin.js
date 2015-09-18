window.onload = function () {
    // ページ読み込み時に実行したい処理
    document.getElementById("statistics_information").style.width = window.parent.screen.width * 0.4 + "px";
    document.getElementById("statistics_information").style.height = window.parent.screen.height * 0.8 + "px";

    document.getElementById("parameter_pattern").style.width = window.parent.screen.width * 0.5 + "px";
    document.getElementById("parameter_pattern").style.height = window.parent.screen.height * 0.8 + "px";
};

//画面遷移
function move_InputParameters(id) {
	location.href = "InputParameters?id=" + id;
	return;

//    switch (id) {
//        case "1": location.href = "InputParameters?id=" + id; break;
//        default: break;
//    }
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
            	unselectAll();
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
	polling(_id);
}

//Jenkinsジョブポーリング
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
