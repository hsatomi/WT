<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>テストケース管理画面</title>

<script src="script/TestCaseAdmin.js"></script>
<link href="css/stylesheet.css" rel="stylesheet" />
</head>
<body>
	<h3>テストツール - テストケース管理画面</h3>
	<div id="move_pattern_information" style="float: left; border-style: solid; margin-right: 20px;">
		<div>
			遷移パターン情報
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
				<c:forEach items="${dto.遷移パターン明細リスト}" var="遷移パターン明細" >
				<div style="width:90%;border-style: solid; margin:10px; ">
					${遷移パターン明細.画面タイトル}
					<br/>
					${遷移パターン明細.url}
				</div>
				</c:forEach>
			</div>
			<div style="margin:10px; border-style:solid;">
				<h4>インプット情報</h4>
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
			<div style="margin:10px; border-style:solid;">
				<h4>アウトプット情報</h4>
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
		<div>
			パラメータパターン一覧
			<br>
			<br>
			<button>生成</button>
			<button>実行</button>
			<button>全て選択</button>
			<button>NGパターン選択</button>
			<button>全て解除</button>
			<button>パターン自動生成</button>
			<button>遷移パターン削除</button>
			<br>
			<table class="borderList">
				<tr>
					<th>No</th>
					<th>入力パターン名</th>
					<th>JOB状況</th>
					<th>遷移結果</th>
					<th>HTML</th>
					<th>DB</th>
					<th>判定結果</th>
					<th>前回との差異</th>
				</tr>

				<c:forEach items="${dto.入力パターンリスト}" var="入力パターン" >
				<tr>
					<td>
						${入力パターン.no}
						<input type="checkbox">
					</td>
					<td>
						<a href="javascript:move_InputParameters('${入力パターン.id}');">${入力パターン.入力パターン名}</a>
					</td>
					<td>
						${入力パターン.job状況}
					</td>
					<td>
						${入力パターン.遷移結果}
					</td>
					<td>
						<a href="${入力パターン.html}">[確認]</a>
					</td>
					<td>
						<a href="${入力パターン.db}">[確認]</a>
					</td>
					<td>
						${入力パターン.判定結果}
					</td>
					<td>
						${入力パターン.前回との結果一致状況}
					</td>
				</tr>
				</c:forEach>

			</table>
		</div>
	</div>


</body>
</html>