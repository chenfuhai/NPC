(function () {
    //获取地址栏文章ID
    //根据ID获取文章信息
    //更改页面标题，更改时间，内容等
    var id = getPar("id");
    if (id!==null && !id!==""){
        //获取文章ID的详细
        $.ajax({
            url:"/admin/art/"+id,
            type:"get",
            success:function (result) {
                var art = result.extend.art;
                var da = new Date(art.arPublictime);
                var year = da.getFullYear();
                var mouth = da.getMonth()+1;
                var day = da.getDate();
                var hour = da.getHours();
                var min = da.getMinutes();

                var dateString = year+"-"+(mouth<10?"0"+mouth:mouth)+"-"+(day<10?"0"+day:day)
                    +" "+(hour<10?"0"+hour:hour)+":"+(min<10?"0"+min:min);
                art.arPublictime = dateString;
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






    function addClickCount(art) {
        //访问量增加1
        $.ajax({
            url:"/admin/art/addCount/"+art.arId,
            type:'get',
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
        document.title = art.arTitle;

        $("#art_title").empty().append(art.arTitle);
        $("#art_subtitle").empty().append(art.arSubtitle);
        $("#art_time").empty().append("发布时间："+art.arPublictime);
        $("#art_from").empty().append("来源："+art.arFrom);
        $("#art_count").empty().append("点击："+art.arClickCount+"次");


        $(document.getElementById("art_content").contentWindow.document.body).html(art.arContent);

        $("#art_content").height(document.getElementById("art_content").contentWindow.document.documentElement.clientHeight);
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
                var e_aCol = $("<a></a>").append(colName);
                e_aCol.attr("href","/list.html?coId="+col_id);
                var e_aHome = $("<a></a>").append("东乡人大网");
                e_aHome.attr("href","/index.html");

                var e_span = $("<span></span>").append("您现在的位置:").append(e_aHome).append(">>").append(e_aCol);
                $("#col_name").empty().append(e_span).append(colName);
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
        return get_par.trim();
    }



})();
