var id_ldzc,id_ysjd,id_dbfc,id_ggtz,id_gzdt,id_jdgz,id_rsrm,id_rdxx;

(function () {
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
})();

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

    $("#nav_ldzc").attr("href","/list.html?coId="+id_ldzc);
    $("#nav_ysjd").attr("href","/list.html?coId="+id_ysjd);
    $("#nav_dbfc").attr("href","/list.html?coId="+id_dbfc);
    $("#nav_ggtz").attr("href","/list.html?coId="+id_ggtz);
    $("#nav_gzdt").attr("href","/list.html?coId="+id_gzdt);
    $("#nav_jdgz").attr("href","/list.html?coId="+id_jdgz);
    $("#nav_rsrm").attr("href","/list.html?coId="+id_rsrm);

    $("#fnav_ldzc").attr("href","/list.html?coId="+id_ldzc);
    $("#fnav_ysjd").attr("href","/list.html?coId="+id_ysjd);
    $("#fnav_dbfc").attr("href","/list.html?coId="+id_dbfc);
    $("#fnav_ggtz").attr("href","/list.html?coId="+id_ggtz);
    $("#fnav_gzdt").attr("href","/list.html?coId="+id_gzdt);
    $("#fnav_jdgz").attr("href","/list.html?coId="+id_jdgz);
    $("#fnav_rsrm").attr("href","/list.html?coId="+id_rsrm);
   
}