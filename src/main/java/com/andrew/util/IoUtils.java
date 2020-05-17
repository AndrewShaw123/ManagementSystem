package com.andrew.util;

import java.io.*;

/**
 * IoUtils Class
 *
 * @author andrew
 * @date 2019/8/16
 */
public class IoUtils {

    public static void writeHistory(String path,String content){
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter(path,true));
            bw.write(content);
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readHistory(String path){
        BufferedReader br=null;
        StringBuilder Message=new StringBuilder();
        try {
            String str="";
            File file=new File(path);
            if(!file.exists()){
                return "";
            }
            br=new BufferedReader(new FileReader(file));
            while((str = br.readLine()) != null){
                Message.append(str);
            }
            return Message.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean checkIfExist(String path){
        File file=new File(path);
        if(file.exists()){
           return true;
        }else{
            return false;
        }

    }

}
