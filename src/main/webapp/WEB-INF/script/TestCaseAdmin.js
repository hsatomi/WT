window.onload = function () {
    // ページ読み込み時に実行したい処理
    document.getElementById("statistics_information").style.width = window.parent.screen.width * 0.4 + "px";
    document.getElementById("statistics_information").style.height = window.parent.screen.height * 0.8 + "px";

    document.getElementById("parameter_pattern").style.width = window.parent.screen.width * 0.5 + "px";
    document.getElementById("parameter_pattern").style.height = window.parent.screen.height * 0.8 + "px";
};


function _onload(_id){
	url_push();
	polling(_id);
}

//画面遷移
function move_InputPattern(id,test_case_id,move_pattern_detail_id) {

//	if (id === undefined || id == "") {
//		doRegistNewInputPattern();
//		return;
//	}
	if (test_case_id === undefined) {
		test_case_id="";
	}
	if (move_pattern_detail_id === undefined) {
		move_pattern_detail_id="";
	}
	location.href = "InputPattern?id=" + id + "&test_case_id=" + test_case_id + "&move_pattern_detail_id=" + move_pattern_detail_id;
	return;
}

//画面遷移
function move_MovePatternDetail(id) {
	location.href = "MovePatternDetail?id=" + id;
	return;
}

//画面遷移(確認画面)
function move_TestCaseAdmin(name,id,input_id) {

	var url = "";
	switch (name) {
	case "htmlResult":
		url = "TestCaseAdmin/htmlResult?id=" + id + "&input_id=" + input_id;
		break;
	case "htmlDiff":
		url = "TestCaseAdmin/htmlDiff?id=" + id + "&input_id=" + input_id;
		break;
	case "htmlCorrect":
		url = "TestCaseAdmin/htmlCorrect?id=" + id + "&input_id=" + input_id;
		break;
	case "dbResult":
		url = "TestCaseAdmin/dbResult?id=" + id + "&input_id=" + input_id;
		break;
	case "dbDiff":
		url = "TestCaseAdmin/dbDiff?id=" + id + "&input_id=" + input_id;
		break;
	case "dbCorrect":
		url = "TestCaseAdmin/dbCorrect?id=" + id + "&input_id=" + input_id;
		break;
	case "moveResult":
		url = "TestCaseAdmin/moveResult?id=" + id + "&input_id=" + input_id;
		break;
	case "pictureCorrect":
		url = "TestCaseAdmin/pictureCorrect?id=" + id + "&input_id=" + input_id;
		break;
	case "pictureNow":
		url = "TestCaseAdmin/pictureNow?id=" + id + "&input_id=" + input_id;
		break;
	case "pictureDiff":
		url = "TestCaseAdmin/pictureDiff?id=" + id + "&input_id=" + input_id;
		break;
	default: break;
	}

//	location.href = url;
	var htmlsrc = window.open("","","scrollbars=yes, width=800,height=600");
	htmlsrc.location.href = url;

	return;
}

//リセット・生成・実行を全て行う(初回)
function execFirstAll(_id){
	selectAll();
	var ids = getSelectedStr();
	var data = {"id":_id,"input_ids":ids};
	//1段目ajax
    $.ajax({
        type:"post",
        url:URL_RESET,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
        		//2段目ajax
                $.ajax({
                    type:"post",
                    url:URL_GENERATE,
                    data:JSON.stringify(data),
                    contentType: 'application/json',
                    dataType: "json",
                    success: function(json_data1) {
                        // 成功時の処理
                    	if(json_data1 == true){
                    		//3段目ajax
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
                    	            	location.href = location.href;
                    	        	}else{
                    	            	alert("JOB実行の登録に失敗しました");
                    	        	}
                    	        },
                    	        error: function(json_data2) {
                    	            // 失敗時の処理
                                	alert("失敗しました");
                    	        }
                    	    });
                    	}else{
                        	alert("テストユニットの生成に失敗しました");
                    	}
                    },
                    error: function(json_data2) {
                        // 失敗時の処理
                    	alert("失敗しました");
                    }
                });
        	}else{
            	alert("リセットに失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗しました");
        }
    });
}

//生成・実行を全て行う
function execAll(_id){
	selectAll();
	var ids = getSelectedStr();
	var data = {"id":_id,"input_ids":ids};
	//2段目ajax
    $.ajax({
        type:"post",
        url:URL_GENERATE,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
        		//3段目ajax
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
        	            	location.href = location.href;
        	        	}else{
        	            	alert("JOB実行の登録に失敗しました");
        	        	}
        	        },
        	        error: function(json_data2) {
        	            // 失敗時の処理
                    	alert("失敗しました");
        	        }
        	    });
        	}else{
            	alert("テストユニットの生成に失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗しました");
        }
    });
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
            	location.href = location.href;
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
            	location.href = location.href;
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

//テストユニット削除
function ungenerate(_id){
	var data = {"id":_id};
	$.ajax({
	    type:"post",
	    url:URL_UNGENERATE,
	    data:JSON.stringify(data),
	    contentType: 'application/json',
	    dataType: "json",
	    success: function(json_data1) {
	        // 成功時の処理
	    	if(json_data1 == true){
	        	alert("テストユニットを削除しました");
	      	location.href = location.href;
	    	}else{
	        	alert("テストユニットの削除に失敗しました");
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
            	location.href = location.href;
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
function polling(_id){
	var data = {"id":_id};
    $.ajax({
        type:"post",
        url:URL_POLLING,
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        complete: function(json_data1) {
            // 応答時の処理

        	if(json_data1!=null){
        		if(json_data1.responseText=="待機不要"){
        			//何もしない
        		}
        		if(json_data1.responseText=="待機完了"){
                	location.href = location.href;
        		}
        		if(json_data1.responseText=="待機失敗"){
        			//何もしない
        		}
        	}
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
            	location.href = location.href;
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


//(入力パターン新規)登録
function doRegistNewInputPattern(){
	var _テストケース管理id = document.getElementById("_テストケース管理id").value;
	var data_inputPattern = {
			"テストケース管理id":_テストケース管理id
			};
	var data = {
			"inputPattern":data_inputPattern
	};

	$.ajax({
	    type:"post",
	    url:URL_API_BASE+"/InputPattern/api/update",
	    data:JSON.stringify(data),
	    contentType: 'application/json',
	    dataType: "json",
	    success: function(json_data1) {
	        // 成功時の処理
	    	if(json_data1 == true){
	        	alert("登録しました");
	        	location.href = location.href;
	    	}else{
	        	alert("登録に失敗しました");
	    	}
	    },
	    error: function(json_data2) {
	        // 失敗時の処理
	    	alert("失敗しました");
	    }
	});
}

//明細削除
function doDelete(_id){
	var ids = getSelectedStr();
	if(ids.length==0){
		alert("ケースが未選択です");
		return;
	}

	var data = {"id":_id,"input_ids":ids};

    $.ajax({
        type:"post",
        url:URL_API_BASE+"/TestCaseAdmin/api/delete",
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("削除しました");
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

//明細コピー
function doCopy(_id){
	var ids = getSelectedStr();
	if(ids.length==0){
		alert("ケースが未選択です");
		return;
	}

	var data = {"id":_id,"input_ids":ids};

    $.ajax({
        type:"post",
        url:URL_API_BASE+"/TestCaseAdmin/api/copy",
        data:JSON.stringify(data),
        contentType: 'application/json',
        dataType: "json",
        success: function(json_data1) {
            // 成功時の処理
        	if(json_data1 == true){
            	alert("コピーしました");
            	location.href = location.href;
        	}else{
            	alert("コピーに失敗しました");
        	}
        },
        error: function(json_data2) {
            // 失敗時の処理
        	alert("失敗しました");
        }
    });
}

//function HtmlWrite(strText){
//	var str = '';
//	str+='			<head>';
//	str+='			<script src="script/TestCaseAdmin.js"></script>';
//	str+='			</head>';
//	str+='			<div style="margin:10px; border-style:solid;" title="管理番号-テストケースの並び順に影響します">';
//	str+='				<h4>No</h4>';
//	str+='				<input type="number" id="_no"';
//	str+='					value="" />';
//	str+='			</div>';
//	str+='			<div style="margin:10px; border-style:solid;" title="パターンを端的に表す名称">';
//	str+='				<h4>パターン名</h4>';
//	str+='				<input type="text" id="_入力パターン名"';
//	str+='					value="" />';
//	str+='			</div>';
//	str+='			<input type="button" value="登録" onClick="doRegistNewInputPattern()" />';
//	str+='			<input type="button" value="閉じる" onClick="test(1)" />';
//	var htmlsrc = window.open("","","scrollbars=yes, width=600,height=400");
//	htmlsrc.document.open();
//	htmlsrc.document.write(str);
//	htmlsrc.document.close();
//}
