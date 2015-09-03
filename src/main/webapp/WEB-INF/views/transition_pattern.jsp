<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>遷移パターン画面</title>

<script src="../script/transition_pattern.js"></script>
<link href="../css/stylesheet.css" rel="stylesheet" />
</head>
<body>
	<h3>テストツール</h3>
	<div>遷移パターン1 遷移パターン画面</div>
	<div id="parameter_pattern_information" style="float: left; border-style: solid; margin-right: 20px;">
		<div>
			遷移パターン情報
			<div style="border-style: solid; margin:10px;">
				<h4>画面遷移パターン名</h4>
				<p>
					遷移パターン1
				</p>
			</div>
			<div style="border-style: solid; margin:10px;">
				<h4>備考:</h4>
				<p>
					パターン1, 2 と別にテストが必要な理由、何をテストしたいかなど。
					
				</p>
			</div>
			<div style="margin:10px; border-style:solid;">
				<h4>インプット情報</h4>
				<div style="margin:0 auto;">
					<SELECT>
						<OPTION>test</OPTION>
					</SELECT>
				</div>
				<div style="margin:0 auto;">
					更新するテーブル
				</div>
				<div>
					<input type="file" name="example" size="30">
				</div>
			</div>
			<div style="border-style:solid; margin:10px;">
				<h4>操作</h4>
				<button>パターン自動生成</button>
				<button>遷移パターン削除</button>
			</div>
			
			
			
		</div>
	</div>
	<div id="parameter_pattern" style="float: left; border-style: solid; padding: 10px;">
		<div>
			パラメータパターン一覧
			<button>実行</button>
			<button>全て選択</button>
			<button>NGパターン選択</button>
			<button>全て解除</button>
			自動生成したパラメータパターン(テストケース)
			<table class="borderList">
				<tr>
					<th>No</th>
					<th>パラメータパターン名</th>
					<th>遷移結果</th>
					<th>HTML</th>
					<th>DB</th>
					<th>判定結果</th>
					<th>前回との結果一覧</th>
				</tr>
				<tr>
					<td>
						1
						<input type="checkbox">
					</td>
					<td>
						<a href="javascript:move('parameter_pattern');">パターン1</a>
					</td>
					<td>
						同一
					</td>
					<td>
						<a href="javascript:move('parameter_pattern');">[確認]</a>
					</td>
					<td>
						<a href="javascript:move('parameter_pattern');">[確認]</a>
					</td>
					<td>
						未確認
					</td>
					<td>
						同じ
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>