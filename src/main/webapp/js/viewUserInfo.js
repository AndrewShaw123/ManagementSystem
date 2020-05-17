start=0;
$(function () {

    getData();
    updatePage();

    $("#firstPage").click(function () {
        $("table tr:gt(0)").remove();
        start=0;
        getData();
        updatePage();
    });

    $("#endPage").click(function () {
        $("table tr:gt(0)").remove();
        start=$("#totalPage").html()-1;
        getData();
        updatePage();
    });

    $("#nextPage").click(function () {
        $("table tr:gt(0)").remove();
        start= $("#currentPage").html();
        getData();
        updatePage();
    });

    $("#lastPage").click(function () {
        $("table tr:gt(0)").remove();
        start= $("#currentPage").html()-2;
        getData();
        updatePage();
    });

});

function getData() {
    $.post("../../GetAllUserServlet",{start:start},function (data) {
        var allUser=$("#allUser");
        $(data).each(function () {
            var tr="<tr>" +
                "<td><img src='../../img/userimg/"+this.picture+"' width='30px' height='30px' /></td>" +
                "<td>"+this.username+"</td>" +
                "<td>"+this.stuNumber+"</td>" +
                "<td>"+this.authority+"</td>" +
                "<td>"+this.gender+"</td>" +
                "<td>"+this.age+"</td>" +
                "<td>"+this.phone+"</td>" +
                "<td style='width: 300px'>"+this.dept+"</td>" +
                "<td><a href='updateUserInfo.html?id="+this.id+"'>修改</a>&nbsp;&nbsp;&nbsp;<a href='../../DeleteUserServlet?id="+this.id+"'>删除</a></td>" +
                "</tr>";
            allUser.append(tr);
        });
    });
}

function updatePage() {
    $("#totalPage").html("");
    $("#currentPage").html("");
    $("#currentPage").html(parseInt(start) +1);
    $.post("../../GetUserInfoCountServlet",{},function (data) {
        $("#totalPage").html(data);
        if( $("#totalPage").html()== $("#currentPage").html()){
            $("#nextPage").prop("disabled",true);
        }else{
            $("#nextPage").prop("disabled",false);
        }
    });
    if($("#currentPage").html()==1){
        $("#lastPage").prop("disabled",true);
    }else{
        $("#lastPage").prop("disabled",false);
    }
}