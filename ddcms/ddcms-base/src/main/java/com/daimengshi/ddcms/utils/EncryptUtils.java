package com.daimengshi.ddcms.utils;

import com.jfinal.kit.HashKit;

import java.util.UUID;

public class EncryptUtils extends HashKit {

    /**
     * 生成新的 盐，规则：UUID的随机长度
     *
     * @return
     */
    public static String generateSalt() {
        int random = (int) (10 + (Math.random() * 10));
        return UUID.randomUUID().toString().replace("-", "").substring(random);// 随机长度
    }

    /**
     * 对密码进行 SHA256 加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return sha256(password + salt);
    }

    /**
     * 验证密码是否一致
     *
     * @param inputPassword 用户输入密码，网页输入的密码
     * @param salt          盐
     * @param password      数据库保存的密码
     * @return
     */
    public static boolean verlifyPassword(String inputPassword, String salt, String password) {

        if (inputPassword == null)
            return false;

        if (salt == null) {
            return false;
        }
        return password.equals(encryptPassword(inputPassword, salt));
    }


    public static void main(String[] args) {
        String salt = generateSalt();
        System.out.println("salt:" + salt);//撒盐打印
        System.out.println("password:" + encryptPassword("123456", salt));//测试
    }

}
