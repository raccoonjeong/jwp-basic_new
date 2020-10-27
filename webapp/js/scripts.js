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
    $(".qna-comment").on("click", ".form-delete", deleteAnswer);

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

    function deleteAnswer(e) {
        e.preventDefault();
        // form 데이터를 자동으로 묶어줌
        var deleteBtn = $(this);
        var queryString = deleteBtn.closest("form").serialize();

        $.ajax({
            type: 'post',
            url: '/api/qna/deleteAnswer',
            data: queryString,
            dataType: 'json',
            error: function (xhr, status) {
                alert("error");
            },
            success: function (json, status) {
                if (json.status) {
                    deleteBtn.closest('article').remove();
                }
            }
        });
    }

    function onSuccess(json, status) {
        var answerTemplate = $('#answerTemplate').html();
        console.log(json);
        var template = answerTemplate.format(json.writer, getFormatDate(new Date(json.createdDate)),
            json.contents, json.answerId);

        $('.answerWrite').prepend(template);
    }

    function getFormatDate(date){
        var year = date.getFullYear();
        var month = (1 + date.getMonth());
        month = month >= 10 ? month : '0' + month;
        var day = date.getDate();
        day = day >= 10 ? day : '0' + day;
        var hours = date.getHours();
        var minutes = date.getMinutes();
        return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
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