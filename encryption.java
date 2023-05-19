import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.spec.SecretKeySpec;

public class encryption{

    public static byte[] encrypt(byte[] key, byte[] iv, byte[] plaintext) throws Exception {
        // Create the AES cipher with CBC mode
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));

        // Initialize the cipher for encryption
        CipherParameters params = new ParametersWithIV(new KeyParameter(key), iv);
        cipher.init(true, params);

        // Create a temporary buffer to hold the encrypted data
        byte[] buffer = new byte[cipher.getOutputSize(plaintext.length)];
        int length = cipher.processBytes(plaintext, 0, plaintext.length, buffer, 0);

        // Process the final block
        length += cipher.doFinal(buffer, length);

        // Encode the encrypted data in Base64
        return Base64.encode(buffer, 0, length);
    }

    public static byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) throws Exception {
        // Create the AES cipher with CBC mode
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));

        // Initialize the cipher for decryption
        CipherParameters params = new ParametersWithIV(new KeyParameter(key), iv);
        cipher.init(false, params);

        // Create a temporary buffer to hold the decrypted data
        byte[] buffer = new byte[cipher.getOutputSize(ciphertext.length)];
        int length = cipher.processBytes(ciphertext, 0, ciphertext.length, buffer, 0);

        // Process the final block
        length += cipher.doFinal(buffer, length);

        // Return the decrypted data
        return buffer;
    }

    public static void main(String[] args) {
        try {
            // The key and IV should be securely generated and exchanged
            byte[] key = "0123456789abcdef".getBytes("UTF-8"); // 128-bit key
            byte[] iv = "fedcba9876543210".getBytes("UTF-8"); // 128-bit IV

            String plaintext = "Hello, world!";
            byte[] encrypted = encrypt(key, iv, plaintext.getBytes("UTF-8"));

            // Print the encrypted data
            System.out.println("Encrypted: " + Base64.toBase64String(encrypted));

            byte[] decrypted = decrypt(key, iv, encrypted);
            String decryptedText = new String(decrypted, "UTF-8");

            // Print the decrypted data
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }
}


/*
   output: 
   Encrypted: GbXKfI0BZVbXOpfpaxG8xQ==
   Decrypted: Hello, world! 




 */