package travel.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Md5Uitl {
    private Md5Uitl(){}

    public static String encodeByMd5(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(password.getBytes());
        return byteArrayToHexString(digest);
    }

    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for(byte b : byteArray){
            String hex = byteToHexString(b);
            sb.append(hex);
        }
        return sb.toString();
    }
    private static String[] hex = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
    private static String byteToHexString(byte b) {
        int n = b;
        if(n<0){
            n=n+256;
        }
        int d1 = n/16;
        int d2 = n%16;
        return hex[d1]+hex[d2];
    }

}
