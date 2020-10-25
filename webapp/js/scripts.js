$(document).ready(function () {/* jQuery toggle layout */
    $('#btnToggle').click(function () {
        if ($(this).hasClass('on')) {
            $('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
            $(this).removeClass('on');
        } else {
            $('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
            $(this).addClass('on');
        }
    });
    $('.answerWrite input[type=submit]').click(addAnswer);

    function addAnswer(e) {
        e.preventDefault();
        // form 데이터를 자동으로 묶어줌
        var queryString = $('form[name=answer]').serialize();

        $.ajax({
            type: 'post',
            url: '/api/qna/addAnswer',
            data: queryString,
            dataType: 'json',
            error: onError,
            success: onSuccess,
        });
    }

    function onSuccess(json, status) {
        var answerTemplate = $('#answerTemplate').html();
        var template = answerTemplate.format(json.writer, new Date(json.createDate),
            json.contents, json.answerId);

        $('.qna-comment-slipp-articles').prepend(template);
    }

    function onError(json, status) {
        console.log('ERROR!!');
    }

    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function(match, number) {
            return typeof args[number] != 'undefined'
                ? args[number]
                : match
                ;
        });
    }
});