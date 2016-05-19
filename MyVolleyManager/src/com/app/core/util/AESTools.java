package com.app.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.text.TextUtils;

public class AESTools {

    private static final String TAG="AESTools";

    /**
     * @description: 加密
     * @author: zhanghao
     * @date: 2015年4月3日 下午1:59:22
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        try {
            String key="iZo7d-@4f](Z^nCa";
            String iv="I(P-~hJgfs%CQxrm";

            Cipher cipher=Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize=cipher.getBlockSize();

            byte[] dataBytes=data.getBytes();
            int plaintextLength=dataBytes.length;
            if(plaintextLength % blockSize != 0) {
                plaintextLength=plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext=new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec=new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec=new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted=cipher.doFinal(plaintext);

            return Base64.encodeBytes(encrypted);

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: 解密
     * @author: zhanghao
     * @date: 2015年4月3日 下午1:59:37
     * @param data
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String data) throws Exception {
        try {
            String key="!]zQ#Fv3(hu@N6P+";
            String iv="XSM#nch(CrT(nW^U";

            byte[] encrypted1=Base64.decode(data);

            Cipher cipher=Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec=new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec=new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original=cipher.doFinal(encrypted1);
            String originalString=new String(original);

            return originalString;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * md5加密的工具方法
     * @param text
     * @return
     */
    public static String md5Encode(String text) {
        try {
            MessageDigest mess=MessageDigest.getInstance("md5");
            byte[] md5=mess.digest(text.getBytes());
            StringBuilder sb=new StringBuilder();
            for(byte b: md5) {
                int i=b & 0xff;
                // 将每一个字节都转换成16进制数
                String string=Integer.toHexString(i);
                if(string.length() == 1)
                    sb.append("0");// 16进制数都是两位数，如果不足两位，就在这个字符串前面添加一个0
                sb.append(string);
            }
            return sb.toString();
        } catch(NoSuchAlgorithmException e) {
            // 此异常不可能出现
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @description: sha1算法
     * @author: zhanghao
     * @date: 2015年4月2日 下午7:36:50
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest=java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[]=digest.digest();
            // Create Hex String
            StringBuffer hexString=new StringBuffer();
            // 字节数组转换为 十六进制 数
            for(int i=0; i < messageDigest.length; i++) {
                String shaHex=Integer.toHexString(messageDigest[i] & 0xFF);
                if(shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @description: 字符串加工
     * @author: Administrator
     * @date: 2015年4月2日 下午7:37:13
     * @param sort
     * @return
     */
    public static String stringSort(String sort) {
        TreeMap<String, Object> treeMap=new TreeMap<String, Object>();

        String newSort="";

        com.alibaba.fastjson.JSONObject jsonObject=com.alibaba.fastjson.JSONObject.parseObject(sort);

        for(java.util.Map.Entry<String, Object> entry: jsonObject.entrySet()) {
            // 如果含有}符号,则认为是JSON
            String skey=entry.getKey().toString();
            String sValue=entry.getValue().toString();

            if(!sValue.contains("{")) {
                treeMap.put(skey, sValue);
            }

            if(sValue.contains("}")) {
                com.alibaba.fastjson.JSONObject jsonObject1=com.alibaba.fastjson.JSONObject.parseObject(sValue);
                for(java.util.Map.Entry<String, Object> entry1: jsonObject1.entrySet()) {
                    treeMap.put(entry.getKey() + '_' + entry1.getKey(), entry1.getValue());
                }
            }
        }

        for(java.util.Map.Entry<String, Object> entry: treeMap.entrySet()) {
            newSort+=entry.getKey() + "=" + entry.getValue() + "&";
        }

        String substring=newSort.substring(0, newSort.length() - 1);

        // System.out.println(substring);

        return substring;
    }

    /***
     * @MethodName: parseString
     * @Description: 解密回来的字符串
     * @param @param parsed
     * @param @return
     * @return String 返回类型
     * @author zhanghao
     */
    public static String parseString(String parsed) {

        String desEncrypt=null;
        String stringCut=null;
        String[] splits=null;
        String newjson1=null;
        String newjson2=null;

        // 替换字符
        if(!TextUtils.isEmpty(parsed)) {
            newjson1=parsed.replaceAll("\n", "");
        }

        // 字符串反转
        if(!TextUtils.isEmpty(newjson1)) {
            newjson2=new StringBuilder(newjson1).reverse().toString();
        }

        try {
            // 解密
            if(!TextUtils.isEmpty(AESTools.desEncrypt(newjson2).trim())) {
                desEncrypt=AESTools.desEncrypt(newjson2).trim();
            }

            // 字符串分割
            String key="faa8c21fc829a7b3d9150f00e91ffced";

            if(desEncrypt.split(key) != null) {
                splits=desEncrypt.split(key);
            }

            if(splits[1] != null) {
                stringCut=splits[1];
                // LogUtils.e(TAG, "stringCut:" + stringCut);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return stringCut;
    }

}
