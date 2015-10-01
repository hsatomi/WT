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
    <input type="text" id="_id" value="${dto.入力パターン.id}" />
    <input type="text" id="_テストケース管理id" value="${dto.入力パターン.テストケース管理id}" />
    </div>
	<div id="move_pattern_information" style="float: left; border-style: solid; margin-right: 20px;">
		<div>
			パラメタ情報
			<div style="margin:10px; border-style:solid;">
				<h4>No</h4>
				<input type="text" id="_no"
					value="${dto.入力パターン.no}" />
			</div>
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
					<th>実行順※</th>
					<th>項目名</th>
					<th>エレメント型※</th>
					<th>エレメント名※</th>
					<th>アクション※</th>
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
						<select name="_エレメント型">
						<option value="" <c:if test="${パラメタ値.エレメント型==''}">selected</c:if>></option>
						<option value="By.name" <c:if test="${パラメタ値.エレメント型=='By.name'}">selected</c:if>>By.name</option>
						<option value="By.id" <c:if test="${パラメタ値.エレメント型=='By.id'}">selected</c:if>>By.id</option>
						<option value="By.className" <c:if test="${パラメタ値.エレメント型=='By.className'}">selected</c:if>>By.className</option>
						<option value="By.linkText" <c:if test="${パラメタ値.エレメント型=='By.linkText'}">selected</c:if>>By.linkText</option>
						<option value="By.cssSelector" <c:if test="${パラメタ値.エレメント型=='By.cssSelector'}">selected</c:if>>By.cssSelector</option>
						<option value="別ウィンドウへ移動" <c:if test="${パラメタ値.エレメント型=='別ウィンドウへ移動'}">selected</c:if>>別ウィンドウへ移動</option>
						<option value="クリックアラートOK" <c:if test="${パラメタ値.エレメント型=='クリックアラートOK'}">selected</c:if>>クリックアラートOK</option>
						<option value="クリックアラートNG" <c:if test="${パラメタ値.エレメント型=='クリックアラートNG'}">selected</c:if>>クリックアラートNG</option>
						</select>
					</td>
					<td>
						<input type="text" value="${パラメタ値.エレメント名}" />
					</td>
					<td>
						<select name="_アクション">
						<option value="" <c:if test="${パラメタ値.アクション==''}">selected</c:if>></option>
						<option value="sendKeys" <c:if test="${パラメタ値.アクション=='sendKeys'}">selected</c:if>>sendKeys</option>
						<option value="click" <c:if test="${パラメタ値.アクション=='click'}">selected</c:if>>click</option>
						<option value="selectByIndex" <c:if test="${パラメタ値.アクション=='selectByIndex'}">selected</c:if>>selectByIndex</option>
						<option value="selectByValue" <c:if test="${パラメタ値.アクション=='selectByValue'}">selected</c:if>>selectByValue</option>
						<option value="clickRadio" <c:if test="${パラメタ値.アクション=='clickRadio'}">selected</c:if>>clickRadio</option>
						<option value="clickByAttrValue" <c:if test="${パラメタ値.アクション=='clickByAttrValue'}">selected</c:if>>clickByAttrValue</option>
						</select>
					</td>
					<td>
						<select name="_型">
						<option value="" <c:if test="${パラメタ値.型==''}">selected</c:if>></option>
						<option value="文字列" <c:if test="${パラメタ値.型=='文字列'}">selected</c:if>>文字列</option>
						<option value="数値" <c:if test="${パラメタ値.型=='数値'}">selected</c:if>>数値</option>
						</select>
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
	<div>
		<textarea id="selenium_code" rows="20" cols="100" ></textarea>
		<input type="button" value="SeleniumIDE解析" onClick="doAnalyze()" />
	</div>

</body>
</html>