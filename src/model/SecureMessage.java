package model;

import java.io.Serializable;

public class SecureMessage implements Serializable {

    private final byte[] encryptedData;
    private final byte[] signature;

    public SecureMessage(byte[] encryptedData, byte[] signature) {
        this.encryptedData = encryptedData;
        this.signature = signature;
    }

    public byte[] getEncryptedData() {
        return encryptedData;
    }

    public byte[] getSignature() {
        return signature;
    }
}