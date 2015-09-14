window.onload = function () {
    // ページ読み込み時に実行したい処理
    document.getElementById("statistics_information").style.width = window.parent.screen.width * 0.4 + "px";
    document.getElementById("statistics_information").style.height = window.parent.screen.height * 0.8 + "px";

    document.getElementById("parameter_pattern").style.width = window.parent.screen.width * 0.5 + "px";
    document.getElementById("parameter_pattern").style.height = window.parent.screen.height * 0.8 + "px";
};

function move_InputParameters(id) {
	location.href = "InputParameters?id=" + id;
	return;

//    switch (id) {
//        case "1": location.href = "InputParameters?id=" + id; break;
//        default: break;
//    }
}

