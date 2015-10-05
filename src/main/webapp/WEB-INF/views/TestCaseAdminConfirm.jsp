<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="co.jp.souya.tool.TTConst" %>

<html>
<head>
</head>

<body>
<form>
<pre><code>${dto}</code></pre>

<img src="data:image/jpg;base64,${dto_image}" />

</form>
</body>
</html>


