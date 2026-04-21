package crypto;

import java.security.MessageDigest;

public class HashUtil {

    private static final String HASH_ALGORITHM = "SHA-256";

    // Generate SHA-256 hash of input data
    public static byte[] generateHash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        return digest.digest(data);
    }
}
