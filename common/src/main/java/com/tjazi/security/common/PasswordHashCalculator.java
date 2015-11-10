package com.tjazi.security.common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Krzysztof Wasiak on 03/11/2015.
 */

public class PasswordHashCalculator {

    private static final String hashAlgorithm = "SHA-1";

    /**
     * Calculate hash of the given password
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String calculatePasswordHash(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm);

            md.update(password.getBytes("UTF-8"));

            return new BigInteger(1, md.digest()).toString(16);

        } catch (Exception ex) {
            return null;
        }
    }
}
