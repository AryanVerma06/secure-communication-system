package model;

import java.io.Serializable;

public class KeyPacket implements Serializable {
    private final byte[] encryptedAESKey;

    public KeyPacket(byte[] encryptedAESKey) {
        this.encryptedAESKey = encryptedAESKey;
    }

    public byte[] getEncryptedAESKey() {
        return encryptedAESKey;
    }
}