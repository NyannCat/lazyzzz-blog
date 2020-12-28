$("#search").bind('keypress', function (event) {
    if (event.keyCode == '13') {
        let val = $("#search").val();
        if (val == "") {
            return;
        }
        window.location.href = "/search?keyword=" + val;
    }
});