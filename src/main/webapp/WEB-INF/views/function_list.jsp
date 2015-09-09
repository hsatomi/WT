<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>画面一覧画面</title>

    <script type="text/javascript"  src="${pageContext.request.contextPath}/src/main/webapp/script/function_list.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/main/webapp/css/stylesheet.css" />

</head>
<body>
    <h3>テストツール</h3>
    <div>プロジェクト1  画面一覧画面</div>

    <div id="statistics_information" style="float:left;border-style:solid;margin-right:20px;">
        統計情報
    </div>

    <div id="transition_pattern_list" style="float:left;border-style:solid">
        <button style="float:right;margin:10px 20px 10px 0;">DBダンプマスタ</button>
        <h4>WEBテスト画面一覧</h4>
        <table class="borderList">
            ※動的作成部分
            <tr>
                <th>機能</th>
            </tr>
            <tr>
                <td>
                    <button onclick="move('function')">機能1</button>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>