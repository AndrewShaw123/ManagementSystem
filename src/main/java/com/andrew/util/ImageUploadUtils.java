package com.andrew.util;

import com.andrew.domain.ShowUser;
import com.andrew.domain.UserLogin;
import org.apache.catalina.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * ImageUploadUtils Class
 *
 * @author andrew
 * @date 2019/8/11
 */
public class  ImageUploadUtils {

    public static UserLogin getUserLoginAllParam(HttpServletRequest request){

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024 * 1024 * 2);
        upload.setHeaderEncoding("utf-8");

        UserLogin userLogin=new UserLogin();

        List<FileItem> items;
        try {
            items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName=item.getFieldName();
                    if("username".equals(fieldName)) {
                        userLogin.setUsername(item.getString("utf-8"));
                    }
                } else {
                    String filename = item.getName();
                    if(!"".equals(filename)) {
                        if(filename.lastIndexOf(".jpg")!=-1||filename.lastIndexOf(".png")!=-1) {
                            filename=setImg(filename,request,item);
                            userLogin.setPicture(filename);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLogin;
    }

    public static ShowUser getUpdateUserAllParam(HttpServletRequest request){

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024 * 1024 * 2);
        upload.setHeaderEncoding("utf-8");

        ShowUser showUser=new ShowUser();

        List<FileItem> items;
        try {
            items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName=item.getFieldName();
                    if("username".equals(fieldName)) {
                        showUser.setUsername(item.getString("utf-8"));
                    } else if("gender".equals(fieldName)) {
                        showUser.setGender(item.getString("utf-8"));
                    } else if("authority".equals(fieldName)) {
                        showUser.setAuthority(item.getString("utf-8"));
                    }else if("number".equals(fieldName)) {
                        showUser.setStuNumber(item.getString("utf-8"));
                    }else if("age".equals(fieldName)) {
                        showUser.setAge(Integer.parseInt(item.getString("utf-8")));
                    }else if("phone".equals(fieldName)) {
                        showUser.setPhone(item.getString("utf-8"));
                    }else if("dept".equals(fieldName)) {
                        showUser.setDept(item.getString("utf-8"));
                    }else if("userid".equals(fieldName)) {
                        showUser.setId(Integer.parseInt(item.getString("utf-8")));
                    }
                } else {
                    String filename = item.getName();
                    if(!"".equals(filename)) {
                        if(filename.lastIndexOf(".jpg")!=-1||filename.lastIndexOf(".png")!=-1) {
                            filename = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
                            String savePath = request.getRealPath("/img/userimg");
                            File saveFile = new File(savePath + "/" + filename);
                            item.write(saveFile);
                            showUser.setPicture(filename);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showUser;
    }

    public static UserLogin getRegisterAllParam(HttpServletRequest request){

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024 * 1024 * 2);
        upload.setHeaderEncoding("utf-8");

        UserLogin userLogin=new UserLogin();
        userLogin.setChecked("unchecked");
        userLogin.setFirst(true);

        List<FileItem> items;
        try {
            items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName=item.getFieldName();
                    if("username".equals(fieldName)) {
                        userLogin.setUsername(item.getString("utf-8"));
                    } else if("password".equals(fieldName)) {
                        userLogin.setPassword(Md5EncryptUtils.encryptByMD5(item.getString("utf-8")));
                    } else if("authority".equals(fieldName)) {
                        userLogin.setAuthority(item.getString("utf-8"));
                    }
                } else {
                    String filename = item.getName();
                    if(!"".equals(filename)) {
                        if(filename.lastIndexOf(".jpg")!=-1||filename.lastIndexOf(".png")!=-1) {
                            filename=setImg(filename,request,item);
                            userLogin.setPicture(filename);
                        }
                    }else{
                        userLogin.setPicture("default.jpg");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLogin;
    }

    private static String setImg(String filename,HttpServletRequest request,FileItem item){
        filename = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
        String savePath = request.getServletContext().getRealPath("/img/userimg");
        File saveFile = new File(savePath + "/" + filename);
        try {
            item.write(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }
}
