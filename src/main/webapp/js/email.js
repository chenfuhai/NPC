
$(function () {
    var coId;

  $.ajax({
      url:"/admin/cols",
      type:"get",
      dataType:"json",
      success:function (result) {
         var cols = result.extend.cols;
         for(var i = 0;i<cols.length;i++){
             if (cols[i].coName==='人大信箱'){
                 coId = cols[i].coId;
             }
         }
         $("#coId").val(coId);
      }

  });

    $("#btnSubmit").click(function () {
       $.ajax({
           url:"/admin/addMessage",
           data:$("#messageForm").serialize(),
           dataType:"json",
           type:"post",
           success:function (result) {
               if(result.code===100){
                   alert(result.msg);
                   $("#title").val("");
                   $("#name").val("");
                   $("#addr").val("");
                   $("#phone").val("");
                   $("#lytext").val("");
                   top.location.href = "/index.html";
               }else{
                   alert(result.msg);
               }
           }


       })
    });
    $("#btnReset").click(function () {
        $("#title").val("");
        $("#name").val("");
        $("#addr").val("");
        $("#phone").val("");
        $("#lytext").val("");
    });

});



