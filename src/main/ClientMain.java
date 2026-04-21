package main;

import network.SecureClient;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        new SecureClient().start();
    }
}