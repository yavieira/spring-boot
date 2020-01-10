package br.com.mySpringBoot.api.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class StringUtils {

    public static String hashPassword(String senha) {

        String hashword = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(senha.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hashword = hash.toString(16);

        } catch (Exception e) {

        }

        return hashword;
    }
}
