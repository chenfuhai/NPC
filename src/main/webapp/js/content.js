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
                fillPosition(art);
                addClickCount(art);
            },
            error:function (result) {
                alert("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
                console.log("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
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
            alert("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
            console.log("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
        }


    });
}

function fill(art) {
    $("#art_title").empty().append(art.arTitle);
    $("#art_subtitle").empty().append(art.arSubtitle);
    $("#art_time").empty().append("发布时间："+art.arPublictime);
    $("#art_from").empty().append("来源："+art.arFrom);
    $("#art_count").empty().append("点击："+art.arClickCount+"次");
    $("#art_content").empty().append(art.arContent);
}
function fillPosition(art){
    var col_id = art.arColumnid;
    $.ajax({
        url:"/admin/col/"+col_id,
        dataType:"json",
        type:"get",
        success:function (result) {
            var col = result.extend.col;
            var colName = col.coName;
            $("#col_name").empty().append("<span >您现在的位置: 东乡人大网>>"+colName+"</span >"+colName);
        },
        error:function (result) {
        alert("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
        console.log("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
    }


    });

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


