<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>画面詳細</title>

    <script src="../script/function.js"></script>
    <link href="../css/stylesheet.css" rel="stylesheet" />
</head>
<body>
    <h3>テストツール</h3>
    <div>
        画面1  遷移パターン一覧画面
    </div>
    <div id="statistics_information" style="float:left;border-style:solid;margin-right:20px;">
        統計情報
    </div>
    <div id="transition_pattern_list" style="float:left;border-style:solid">
        <h4>遷移パターン一覧</h4>
        <button>新規作成</button>
        <table class="borderList">
            <tr>
                <th>No</th>
                <th>遷移パターン</th>
                <th>連番</th>
                <th>パターン数</th>
                <th>OK</th>
                <th>NG</th>
                <th>未確認</th>
                <th>備考</th>
                <th>DB</th>
            </tr>
            <tr>
                <!-- ※動的作成部分 -->
                <td>
                    1
                </td>
                <td>
                    <a href="javascript:move('transition_pattern');">パターンA</a>
                </td>
                <td>
                    1
                </td>
                <td>
                    50
                </td>
                <td>
                    33
                </td>
                <td>
                    7
                </td>
                <td>
                    10
                </td>
                <td>
                    test
                </td>
                <td>
                    ダンプ名
                </td>
            </tr>
        </table>
    </div>
</body>
</html>