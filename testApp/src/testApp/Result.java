package testApp;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Result {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_SPEC = "AES";
//    private static final int KEY_SIZE = 256;

    private static SecretKey generateSecretKey(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_SPEC);
        PBEKeySpec keySpec = new PBEKeySpec(password);
        SecretKey secretKey = factory.generateSecret(keySpec);
        return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
    }

    public static String encrypt(String plaintext, String password) throws Exception {
        SecretKey secretKey = generateSecretKey(password.toCharArray());
        String encryptedText = "";
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedText;
    }

    public static String decrypt(String ciphertext, String password) throws Exception {
        SecretKey secretKey = generateSecretKey(password.toCharArray());
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = new byte[0];
        decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String plaintext = "Hello, world!";
            String password = "MySecretPassword";

            // Encrypt the plaintext
            String encryptedText = encrypt(plaintext, password);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the encrypted text
            String decryptedText = decrypt(encryptedText, password);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//public class Result {
//
//    /*
//     * Complete the 'missingPrisoner' function below.
//     *
//     * The function is expected to return an INTEGER_ARRAY.
//     * The function accepts 2D_INTEGER_ARRAY locations as parameter.
//     */
//
//    public static List<Integer> missingPrisoner(List<List<Integer>> locations) {
//        List<Integer> result = new ArrayList<>();
//
//        // Maps to store occurrences of x and y coordinates
//        Map<Integer, Integer> xCount = new HashMap<>();
//        Map<Integer, Integer> yCount = new HashMap<>();
//
//        // Populate the maps with occurrences of x and y coordinates
//        for (List<Integer> location : locations) {
//            int x = location.get(0);
//            int y = location.get(1);
//            
//            // Increment count for x coordinate
//            if (xCount.containsKey(x)) {
//                xCount.put(x, xCount.get(x) + 1);
//            } else {
//                xCount.put(x, 1);
//            }
//            
//            // Increment count for y coordinate
//            if (yCount.containsKey(y)) {
//                yCount.put(y, yCount.get(y) + 1);
//            } else {
//                yCount.put(y, 1);
//            }
//        }
//
//        // Determine the missing x and y coordinates
//        int missingX = -1;
//        int missingY = -1;
//
//        for (Map.Entry<Integer, Integer> entry : xCount.entrySet()) {
//            if (entry.getValue() % 2 != 0) {
//                missingX = entry.getKey();
//                break;
//            }
//        }
//
//        for (Map.Entry<Integer, Integer> entry : yCount.entrySet()) {
//            if (entry.getValue() % 2 != 0) {
//                missingY = entry.getKey();
//                break;
//            }
//        }
//
//        // Add missing coordinates to the result list
//        result.add(missingX);
//        result.add(missingY);
//
//        return result;
//    }
//
//    public static void main(String[] args) {
//        // Example usage
//        List<List<Integer>> locations = new ArrayList<>();
//        locations.add(Arrays.asList(1, 2));
//        locations.add(Arrays.asList(3, 4));
//        locations.add(Arrays.asList(1, 4));
//        // locations.add(Arrays.asList(3, 2)); // Uncomment to simulate a missing prisoner
//
//        List<Integer> missingCoordinates = missingPrisoner(locations);
//        System.out.println("Missing Prisoner Coordinates: " + missingCoordinates);
//    }
//}
