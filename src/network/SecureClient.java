package network;

import crypto.AESUtil;
import crypto.DigitalSignatureUtil;
import crypto.RSAUtil;
import model.KeyPacket;
import model.SecureMessage;
import util.Constants;

import javax.crypto.SecretKey;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

public class SecureClient {

    public void start() throws Exception {

        Socket socket =
                new Socket(Constants.SERVER_HOST,
                        Constants.SERVER_PORT);

        SecureSocketHandler handler =
                new SecureSocketHandler(socket);

        PublicKey serverPublicKey =
                (PublicKey) handler.receiveObject();

        KeyPair clientKP = RSAUtil.generateKeyPair();
        handler.sendObject(clientKP.getPublic());

        SecretKey aesKey = AESUtil.generateKey();
        handler.sendObject(new KeyPacket(
                RSAUtil.encrypt(
                        aesKey.getEncoded(),
                        serverPublicKey)));

        SecureMessage welcome =
                (SecureMessage) handler.receiveObject();

        System.out.println("Server: " +
                new String(AESUtil.decrypt(
                        welcome.getEncryptedData(),
                        aesKey)));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String msg = sc.nextLine();

            byte[] sig =
                    DigitalSignatureUtil.sign(
                            msg.getBytes(),
                            clientKP.getPrivate());

            handler.sendObject(new SecureMessage(
                    AESUtil.encrypt(msg.getBytes(), aesKey),
                    sig));

            SecureMessage reply =
                    (SecureMessage) handler.receiveObject();

            System.out.println("Server: " +
                    new String(AESUtil.decrypt(
                            reply.getEncryptedData(),
                            aesKey)));
        }
    }
}