<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="co.jp.souya.tool.TTConst" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>テストケース管理画面</title>
	<script src="script/ajax.js"></script>
	<script src="script/TestCaseAdmin.js"></script>
	<link href="css/stylesheet.css" rel="stylesheet" />

<script>
var URL_API_BASE = "<%= TTConst.URL_API_BASE %>";
var URL_RESET = "<%= TTConst.URL_API_BASE+TTConst.URL_RESET_TESTCASE %>";
var URL_GENERATE = "<%= TTConst.URL_API_BASE+TTConst.URL_GENERATE_TESTCASE %>";
var URL_UNGENERATE = "<%= TTConst.URL_API_BASE+TTConst.URL_DELETE_TESTCASE %>";
var URL_EXECJENKINS = "<%= TTConst.URL_API_BASE+TTConst.URL_EXECJENKINS %>";
var URL_POLLING = "<%= TTConst.URL_API_BASE+TTConst.URL_POLLINGJENKINS %>";
var URL_ANALYZE = "<%= TTConst.URL_API_BASE+TTConst.URL_ANALYZE %>";
</script>
</head>

<body>
	<h3>テストツール - テストケース管理画面
    <a href="javascript:history.back();">戻る</a>
    <a href="">再表示</a>
	</h3>
	<div id="move_pattern_information" style="float: left; border-style: solid; margin-right: 20px;">
		<div>
			総合情報
			<div style="border-style: solid; margin:10px;">
				<h4>画面名</h4>
				<p>
					${dto.画面管理.画面名}
				</p>
			</div>
			<div style="border-style: solid; margin:10px;">
				<h4>画面遷移パターン名</h4>
				<p>
					${dto.遷移パターン管理.遷移パターン名}
				</p>
				<h4>備考:</h4>
				<p>
					${dto.遷移パターン管理.備考}
				</p>
				<h4>画面遷移</h4>
				<a href="javascript:move_MovePatternDetail('${dto.遷移パターン管理.id}')">①遷移パターン編集へ</a>
				<c:forEach items="${dto.遷移パターン明細リスト}" var="遷移パターン明細" >
				<div style="width:90%;border-style: solid; margin:10px; ">
					${遷移パターン明細.画面タイトル}
					<br/>
					${遷移パターン明細.url}
					<br/>
					<a href="javascript:move_InputPattern('${遷移パターン明細.入力パターンid}')">②入力パターン編集へ</a>
					<input type="hidden" id="_遷移パターン明細id" value="${遷移パターン明細.id}" />
					<input type="hidden" id="_入力パターンid" value="${遷移パターン明細.入力パターンid}" />
				</div>
				</c:forEach>
				<input type="button" name="btnAnalyze" value="遷移先解析" onClick="analyze(${dto.テストケース管理.id})" />
			</div>
			<div style="margin:10px; border-style:solid; display:none;">
				<h4>インプット情報 !未実装</h4>
				<div style="margin:0 auto;">
					インプット(初期化)
					<SELECT>
						<OPTION>test</OPTION>
					</SELECT>
				</div>
				<div>
					<input type="file" name="example" size="30">
				</div>
			</div>
			<div style="margin:10px; border-style:solid; display:none;">
				<h4>アウトプット情報 !未実装</h4>
				<div style="margin:0 auto;">
					更新するテーブル
					<SELECT>
						<OPTION>test</OPTION>
					</SELECT>
				</div>
			</div>
		</div>
	</div>
	<div id="input_parameter_pattern" style="float: left; border-style: solid; padding: 10px;">
		<div style="display:none;">
			<input type="hidden" id="_テストケース管理id" value="${dto.テストケース管理.id}" />
		</div>
		<div>
			入力パターン一覧
			<br>
			<br>
			<button>パターン自動生成</button>
			<input type="button" value="③パターン追加" onClick="move_InputPattern('',${dto.テストケース管理.id},'')" />
			<input type="button" value="パターン削除" onClick="doDelete(${dto.テストケース管理.id})" />
			<br>
			<br>
			<input type="button" value="UNIT一括実行(初回)" onClick="execFirstAll(${dto.テストケース管理.id})" />
			<input type="button" value="UNIT一括実行" onClick="execAll(${dto.テストケース管理.id})" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="全て選択" onClick="selectAll()" />
			<input type="button" value="全て解除" onClick="unselectAll()" />
			<input type="button" value="回数リセット" onClick="reset(${dto.テストケース管理.id})" />
			<input type="button" value="UNIT部分生成" onClick="generate(${dto.テストケース管理.id})" />
			<input type="button" value="UNIT部分実行" onClick="execjenkins(${dto.テストケース管理.id})" />
			<input type="button" value="UNIT削除" onClick="ungenerate(${dto.テストケース管理.id})" />
			<br>
			<table class="borderList">
				<tr>
					<th>No</th>
					<th>id</th>
					<th>入力パターン名</th>
					<th>実行回数</th>
					<th>JOB状況</th>
					<th>キャプチャ(正解)</th>
					<th>キャプチャ(今回)</th>
					<th width=70>HTML</th>
					<th width=70>DB</th>
					<th>判定結果</th>
				</tr>

				<c:forEach items="${dto.入力パターンリスト}" var="入力パターン" >
				<tr>
					<td>
						${入力パターン.no}
						<input type="checkbox" class="chkbox" value="${入力パターン.id}" >
					</td>
					<td>
						${入力パターン.id}
					</td>
					<td>
						<a href="javascript:move_InputPattern('${入力パターン.id}','','');">④${入力パターン.入力パターン名}編集へ</a>
					</td>
					<td>
						${入力パターン.実行回数}
					</td>
					<td>
						${入力パターン.job状況}
						<br>
						<a href="javascript:move_TestCaseAdmin('moveResult','${dto.テストケース管理.id}','${入力パターン.id}')">エラー</a>
					</td>
					<td>
						<a href="javascript:move_TestCaseAdmin('pictureCorrect','${dto.テストケース管理.id}','${入力パターン.id}')">
						<img src="data:image/jpg;base64,${入力パターン.画面正解}" width=200 height=70 alt="1回目画像" />
						</a>
					</td>
					<td>
						<a href="javascript:move_TestCaseAdmin('pictureNow','${dto.テストケース管理.id}','${入力パターン.id}')">
						<img src="data:image/jpg;base64,${入力パターン.画面}" width=200 height=70 alt="今回画像" />
						</a>
					</td>
					<td>
						<a href="javascript:move_TestCaseAdmin('htmlCorrect','${dto.テストケース管理.id}','${入力パターン.id}')">正解値</a>
						<br>
						<a href="javascript:move_TestCaseAdmin('htmlResult','${dto.テストケース管理.id}','${入力パターン.id}')">実行結果</a>
						<br>
						<a href="javascript:move_TestCaseAdmin('htmlDiff','${dto.テストケース管理.id}','${入力パターン.id}')">差異</a>
					</td>
					<td>
						<a href="javascript:move_TestCaseAdmin('dbCorrect','${dto.テストケース管理.id}','${入力パターン.id}')">正解値</a>
						<br>
						<a href="javascript:move_TestCaseAdmin('dbResult','${dto.テストケース管理.id}','${入力パターン.id}')">実行結果</a>
						<br>
						<a href="javascript:move_TestCaseAdmin('dbDiff','${dto.テストケース管理.id}','${入力パターン.id}')">差異</a>
					</td>
					<td>
						${入力パターン.判定結果}
					</td>
				</tr>
				</c:forEach>
			</table>
			<br>
			<a href="${dto.ジェンキンスURL}">JOBの確認</a>
		</div>
	</div>
</body>
</html>