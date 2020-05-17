$(function () {

    $("#searchText").keyup(function () {
        $(".search ul>li").remove();
        var searchText=$("#searchText").val();
        var column=$("select option:selected").val();
        if(column=="gender"||column=="age"){
            return;
        }
        if(searchText==""){
            $(".search ul>li").remove();
            $(".changes").css("display","none");
            return;
        }
        getSuggest(searchText,column);

    });

    $("#search").click(function () {
        $(".search ul>li").remove();
        $(".changes").css("display","none");
        var searchText=$("#searchText").val();
        var column=$("select option:selected").val();
        $.post("../../SearchServlet",{searchText:searchText,column:column},function (data) {
            $("table tr:gt(0)").remove();
            if(data==""){
                alert("没查询到！");
                return;
            }
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
    });

});

function getSuggest(searchText,column) {
    $.post("../../SearchSuggestServlet",{searchText:searchText,column:column},function (data) {
        if(data==""){
            return;
        }
        var suggest=$(".changes");
        $(data).each(function () {
            var li="<li>" + this +"</li>";
            suggest.append(li);
        });
        addEvent();
        $(".changes").css("display","block");
    });
}

function addEvent() {
    $(".changes li").each(function () {
        $(this).click(function () {
            $("#searchText").val($(this).text());
            $(".changes").css("display","none");
        });
        this.onmouseover=function () {
            $(this).addClass("active").siblings().removeClass();
        }

    });
}
