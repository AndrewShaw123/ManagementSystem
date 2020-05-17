$(function () {

    showErrorMessage();
    showSignupSuccess();
    otherlogin();
});

function getParam(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null){
        return unescape(r[2]);
    }
    return null;
}

function showErrorMessage() {
    var param=getParam("error");
    var message=$("#message");
    message.css("color","red");
    if(param==null){
        return;
    }else if(param=="UserNotExist"){
        message.html("用户不存在");
    }else if(param=="PasswordWrong"){
        message.html("密码错误");
    }else if(param=="NotPermit"){
        message.html("管理员没有审批");
    }
}

function  showSignupSuccess() {
    var param=getParam("signup");
    if(param==null){
        return;
    }else{
        alert("注册成功");
    }
}

function otherlogin() {
    var param=getParam("otherLogin");
    if(param==null){
        return;
    }else{
        alert("账号在其他地方登录");
    }

}