$("#localsearch").bind('keypress', function (event) {
    if (event.keyCode === '13') {
        let val = $("#localsearch").val();
        if (val === "") {
            return;
        }
        window.location.href = "/search?keyword=" + val;
    }
});

$("#localsearchbutton").click(function () {
    let val = $("#localsearch").val();
    if (val === "") {
        return;
    }
    window.location.href = "/search?keyword=" + val;
});