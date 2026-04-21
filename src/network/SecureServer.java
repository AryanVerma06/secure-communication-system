package network;

import crypto.AESUtil;
import crypto.DigitalSignatureUtil;
import crypto.RSAUtil;
import model.KeyPacket;
import model.SecureMessage;
import util.Constants;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SecureServer {

    public void start() {
        try (ServerSocket serverSocket =
                     new ServerSocket(Constants.SERVER_PORT)) {

            System.out.println("Server started on port " + Constants.SERVER_PORT);

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            SecureSocketHandler handler = new SecureSocketHandler(socket);

            KeyPair serverKP = RSAUtil.generateKeyPair();
            handler.sendObject(serverKP.getPublic());

            PublicKey clientPublicKey =
                    (PublicKey) handler.receiveObject();

            KeyPacket packet =
                    (KeyPacket) handler.receiveObject();

            byte[] aesKeyBytes =
                    RSAUtil.decrypt(packet.getEncryptedAESKey(),
                            serverKP.getPrivate());

            SecretKey aesKey =
                    new SecretKeySpec(aesKeyBytes, "AES");

            System.out.println("AES session established");

            handler.sendObject(new SecureMessage(
                    AESUtil.encrypt(
                            "Secure session established".getBytes(),
                            aesKey),
                    null));

            while (true) {
                SecureMessage msg =
                        (SecureMessage) handler.receiveObject();

                byte[] decrypted =
                        AESUtil.decrypt(msg.getEncryptedData(), aesKey);

                boolean ok =
                        DigitalSignatureUtil.verify(
                                decrypted,
                                msg.getSignature(),
                                clientPublicKey);

                if (!ok) {
                    System.out.println("Signature verification failed");
                    continue;
                }

                String text = new String(decrypted);
                System.out.println("Client: " + text);

                handler.sendObject(new SecureMessage(
                        AESUtil.encrypt(
                                ("ACK: " + text).getBytes(),
                                aesKey),
                        null));
            }

        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }
}