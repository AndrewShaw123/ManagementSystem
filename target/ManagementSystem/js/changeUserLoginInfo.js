var checkUsername=false;

$(function () {

    checkSubmit();

    $.post("../GetCurrentLoginServlet",{},function (data) {
        var username=$("#loginName");
        var pic=$("#pic");
        username.html(data.loginName);
        pic.attr("src","../img/userimg/"+data.picture);
    });

    $("#username").blur(function() {
        var reg=/^[\u4e00-\u9fa5\w]{1,6}$/g;
        var username=$(this).val();
        var message=$("#user-username");
        var img=$("#username-img");
        if(username==""){
            setWrongMessage(img,message,"用户名不能为空");
            checkUsername=false;
            return;
        }
        if(reg.test(username)){
            checkUsername=true;
            setCorrectMessage(img,message);
        }else{
            checkUsername=false;
            setWrongMessage(img,message,"用户名不规范");
            return;
        }
        $.post("../FindLoginNameExceptThisServlet",{username:username},function (data) {
            if(data.userExist==1){
                setWrongMessage(img,message,data.msg);
                checkUsername=false;
            }else{
                setCorrectMessage(img,message);
                checkUsername=true;
            }
            checkSubmit();
        },"json");
    });



});

function checkSubmit() {
    if(checkUsername==true){
        $("#submit").prop("disabled",false);
    }else{
        $("#submit").prop("disabled",true);
    }
}

function noImg(obj) {
    obj.style.display="none";
}

function setWrongMessage(imgObj,messageObj,content) {
    imgObj.attr("src","../img/wrong.png");
    imgObj.css("display","inline-block");
    messageObj.css("color","red");
    messageObj.html(content);
    $("#submit").prop("disabled",true);
}

function setCorrectMessage(imgObj,messageObj) {
    imgObj.attr("src","../img/correct.png");
    imgObj.css("display","inline-block");
    messageObj.html("");
}