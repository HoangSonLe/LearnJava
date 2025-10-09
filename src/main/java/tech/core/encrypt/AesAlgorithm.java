package tech.core.encrypt;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "aes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class AesAlgorithm {

    static String SECRET_KEY = "TMySecretKey1801"; // 16/24/32 bytes
    static String ALGORITHM = "AES";
    static String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    static Charset CHARSET = StandardCharsets.UTF_8;

    public static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(CHARSET), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(SECRET_KEY.getBytes(CHARSET));
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(CHARSET), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, CHARSET);
    }
}
