$('#payButton').popup({
    popup: $('.payQR.popup'),
    on: 'click',
    position: 'bottom center'
});

$('.ui.form').form({
    fields: {
        title: {
            identifier: 'content',
            rules: [{
                type: 'empty',
                prompt: '请输入评论内容'
            }]
        }
        , content: {
            identifier: 'nickname',
            rules: [{
                type: 'empty',
                prompt: '请输入昵称'
            }]
        }
        , type: {
            identifier: 'email',
            rules: [{
                type: 'email',
                prompt: '请输入正确的邮箱地址'
            }]
        }
    }
});

$('#comment-submit').click(function () {
    if ($('.ui.form').form('validate form')) {
        let data = {
            "articleId": $("[name='articleId']").val(),
            "parentId": $("[name='parentId']").val(),
            "nickname": $("[name='nickname']").val(),
            "email": $("[name='email']").val(),
            "content": $("[name='content']").val(),
            "captcha": $("[name='captcha']").val()
        };
        $.ajax({
            url: '/api/comment'
            , type: 'POST'
            , data: JSON.stringify(data)
            , dataType: 'text'
            , contentType: 'application/json'
            , success: function (response) {
                response = JSON.parse(response);
                alert(response.msg);
                if (response.status === 200) {
                    window.location.reload();
                }
            }
        })
    } else {
        window.alert("校验失败")
    }
});

function reply(obj) {
    let commentId = $(obj).data('id')
        , nickname = $(obj).data('nickname');

    $("[name='content']").attr('placeholder', "@" + nickname).focus();
    $("[name='parentId']").val(commentId);
    $.scrollTo('#comment-form', 500);
}

$("#captcha").bind("click", function () {
    $(this).hide().attr('src', '/api/captcha?random=' + Math.random()).fadeIn();
})
