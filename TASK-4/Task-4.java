import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 * This example program shows how AES encryption and decryption can be done in
 * java
 * Please note that secret key and encrypted text is unreadable binary and hence
 * in the following program we display it in hexadecimal format of the
 * underlying bytes.
 */

class AESEncryption {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(" Enter Text to be Encrypted::");
        String plainText = br.readLine();
        SecretKey secKey = getSecretEncryptionKey();
        byte[] cipherText = encryptText(plainText, secKey);
        String decryptedText = decryptText(cipherText, secKey);
        System.out.println("Original Text:" + plainText);
        System.out.println("AES Key (Hex Form):" + bytesToHex(secKey.getEncoded()));
        System.out.println("Encrypted Text (Hex Form):" + bytesToHex(cipherText));
        System.out.println("Descrypted Text:" + decryptedText);
    }

    // /**
    // * gets the AES encryption key. In your actual programs, this should be
    // * safely stored.
    // *
    // * @return
    // * @throws Exception
    // */
    public static SecretKey getSecretEncryptionKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return secKey;

    }

    // /**
    // * Encrypts plainText in AES using the secret key
    // *
    // * @param plainText
    // * @param secKey
    // * @return
    // * @throws Exception
    // */

    public static byte[] encryptText(String plainText, SecretKey secKey) throws Exception {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }

    // /**
    // * Decrypts encrypted byte array using the key used for encryption.
    // *
    // * @param byteCipherText
    // * @param secKey
    // * @return
    // * @throws Exception
    // */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }

    private static String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}
