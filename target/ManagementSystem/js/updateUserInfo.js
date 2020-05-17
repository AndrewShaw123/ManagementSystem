var checkNumber=true;
var checkAge=true;
var checkPhone=true;
var checkName=true;

$(function () {

    $.get("../../GetAllDeptServlet",{},function (data) {

        var dept=$("#dept");

        $(data).each(function () {
            var option="<option value='"+this.deptId+"'>"+this.dept+"</option>";
            dept.append(option);
        });

    });

    var id=getParam("id");
    $("#user-id").attr("value",id);

    checkSubmit();

    $("#username").blur(function() {
        var reg=/^[\u4e00-\u9fa5\w]{1,6}$/g;
        var name=$(this).val();
        var message=$("#user-username");
        var img=$("#username-img");
        if(name==""){
            setWrongMessage(img,message,"姓名不能为空");
            checkName=false;
            return;
        }
        if(reg.test(name)){
            checkName=true;
            setCorrectMessage(img,message);
        }else{
            checkName=false;
            setWrongMessage(img,message,"姓名不规范");
            return;
        }
        $.post("../../FindNameExceptThisServlet",{name:name,id:id},function (data) {
            if(data.userExist==1){
                setWrongMessage(img,message,data.msg);
                checkName=false;
            }else{
                setCorrectMessage(img,message);
                checkName=true;
            }
            checkSubmit();
        },"json");
    });

    $("#number").blur(function() {
        var reg=/^[a-zA-Z0-9]{9}$/g;
        var number=$(this).val();
        var message=$("#user-number");
        var img=$("#number-img");
        if(number==""){
            setWrongMessage(img,message,"学号不能为空");
            checkNumber=false;
            return;
        }
        if(reg.test(number)){
            checkNumber=true;
            setCorrectMessage(img,message);
        }else{
            checkNumber=false;
            setWrongMessage(img,message,"学号不规范");
            return;
        }
        $.post("../../FindNumberExceptThisServlet",{number:number,id:id},function (data) {
            if(data.numberExist==1){
                setWrongMessage(img,message,data.msg);
                checkNumber=false;
            }else{
                setCorrectMessage(img,message);
                checkNumber=true;
            }
            checkSubmit();
        },"json");
    });

    $("#phone").blur(function() {
        var reg=/^[\d]{11}$/g;
        var phone=$(this).val();
        var message=$("#user-phone");
        var img=$("#phone-img");
        if(phone==""){
            setWrongMessage(img,message,"电话不能为空");
            checkPhone=false;
            return;
        }
        if(reg.test(phone)){
            checkPhone=true;
            setCorrectMessage(img,message);
        }else{
            checkPhone=false;
            setWrongMessage(img,message,"电话不规范");
            return;
        }
        $.post("../../FindPhoneExceptThisServlet",{phone:phone,id:id},function (data) {
            if(data.phoneExist==1){
                setWrongMessage(img,message,data.msg);
                checkPhone=false;
            }else{
                setCorrectMessage(img,message);
                checkPhone=true;
            }
            checkSubmit();
        },"json");
    });

    $("#age").blur(function () {
        var age=$("#age").val();
        var message=$("#user-age");
        var img=$("#age-img");
        if(age==""){
            setWrongMessage(img,message,"年龄不能为空");
            checkAge=false;
        }else{
            if(age<17||age>30){
                setWrongMessage(img,message,"年龄不规范");
                checkAge=false;
            }else{
                setCorrectMessage(img,message);
                checkAge=true;
            }
        }
        checkSubmit();
    });


    $.post("../../GetCurrentUserServlet",{id:id},function (data) {
        $("#username").val(data.username);
        $("#number").val(data.stuNumber);
        $("#age").val(data.age);
        $("#phone").val(data.phone);

        if(data.gender=="男"){
            $("input[value='male']").prop("checked",true);
        }else{
            $("input[value='female']").prop("checked",true);
        }


        if(data.authority=="管理员"){
            $("option[value='manager']").prop("selected",true);
        }else{
            $("option[value='member']").prop("selected",true);
        }

        switch(data.dept) {
            case "互联网金融与信息工程学院":
                $("option[value='1']").prop("selected",true);
                break;
            case "会计学院":
                $("option[value='2']").prop("selected",true);
                break;
            case "保险学院":
                $("option[value='3']").prop("selected",true);
                break;
            case "公共管理学院":
                $("option[value='4']").prop("selected",true);
                break;
            case "外语学院":
                $("option[value='5']").prop("selected",true);
                break;
            case "工商管理学院":
                $("option[value='6']").prop("selected",true);
                break;
            case "应用数学学院":
                $("option[value='7']").prop("selected",true);
                break;
            case "法律学院":
                $("option[value='8']").prop("selected",true);
                break;
            case "金融学院":
                $("option[value='9']").prop("selected",true);
                break;
            case "财经传媒学院":
                $("option[value='10']").prop("selected",true);
                break;
            case "经济贸易学院":
                $("option[value='11']").prop("selected",true);
                break;
        }

    },"json");


});

function checkSubmit() {
    if(checkNumber==true&&checkAge==true&&checkPhone==true&&checkName==true){
        $("#submit").prop("disabled",false);
    }else{
        $("#submit").prop("disabled",true);
    }
}

function noImg(obj) {
    obj.style.display="none";
}

function setWrongMessage(imgObj,messageObj,content) {
    imgObj.attr("src","../../img/wrong.png");
    imgObj.css("display","inline-block");
    messageObj.css("color","red");
    messageObj.html(content);
    $("#submit").prop("disabled",true);
}

function setCorrectMessage(imgObj,messageObj) {
    imgObj.attr("src","../../img/correct.png");
    imgObj.css("display","inline-block");
    messageObj.html("");
}

function getParam(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null){
        return unescape(r[2]);
    }
    return null;
}