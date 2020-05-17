var ws;
var target="";

$(function () {

    $.post("../GetCurrentLoginServlet",{},function (data) {
        var username=$("#loginName");
        var pic=$("#pic");
        var myPic=$("#myPic");
        var myName=$("#myName");
        username.html(data.loginName);
        myName.html(data.loginName)
        pic.attr("src","../img/userimg/"+data.picture);
        myPic.attr("src","../img/userimg/"+data.picture);
    });

    target="ws://localhost:8080/ManagementSystem/chatSocket?username="+getParam("username");
    connect();
});

function connect() {

    if ('WebSocket' in window) {
        ws = new WebSocket(target);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(target);
    } else {
        alert('WebSocket is not supported by this browser.');
        return;
    }
    ws.onmessage=function (event) {

        eval("var msg="+event.data+";");

        if(undefined!=msg.userList){
            $("#all #userList ul li").remove();
            $(msg.userList).each(function () {
                var li="<li><img id='"+this+"' src='' width='35px' height='35px' style='margin-top: 10px'><span>"+this+"</span><div id='"+this+"notice'></div></li>";
                $("#all #userList ul").append(li);
                $.post("../GetCurrentPictureServlet",{name:this},function (data) {
                    var thisName=this.data;
                    thisName=thisName.split("=")[1];
                    thisName=decodeURIComponent(thisName);
                    var pic=$("#"+thisName);
                    pic.attr("src","../img/userimg/"+data);
                });
            });
            addEvent();
        }


        if(undefined!=msg.message){

            var getMessage="<br><br><br><img src='"+$("#"+msg.from)[0].src+"' width='35px' height='35px'>"+msg.from+" "+msg.time+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+msg.message+"<br/>";
            if(msg.from==$("#loginName").text()){
                getMessage="<br><br><br><div style='float: right'>"+msg.time+" "+msg.from+"<img src='"+$("#"+msg.from)[0].src+"' width='35px' height='35px'>"+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+msg.message+"<br/></div>";
            }

            if($("#unselectChat")[0]==undefined&&($("#to").text()==msg.to||$("#to").text()==msg.from)){
                $("#content").append(getMessage);
                var scrollHeight=$("#content").prop("scrollHeight");
                $("#content").scrollTop(scrollHeight,200);
            }
            if($("#loginName").text()!=msg.from){
                $("#"+msg.from+"notice").addClass("notice");
                $("#"+msg.from+"notice").html("1");
                $.post("../SetChatContentServlet",{content:getMessage,from:msg.from,to:msg.to},function () {});
            }

        }

    }
}


function send(){
    var msg=$("#msg").val();
    var to=$("#to").text();
    var from=$("#loginName").text();
    var date = new Date();
    var time=date.getFullYear()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
    if(msg==""){
        $("#msg").val("");
        return;
    }
    var message ={"to":to,"message":msg,"from":from,"time":time};
    ws.send(JSON.stringify(message));
    $("#msg").val("");
}

function getParam(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null){
        return unescape(r[2]);
    }
    return null;
}

function noImg(obj) {
    obj.style.display="none";
}

window.onbeforeunload = function(){
    ws.close();
}

function addEvent() {
    $("#all #userList ul li").each(function () {
        $(this).click(function () {
            $("#container").css("background","whitesmoke");
            $("#content").empty();
            $("#sendArea").css("display","block");
            $(this.lastChild).removeClass("notice");
            $(this.lastChild).html("");
            $(this).addClass("sel").siblings().removeClass();
            $("#to").text($(this).text());
            var from=$("#loginName").text();
            var to=$(this).text();
            $.post("../GetChatContentServlet",{from:from,to:to},function (data) {
                $("#content").append(data);
                var scrollHeight=$("#content").prop("scrollHeight");
                $("#content").scrollTop(scrollHeight,200);
            });
        });

    });
}