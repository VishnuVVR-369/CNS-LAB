import java.security.*;
import java.util.Base64;

class DigSign {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keyPair = kpg.genKeyPair();
        byte[] data = "Sample Text".getBytes("UTF8");
        Signature sig = Signature.getInstance("MD5WithRSA");
        sig.initSign(keyPair.getPrivate());
        sig.update(data);
        byte[] signatureBytes = sig.sign();
        System.out.println("Signature: " + Base64.getEncoder().encode(signatureBytes));
        sig.initVerify(keyPair.getPublic());
        sig.update(data);
        System.out.println(sig.verify(signatureBytes));
    }
}
