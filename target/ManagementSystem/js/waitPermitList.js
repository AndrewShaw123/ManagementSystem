$(function () {

    $.post("../../GetPermitListServlet",{},function (data) {

        var permit=$("#permit");
        $(data).each(function () {
            var tr="<tr>" +
                "<td><img src='../../img/userimg/"+this.picture+"' width='30px' height='30px' /></td>" +
                "<td>"+this.username+"</td>" +
                "<td>"+this.authority+"</td>" +
                "<td style='width: 30px'><a href='../../PermitServlet?id="+this.id+"'>允许</a>&nbsp;&nbsp;&nbsp;<a href='../../RefuseServlet?id="+this.id+"'>拒绝</a></td>" +
                "</tr>";
            permit.append(tr);
        });

    });

});