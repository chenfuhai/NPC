
//
function getQueryStringByName(name) {
    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}
//
function initLayout() {
    var h = document.documentElement.clientHeight;
    var w = document.documentElement.clientWidth;

    $(".l-window-mask").height(h);
    $(".l-window-mask").width(w);

    var dialogwidth = $(".l-dialog").width();
    var dialogheight = $(".l-dialog").height();

    var offsettop = (h * 0.5 - dialogheight * 0.5) > 0 ? (h * 0.5 - dialogheight * 0.5) : 0;
    var offsetleft = (w * 0.5 - dialogwidth * 0.5) > 5 ? (w * 0.5 - dialogwidth * 0.5) : 5;
    $(".l-dialog").css({ 'top': offsettop, 'left': offsetleft });
}