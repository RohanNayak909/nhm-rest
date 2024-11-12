package nirmalya.aatithya.restmodule.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;


@Component
public class AESUtil {

    private static final String SECRET_KEY = "abcdefghijklmnop"; // 16-char key for AES-128

    public static String decrypt(String encrypted) {
        try {
            // Base64 decode the encrypted input (because CryptoJS outputs Base64)
            byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);

            // Prepare the secret key for AES decryption
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

            // Initialize the AES cipher in ECB mode with PKCS5Padding (same as PKCS7 in JavaScript)
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Perform the decryption
            byte[] originalBytes = cipher.doFinal(encryptedBytes);

            // Convert to string and return the decrypted value
            return new String(originalBytes);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error while decrypting", ex);
        }
    }
}





