$('#page').bind('keypress', function (event) {
    if (event.keyCode == '13') {
        let val = $('#page').val();
        const total = parseInt($('#total').text());
        if (val < 1) val = 1;
        else if (val > total) val = total;
        window.location.href = "/page?current=" + val;
    }
});