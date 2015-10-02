<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="co.jp.souya.tool.TTConst" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>遷移パターン管理画面</title>
	<script src="script/ajax.js"></script>
	<script src="script/MovePatternDetail.js"></script>
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
	<h3>テストツール - 遷移パターン明細画面</h3>
	<div id="input_parameter_pattern" style="float: left; border-style: solid; padding: 10px;">
		<div>
			遷移パターン明細一覧
			<br>
			<br>
			<input type="button" value="登録" onClick="doRegist()" />
			<input type="button" value="明細追加" onClick="doAdd('${dto.遷移パターン管理.id}')" />
			<br>
			<table class="borderList">
				<tr>
					<th>id</th>
					<th>url</th>
					<th>画面タイトル</th>
					<th>遷移パターン管理id</th>
					<th>遷移順</th>
					<th>入力パターンid</th>
					<th>明細削除</th>
				</tr>

				<c:forEach items="${dto.遷移パターン明細リスト}" var="遷移パターン明細" >
				<tr class="list">
					<td>
						<input type="text" value="${遷移パターン明細.id}" readonly="readonly" />
					</td>
					<td>
						<input type="text" value="${遷移パターン明細.url}" />
					</td>
					<td>
						<input type="text" value="${遷移パターン明細.画面タイトル}" />
					</td>
					<td>
						<input type="text" value="${遷移パターン明細.遷移パターン管理id}" readonly="readonly" />
					</td>
					<td>
						<input type="text" value="${遷移パターン明細.遷移順}" />
					</td>
					<td>
						<input type="text" value="${遷移パターン明細.入力パターンid}" readonly="readonly" />
					</td>
					<td>
						<input type="button" value="削除" onClick="doDelete('${遷移パターン明細.id}')" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>