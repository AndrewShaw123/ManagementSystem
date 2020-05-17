var checkPassword=false;
$(function () {

    $.post("../GetCurrentLoginServlet",{},function (data) {
        var username=$("#loginName");
        var pic=$("#pic");
        username.html(data.loginName);
        pic.attr("src","../img/userimg/"+data.picture);
    });

    checkSubmit();

    var param=getParam("oldPasswordWrong");
    if(param==1){
        setWrongMessage($("#oldPassword-img"),$("#user-oldPassword"),"旧密码错误");
    }else if(param==0){
        alert("修改成功");
    }else{
        setCorrectMessage($("#oldPassword-img"),$("#user-oldPassword"));
    }

    $("#newPassword").blur(function() {
        var reg=/^[\w]{4,20}$/g;
        var newPassword=$(this).val();
        var message=$("#user-newPassword");
        var img=$("#newPassword-img");
        if(reg.test(newPassword)){
            checkPassword=true;
            setCorrectMessage(img,message);
            img.attr("src","../img/correct.png");
            img.css("display","inline-block");
        }else{
            checkPassword=false;
            setWrongMessage(img,message,"密码不规范");
        }
        checkSubmit();
    });

});

function getParam(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null){
        return unescape(r[2]);
    }
    return null;
}

function setWrongMessage(imgObj,messageObj,content) {
    imgObj.attr("src","../img/wrong.png");
    imgObj.css("display","inline-block");
    messageObj.css("color","red");
    messageObj.html(content);
}

function setCorrectMessage(imgObj,messageObj) {
    imgObj.css("display","none");
    messageObj.html("");
}

function noImg(obj) {
    obj.style.display="none";
}

function checkSubmit() {
    if(checkPassword==true){
        $("#submit").prop("disabled",false);
    }else{
        $("#submit").prop("disabled",true);
    }
}