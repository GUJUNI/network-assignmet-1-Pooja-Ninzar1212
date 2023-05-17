import java.io.FileInputStream;
import java.security.MessageDigest;

public class md{

    public static byte[] calculateHash(String filePath, String algorithm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] buffer = new byte[8192];
        int bytesRead;

        FileInputStream fis = new FileInputStream(filePath);
        while ((bytesRead = fis.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        fis.close();

        return digest.digest();
    }

    public static void main(String[] args) {
        try {
            String filePath = "path/to/your/file";
            String algorithm = "SHA-256";

            byte[] hash = calculateHash(filePath, algorithm);

            // Convert the hash to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            String hashHex = sb.toString();

            // Print the computed hash
            System.out.println("Hash (Hex): " + hashHex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 
Hash (Hex): 69db72f91b3d824af5e17c4ae7dab04572e3b9133d5d9a98f1d208beac7dbd0f


 */

