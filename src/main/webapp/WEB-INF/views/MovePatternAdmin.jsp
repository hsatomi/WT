<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="co.jp.souya.tool.TTConst" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>遷移パターン管理画面</title>
	<script src="script/ajax.js"></script>
	<script src="script/MovePatternAdmin.js"></script>
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
	<strong>スタブ機能です！実装はほとんどありません！</strong>
	<h3>テストツール - 遷移パターン管理画面
    <a href="javascript:history.back();">戻る</a>
    <a href="">再表示</a>
	</h3>
	<div id="input_parameter_pattern" style="float: left; border-style: solid; padding: 10px;">
		<div>
			遷移パターン一覧
			<br>
			<br>
			<input type="button" value="登録" onClick="doRegist()" />
			<br>
			<table class="borderList">
				<tr>
					<th>id</th>
					<th>画面管理id</th>
					<th>遷移パターン名</th>
					<th>備考</th>
				</tr>

				<c:forEach items="${dto}" var="遷移パターン" >
				<tr class="list">
					<td>
						<input type="text" value="${遷移パターン.id}" readonly="readonly" />
					</td>
					<td>
						<input type="text" value="${遷移パターン.画面管理id}" />
					</td>
					<td>
						<input type="text" value="${遷移パターン.遷移パターン名}" />
					</td>
					<td>
						<input type="text" value="${遷移パターン.備考}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>