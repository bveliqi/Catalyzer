package com.hackzurich.catalyzer.auth;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by behar on 11.10.14.
 */
public class PasswordHashing {

    private static final String SALT = "EA333B0BFC3DFFE85";
    private static MessageDigest digestInstance;

    static {
        try {
            digestInstance = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        };
    }



    public static String hash(String password) {
        String pwd = password == null ? "" : password;
        final byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digestInstance.update(bytes);
        final byte[] digest = digestInstance.digest();
        final String hashedPassword = new HexBinaryAdapter().marshal(digest);
        return SALT + hashedPassword;
    }

}
