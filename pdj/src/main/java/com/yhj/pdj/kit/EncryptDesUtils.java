package com.yhj.pdj.kit;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * despass@ 默认为好差评 密钥
 * @Description des加解密类
 * @since 2019-08-28
 */
public class EncryptDesUtils {
	
	public static final String unicom_key = "chinaunicom";
	
	
    /**
     * 使用默认密钥，加密字符串
     * @param msg  需加密的字符串
     * @return 加密后的字符串
     */
    private static String encrypt(String msg) throws Exception {
        try {
            String password = "despass@";
            return byteArr2HexStr(encrypt(msg.getBytes(),password));
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 加密字符串
     * @param msg  需加密的字符串
     * @param password  密钥
     * @return 加密后的字符串
     */
    public static String encrypt(String msg,String password){
        try {
            password = StrKit.notBlank(password)?password:"despass@";
            System.out.println(msg+":"+msg.getBytes());
            return byteArr2HexStr(encrypt(msg.getBytes(),password));
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 加密字节数组
     * @param bytes  需加密的字节数组
     * @param password  密钥
     * @return 加密后的字节数组
     */
    private static byte[] encrypt(byte[] bytes,String password) throws Exception {
        Key key = getKey(password.getBytes());
        Cipher encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        return encryptCipher.doFinal(bytes);
    }
    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813，和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     * @param arrB  需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用2个字符才能表示，所以字符串的长度是数组长度的2倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            System.out.println("字节内容："+intTmp);
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 解密字节数组
     * @param bytes  需解密的字节数组
     * @param password  密钥
     * @return 解密后的字节数组
     */
    public static byte[] decrypt(byte[] bytes,String password) throws Exception {
        Key key = getKey(password.getBytes());
        Cipher decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        return decryptCipher.doFinal(bytes);
    }
    /**
     * 解密字符串
     * @param strIn  需解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String strIn,String password) throws Exception {
        password = StrKit.notBlank(password)?password:"despass@";
        return new String(decrypt(hexStr2ByteArr(strIn),password));
    }
    /**
     * 使用默认密钥解密字符串
     * @param strIn  需解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String strIn) throws Exception {
        String password = "despass@";
        return new String(decrypt(hexStr2ByteArr(strIn),password));
    }
    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     * @param arrBTmp  构成该字符串的字节数组
     * @return 生成的密钥
     */
    private static Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }
   

    /**
     * 将表示16进制值的字符串转换为byte数组，和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     */

    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    public static void main(String[] args) {
        try {
            String key="despass@";
            // 自定义密钥
            String str = "37021119910101198x";
            System.out.println("加密后的字符：" + encrypt(str,key));
            System.out.println("解密后的字符：" + decrypt(encrypt(str,key),""));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
