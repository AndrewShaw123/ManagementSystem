$(function () {

    $.post("../../GetCurrentLoginServlet",{},function (data) {
        var username=$("#loginName");
        var pic=$("#pic");
        username.html(data.loginName);
        pic.attr("src","../../img/userimg/"+data.picture);
    });

    $.post("../../GetPermitCountServlet",{},function (data) {
        var permitList=$("#permitList");
        if(data!=0){
            permitList.addClass("notice");
            permitList.html(data);
        }else{
            permitList.removeClass("notice");
            permitList.html("");
        }

    });


});