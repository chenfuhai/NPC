var id_ldzc,id_ysjd,id_dbfc,id_ggtz,id_gzdt,id_jdgz,id_rsrm,id_rdxx,coId;
$(function () {
    //获取地址栏的栏目ID
    //根据ID获取去栏目的所有文章
    //获取对应的栏目 并加入到左边的栏目表中
    //循环加入到目标div中
    //每个文章有其对应的文章ID参数在元素中
    initLNav();
    coId = getPar("coId");
    if (coId===null || coId===""){
        return;
    }
    fillPosition(coId);
    gotoPageN(1);


});



function gotoPageN(n) {
    var arts = null;

    //获取栏目的全部文章和分页信息
    $.ajax({
        url:"/admin/arts/"+coId,
        type:"get",
        data:"page="+n+"&pagesize=10",
        success:function (result) {
            //解析文章信息
            var arts =result.Rows;
            var pageInfo = result.pageInfo;
            fillList(arts);
            fillPageNav(pageInfo);
        },
        error:function (result) {
            alert("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
            console.log("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
        }
    });
    
    


}

function fillList(arts){
    /**
     * <ul id="ul_list">
         <li><a href="#"><span>2011-1-29</span>发票查询--方便快捷，真伪立见 <b>[图]</b></a></li>
     </ul>
     */

    var listContent = $("#ul_list");
    listContent.empty();
    $.each(arts,function (index,item) {
        var e_li=$("<li></li>");
        var e_a = $("<a></a>").attr("href","/content.html?id="+item.id).attr("target","_blank");
        var e_spanTime=$("<span></span>").append(item.publicTime);
        e_a.append(e_spanTime).append(item.title);
        e_li.append(e_a);
        listContent.append(e_li);
    })
}
function fillPageNav(pageInfo) {

    /**
     <a title="Total record"><b>29</b></a>
     <b>1</b>
     <a href="/download/index_2.html">2</a>
     <a href="/download/index_2.html">&gt;</a>
     <a href="/download/index_2.html">&gt;&gt;</a>
     */
    var pageInfoDiv = $("#pageInfo");
    pageInfoDiv.empty();

    var e_spanTotal = $("<span></span>");
    e_spanTotal.append("记录总数："+"<b>"+pageInfo.total+"</b>");
    //<a title="Total record"><b>29</b></a>
    var e_spanPages = $("<span></span>").append("总页数：<b>"+pageInfo.pages+"</b>");
    var e_aFirst=$("<a></a>").append("首页").attr("href","#");
    var e_aPre=$("<a></a>").append("&laquo;");
    if (pageInfo.hasPreviousPage === false) {
        e_aFirst.addClass("disabled");
        e_aPre.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        e_aFirst.click(function() {
            gotoPageN(1);
        });
        e_aPre.click(function() {
            gotoPageN(pageInfo.pageNum - 1);
        });
    }

    pageInfoDiv.append(e_spanTotal).append(e_spanPages).append(e_aFirst).append(e_aPre);

    var navNums = pageInfo.navigatepageNums;
    $.each(navNums,function (index,item) {
        var navNumA;
        if (item === pageInfo.pageNum){
            navNumA = $("<b></b>").append("<b>"+pageInfo.pageNum+"</b>");
        }else{
            navNumA = $("<a></a>").append(item).attr("href", "#");
            navNumA.click(function () {
                gotoPageN(item);
            });
        }
        pageInfoDiv.append(navNumA);

    });

    var e_aNext=$("<a></a>").append("&raquo;");
    var e_aLast=$("<a></a>").append("末页").attr("href", "#");

    if (pageInfo.hasNextPage === false) {
        e_aNext.addClass("disabled");
        e_aLast.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        e_aNext.click(function() {
            gotoPageN(pageInfo.pageNum + 1);
        });
        e_aLast.click(function() {
            gotoPageN(pageInfo.pages);
        });
    }

    pageInfoDiv.append(e_aNext).append(e_aLast);

}


function fillPosition(col_id){

    $.ajax({
        url:"/admin/col/"+col_id,
        dataType:"json",
        type:"get",
        success:function (result) {
            var col = result.extend.col;
            var colName = col.coName;
            document.title = colName;
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

function initLNav() {
    $.ajax({
        url:"/admin/cols",
        dataType:"json",
        type:"get",
        success:function (result) {
            var cols = result.extend.cols;
            fillAll(cols);
        },
        error:function (result) {
            alert("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
            console.log("发生错误 "+"状态码"+result.status+"错误信息"+result.statusText);
        }


    });
}

function fillAll(cols) {

    $.each(cols,function (i,col) {
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
        }
    });

  

    $("#lnav_ldzc").attr("href","/list.html?coId="+id_ldzc);
    $("#lnav_ysjd").attr("href","/list.html?coId="+id_ysjd);
    $("#lnav_dbfc").attr("href","/list.html?coId="+id_dbfc);
    $("#lnav_ggtz").attr("href","/list.html?coId="+id_ggtz);
    $("#lnav_gzdt").attr("href","/list.html?coId="+id_gzdt);
    $("#lnav_jdgz").attr("href","/list.html?coId="+id_jdgz);
    $("#lnav_rsrm").attr("href","/list.html?coId="+id_rsrm);

}