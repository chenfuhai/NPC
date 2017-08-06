

$(function () {
    var id_ldzc,id_ysjd,id_dbfc,id_ggtz,id_gzdt,id_jdgz,id_rsrm,id_tpxw,id_zhyw;
    //根据栏目名称获取栏目ID
    //根据ID获取前N页文章
    //循环加入到目标div中
    //每个文章有其对应的文章ID参数在元素中
    initAll();


    function initAll() {
        $.ajax({
            url:"/admin/cols",
            dataType:"json",
            type:"get",
            success:function (result) {
                var cols = result.extend.cols;
                initData(cols);
                fillLdzc();
                fillYsjd();
                fillDbfc();
                fillGgtz();
                fillGzdt();
                fillRsrm();
                fillZhyw();
                fillTpxw();
            },
            error:function (result) {
                alert("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
                console.log("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
            }


        });




    }

    function initData(cols) {
        var col;
        for (var i=0;i<cols.length;i++){
            col = cols[i];
            switch (col.coName){
                case '领导致词':
                    id_ldzc = col.coId;
                    break;
                case '议事决定':
                    id_ysjd = col.coId;
                    break;
                case '代表风采':
                    id_dbfc = col.coId;
                    break;
                case '公告通知':
                    id_ggtz = col.coId;
                    break;
                case '工作动态':
                    id_gzdt = col.coId;
                    break;
                case '监督工作':
                    id_jdgz = col.coId;
                    break;
                case '人事任免':
                    id_rsrm = col.coId;
                    break;
                case '综合要闻':
                    id_zhyw = col.coId;
                    break;
                case '图片新闻':
                    id_tpxw = col.coId;
                    break;
            }

        }

    }


    function fillLdzc() {
        $.ajax({
            url:"/admin/arts/"+id_ldzc,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 <a id="more_ggzz"
                 href="#">-更多-</a>
                 */
                var ul = $("#ul_ldzc");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publicTime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });
                $("#more_ldzc").attr("href","/list.html?coId="+id_ldzc).attr("target","_blank");
            }
            
            
        });
    }

    function fillYsjd() {
        $.ajax({
            url:"/admin/arts/"+id_ysjd,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 */
                var ul = $("#ul_ysjd");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publictime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });
                $("#more_ysjd").attr("href","/list.html?coId="+id_ysjd).attr("target","_blank");
            }


        });
    }

    function fillDbfc() {
        $.ajax({
            url:"/admin/arts/"+id_dbfc,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 */
                var ul = $("#ul_dbfc");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publictime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });
                $("#more_dbfc").attr("href","/list.html?coId="+id_dbfc).attr("target","_blank");
            }


        });
    }

    function fillGgtz() {
        $.ajax({
            url:"/admin/arts/"+id_ggtz,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 */
                var ul = $("#ul_ggzz");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publictime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });
                $("#more_ggzz").attr("href","/list.html?coId="+id_ggtz).attr("target","_blank");
            }


        });
    }

    function fillGzdt() {
        $.ajax({
            url:"/admin/arts/"+id_gzdt,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 */
                var ul = $("#ul_gzdt");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publictime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });
                $("#more_gzdt").attr("href","/list.html?coId="+id_gzdt).attr("target","_blank");
            }


        });
    }

    function fillJdgz() {
        $.ajax({
            url:"/admin/arts/"+id_jdgz,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 */
                var ul = $("#ul_jdgz");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publictime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });

            }


        });
    }

    function fillRsrm() {
        $.ajax({
            url:"/admin/arts/"+id_rsrm,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 */
                var ul = $("#ul_rsrm");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publictime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });
                $("#more_rsrm").attr("href","/list.html?coId="+id_rsrm).attr("target","_blank");
            }


        });
    }

    function fillZhyw() {
        $.ajax({
            url:"/admin/arts/"+id_zhyw,
            data:"pagesize=5",
            dataType:"json",
            type:"get",
            success:function (result) {
                var arts = result.Rows;
                /**
                 *  <ul id="ul_ldzc">
                 <li><span class="title">
                 <a href="#" target="_self" title="党建须把执政能力和先进性建设作主线" style="">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端 矛头直指</a></span>
                 <span class="date">05-19</span>
                 <span class="clear"></span>
                 </li>

                 </ul>
                 */
                var ul = $("#ul_zhyw");
                ul.empty();
                $.each(arts,function (index,art) {
                    var e_li = $("<li></li>");
                    var e_spanTitle = $("<span></span>");
                    var e_spanDate = $("<span></span>");
                    var e_spanClear = $("<span></span>");

                    var e_a = $("<a></a>").append(art.title)
                        .attr("href","/content.html?id="+art.id).attr("target","_blank").attr("title",art.title);

                    e_spanTitle.append(e_a).addClass("title");
                    e_spanDate.append(art.publictime.substring(5,10)).addClass("date");
                    e_spanClear.addClass("clear");
                    e_li.append(e_spanTitle).append(e_spanDate).append(e_spanClear);
                    ul.append(e_li);
                });
                $("#more_zhyw").attr("href","/list.html?coId="+id_zhyw).attr("target","_blank");
            }


        });
    }

    function fillTpxw() {
        $.ajax({
            url: "/admin/arts/img/" + id_tpxw,
            type: 'get',
            dataType: "json",
            success: function (result) {
                if (result.code === 100) {
                    var data = result.extend.data;
                    var jsonData = [];
                    $.each(data, function (i, d) {

                        jsonData.push($.parseJSON('{"href":"/content.html?id=' + d.id + '","src":"' + d.src + '","title":"' + d.title + '"}'));
                    });

                    $("#ul_tpxw").empty();
                    $("#ul_tpxw").yx_rotaion({data: jsonData});
                } else {
                    alert(result.msg);
                }
            }
        });

    }


});
