package modelo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptacion {

    public String getHash(String password) {

        MessageDigest messageDigest;
        byte[] bytes;
        StringBuffer stringBuffer;
        int i;
        String hash;
        
        hash = null;

        try {

            messageDigest = MessageDigest.getInstance("MD5");
            bytes = messageDigest.digest(password.getBytes("UTF-8"));

            stringBuffer = new StringBuffer();

            for (i = 0; i < bytes.length; i++) {
                stringBuffer.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
            }
            
            hash = stringBuffer.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            
            ex.printStackTrace();
        }

        return hash;
    }
}
