(function ($, w) {

    var createDom = function (title, message, cls, timeout) {
        var $alert =
            '<div class="alert toast  ' + cls + '" role="alert">' +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
            '<span aria-hidden="true">&times;</span>' +
            '</button>' +
            '<strong class="toast-title">' + title + '</strong>' +
            '<span class="toast-content">' + message + '</span>'
        '</div>';
        $(document.querySelector('body')).append($alert);

        clearToast(timeout);
        return $($alert);
    };

    var clearToast = function (timeout) {
        setTimeout(function () {
            $('.toast').remove();
        }, timeout);
    };

    var toast = {
        success: function (msg, timeout) {
            if (!timeout)
                timeout = 4000;
            createDom('恭喜: ', msg, 'alert-success', timeout);
        },
        error: function (msg, timeout) {
            if (!timeout)
                timeout = 4000;
            createDom('错误: ', msg, 'alert-error', timeout);
        },
        info: function (msg, timeout) {
            if (!timeout)
                timeout = 4000;
            createDom('提示: ', msg, 'alert-info', timeout);
        },
        warning: function (msg, timeout) {
            if (!timeout)
                timeout = 4000;
            createDom('警告: ', msg, 'alert-warning', timeout);
        }
    };
    w.toast = toast;
})(jQuery, window);