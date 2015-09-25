<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="co.jp.souya.tool.TTConst" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>入力パラメータ画面</title>
	<script src="script/ajax.js"></script>
    <script src="script/InputParameters.js"></script>
    <link href="css/stylesheet.css" rel="stylesheet" />
<script>
var URL_API_BASE = "<%= TTConst.URL_API_BASE %>";
var URL_RESET = "<%= TTConst.URL_API_BASE+TTConst.URL_RESET_TESTCASE %>";
var URL_GENERATE = "<%= TTConst.URL_API_BASE+TTConst.URL_GENERATE_TESTCASE %>";
var URL_EXECJENKINS = "<%= TTConst.URL_API_BASE+TTConst.URL_EXECJENKINS %>";
var URL_POLLING = "<%= TTConst.URL_API_BASE+TTConst.URL_POLLINGJENKINS %>";
</script>
</head>
<body>
    <h3>テストツール - 入力パラメータ画面</h3>
    <div id="div_hidden" style="display:none">
    </div>
    <div>
    <input type="text" id="_id" value="${dto.入力パターン.id}" />
    <input type="text" id="_テストケース管理id" value="${dto.入力パターン.テストケース管理id}" />
    </div>
	<div id="move_pattern_information" style="float: left; border-style: solid; margin-right: 20px;">
		<div>
			パラメタ情報
			<div style="margin:10px; border-style:solid;">
				<h4>パターン名</h4>
				<input type="text" id="_入力パターン名"
					value="${dto.入力パターン.入力パターン名}" />
			</div>
			<div style="margin:10px; border-style:solid;">
				<h4>備考</h4>
				<input type="text" id="_備考"
					value="${dto.入力パターン.備考}" />
			</div>

		</div>
	</div>
	<div id="input_parameter_pattern" style="float: left; border-style: solid; padding: 10px;">
 		<div>
			入力パラメータ一覧
			<br>
			<br>
			<input type="button" value="登録" onClick="doRegist()" />
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
				<tr class="list">
					<td>
						<input type="text" value="${パラメタ値.実行順}" />
					</td>
					<td>
						<input type="text" value="${パラメタ値.項目名}" />
					</td>
					<td>
						<input type="text" value="${パラメタ値.エレメント型}" />
					</td>
					<td>
						<input type="text" value="${パラメタ値.エレメント名}" />
					</td>
					<td>
						<input type="text" value="${パラメタ値.アクション}" />
					</td>
					<td>
						<input type="text" value="${パラメタ値.型}" />
					</td>
					<td>
						<input type="text" value="${パラメタ値.値}" />
					</td>
					<td>
						<input type="text" value="${パラメタ値.備考}" />
					</td>
					<td style="display:none">
						<input type="text" value="${パラメタ値.id}" />
					</td>
				</tr>
				</c:forEach>

			</table>
		</div>
    </div>

</body>
</html>