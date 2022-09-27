package model.utils;

import java.util.Base64;

public class Encrypt {
    public static String encode(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(password.getBytes());
        return new String(encoded);
    }

    public static String decode(String password) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(password);
        return new String(decoded);
    }
}
