<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="co.jp.souya.tool.TTConst" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>テストツール</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="script/Common.js"></script>
	<link href="css/stylesheet.css" rel="stylesheet" />
</head>
<script>
var URL_API_BASE = "<%= TTConst.URL_API_BASE %>";
var URL_UPDATE_SESSION_URLGO = "<%= TTConst.URL_API_BASE+TTConst.URL_UPDATE_SESSION_URLGO %>";
var URL_UPDATE_SESSION_URLBACK = "<%= TTConst.URL_API_BASE+TTConst.URL_UPDATE_SESSION_URLBACK %>";
var URL_RESET = "<%= TTConst.URL_API_BASE+TTConst.URL_RESET_TESTCASE %>";
var URL_GENERATE = "<%= TTConst.URL_API_BASE+TTConst.URL_GENERATE_TESTCASE %>";
var URL_UNGENERATE = "<%= TTConst.URL_API_BASE+TTConst.URL_DELETE_TESTCASE %>";
var URL_EXECJENKINS = "<%= TTConst.URL_API_BASE+TTConst.URL_EXECJENKINS %>";
var URL_POLLING = "<%= TTConst.URL_API_BASE+TTConst.URL_POLLINGJENKINS %>";
var URL_ANALYZE = "<%= TTConst.URL_API_BASE+TTConst.URL_ANALYZE %>";
</script>

<body onload="javascript:url_push();">
<h1>
	テストツールへようこそ！
</h1>

<P>
${dto}
</P>
<P>
<a href="<%= TTConst.URL_API_BASE %>/MovePatternAdmin">遷移パターンへ</a>
</P>
</body>
</html>
