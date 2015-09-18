<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>入力パラメータ画面</title>

    <script src="script/InputParameters.js"></script>
    <link href="css/stylesheet.css" rel="stylesheet" />
</head>
<body>
    <h3>テストツール - 入力パラメータ画面</h3>
	<div id="move_pattern_information" style="float: left; border-style: solid; margin-right: 20px;">
		<div>
			パラメタ情報
			<div style="margin:10px; border-style:solid;">
				<h4>パターン名</h4>
				<div style="margin:0 auto;">
					${dto.入力パターン.入力パターン名}
				</div>
			</div>
			<div style="margin:10px; border-style:solid;">
				<h4>備考</h4>
				<div style="margin:0 auto;">
					${dto.入力パターン.備考}
				</div>
			</div>

		</div>
	</div>
	<div id="input_parameter_pattern" style="float: left; border-style: solid; padding: 10px;">
 		<div>
			入力パラメータ一覧
			<br>
			<br>
			<button>登録</button>
			<br>
			<table class="borderList">
				<tr>
					<th>実行順</th>
					<th>項目名</th>
					<th>エレメント型</th>
					<th>エレメント名</th>
					<th>アクション</th>
					<th>型</th>
					<th>値</th>
					<th>備考</th>
				</tr>

				<c:forEach items="${dto.パラメタ値リスト}" var="パラメタ値" >
				<tr>
					<td>
						${パラメタ値.実行順}
					</td>
					<td>
						${パラメタ値.項目名}
					</td>
					<td>
						${パラメタ値.エレメント型}
					</td>
					<td>
						${パラメタ値.エレメント名}
					</td>
					<td>
						${パラメタ値.アクション}
					</td>
					<td>
						${パラメタ値.型}
					</td>
					<td>
						${パラメタ値.値}
					</td>
					<td>
						${パラメタ値.備考}
					</td>
				</tr>
				</c:forEach>

			</table>
		</div>
    </div>

    <br />
</body>
</html>