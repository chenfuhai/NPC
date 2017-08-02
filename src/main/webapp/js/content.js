(function () {
    //获取地址栏文章ID
    //根据ID获取文章信息
    //更改页面标题，更改时间，内容等
    var id = getPar("id");
    if (id!==null ||id!==""){
        //获取文章ID的详细
        $.ajax({
            url:"/admin/art/"+id,
            type:"get",
            success:function (result) {
                var art = result.extend.art;
                fill(art);
                addClickCount(art);
            },
            error:function (result) {
                alert("发生错误，请联系管理员： "+"状态码"+result.status+"错误信息"+result.statusText+"服务器返回信息"+result.responseText);
                console.log("发生错误，请联系管理员： "+"状态码"+result.status+"错误信息"+result.statusText+"服务器返回信息"+result.responseText);
            }
        });

    }
})();
function addClickCount(art) {
    //访问量增加1
    $.ajax({
        url:"/admin/art/"+art.arId,
        type:'put',
        data:'arClickCount='+(art.arClickCount+1),
        dataType:"json",
        success:function (result) {

        },
        error:function (result) {
            alert("发生错误，请联系管理员： "+"状态码"+result.status+"错误信息"+result.statusText+"服务器返回信息"+result.responseText);
            console.log("发生错误，请联系管理员： "+"状态码"+result.status+"错误信息"+result.statusText+"服务器返回信息"+result.responseText);
        }


    });
}

function fill(art) {
    $("#ar_title").empty().append(art.arTitle);
    $("#ar_subTitle").empty().append(art.arSubtitle);
    $("#ar_time_from_count").empty().append("发布时间："+art.arPublictime+"\t来源："+art.arFrom+"\t阅读:"+art.arClickCount+"次");
    $("#zoom").empty().append(art.arContent);

}

function getPar(par) {
    var localUrl = document.location.href;
    var get = localUrl.indexOf(par+"=");
    if(get === -1){
        return false;
    }

    var get_par  = localUrl.slice(par.length+get+1);
    return get_par;
}


