var checkUsername=false;
var checkPassword=false;
var checkPasswordConfirm=false;
var checkVerificationCode=false;

$(function () {

    checkSubmit();

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
        $.post("FindUserNameServlet",{username:username},function (data) {
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

    $("#verificationCode").blur(function() {
        var code=$(this).val();
        var message=$("#user-verificationCode");
        var img=$("#verificationCode-img");
        if(code==""){
            setWrongMessage(img,message,"验证码不能为空");
            checkVerificationCode=false;
            return;
        }
        $.post("FindValidateCodeServlet",{code:code},function (data) {
            if(data.codeWrong==1){
                setWrongMessage(img,message,data.msg);
                checkVerificationCode=false;
            }else{
                setCorrectMessage(img,message);
                checkVerificationCode=true;
            }
            checkSubmit();
        },"json");
    });


    $("#password").blur(function () {
        var reg=/^[\w]{4,20}$/g;

        var password=$("#password").val();
        var passwordConfirm=$("#passwordConfirm").val();

        var message=$("#user-password");
        var  message2=$("#user-passwordConfirm");

        var img=$("#password-img");
        var img2=$("#passwordConfirm-img");

        if(!reg.test(password)){
            setWrongMessage(img,message,"密码不规范");
            checkPassword=false;
        }else if(passwordConfirm==password){
            setCorrectMessage(img,message);
            setCorrectMessage(img2,message2);
            checkPasswordConfirm=true;
            checkPassword=true;
        }else{
            setWrongMessage(img2,message2,"两次密码不一致");
            img.attr("src","");
            message.html("");
            checkPasswordConfirm=false;
            checkPassword=false;
        }
        checkSubmit();
    });

    $("#passwordConfirm").blur(function () {
        var passwordConfirm=$("#passwordConfirm").val();
        var password=$("#password").val();

        var message=$("#user-passwordConfirm");
        var  message2=$("#user-password");

        var img=$("#passwordConfirm-img");
        var img2=$("#password-img");

        if(passwordConfirm==""){
            setWrongMessage(img,message,"密码不能为空");
            checkPasswordConfirm=false;
        }else if(passwordConfirm==password){
            setCorrectMessage(img,message);
            setCorrectMessage(img2,message2);
            checkPasswordConfirm=true;
            checkPassword=true;
        }else{
            setWrongMessage(img,message,"两次密码不一致");
            img2.attr("src","");
            message2.html("");
            checkPasswordConfirm=false;
            checkPassword=false;
        }
        checkSubmit();
    });

});

function checkSubmit() {
    if(checkUsername==true&&checkPassword==true&&checkPasswordConfirm==true&&checkVerificationCode==true){
        $("#submit").prop("disabled",false);
    }else{
        $("#submit").prop("disabled",true);
    }
}

function noImg(obj) {
    obj.style.display="none";
}

function setWrongMessage(imgObj,messageObj,content) {
    imgObj.attr("src","img/wrong.png");
    imgObj.css("display","inline-block");
    messageObj.css("color","red");
    messageObj.html(content);
    $("#submit").prop("disabled",true);
}

function setCorrectMessage(imgObj,messageObj) {
    imgObj.attr("src","img/correct.png");
    imgObj.css("display","inline-block");
    messageObj.html("");
}