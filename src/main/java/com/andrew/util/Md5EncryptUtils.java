package com.andrew.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Md5EncryptUtils Class
 *
 * @author andrew
 * @date 2019/8/19
 */
public class Md5EncryptUtils {

    /**
     * MD5加密加上12位的随机数
     *
     * @param password 明文密码
     * @return 加密完的byte数组
     */
    public static byte[] encryptByMD5(String password) {
        byte[] randomBytes=new byte[12];
        SecureRandom random =new SecureRandom();
        random.nextBytes(randomBytes);

        byte[] passwordBytes=md5(randomBytes,password);

        byte[] resultPassword=new byte[randomBytes.length+passwordBytes.length];
        System.arraycopy(randomBytes, 0, resultPassword, 0,randomBytes.length);
        System.arraycopy(passwordBytes, 0, resultPassword, randomBytes.length, passwordBytes.length);
        return resultPassword;
    }

    /**
     * 验证密码
     *
     * @param password 带验证的密码
     * @param resultPassword 数据库得到的密码
     * @return 正确返回true 错误返回false
     */
    public static boolean validatePassword(String password,byte[] resultPassword) {
        //获得存储时的随机数
        byte[] randomBytes=new byte[12];
        System.arraycopy(resultPassword, 0, randomBytes, 0,randomBytes.length);
        //通过获得的随机数加上输入的密码更新摘要，在计算一次看是否与存储的密码相同
        byte[] passwordBytes=md5(randomBytes,password);
        byte[] passwordBytesInDB=new byte[resultPassword.length-randomBytes.length];
        System.arraycopy(resultPassword, randomBytes.length, passwordBytesInDB, 0,passwordBytesInDB.length);
        if(Arrays.equals(passwordBytes, passwordBytesInDB)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static byte[] md5( byte[] randomBytes,String password){
        MessageDigest messageDigest=null;
        try {
            messageDigest=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(randomBytes);
        messageDigest.update(password.getBytes());
        return messageDigest.digest();
    }
}
