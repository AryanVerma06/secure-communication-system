# Secure Communication System

A secure, session-based client-server communication system implemented in Java, featuring RSA-based key exchange and AES-encrypted messaging for confidential and authenticated data transmission.

## Features

- **RSA Key Exchange**: Secure asymmetric encryption for initial key sharing
- **AES Encryption**: Symmetric encryption for fast, secure message communication
- **Digital Signatures**: Ensures message integrity and authentication
- **Hashing Utilities**: Supports secure hashing for data verification
- **Persistent Connection**: Maintains a stable client-server session
- **Custom Crypto Library**: Built-in implementations of cryptographic algorithms

## Tech Stack

- **Java** (JDK 8 or higher)
- **Custom Cryptography Classes**:
  - AESUtil: AES encryption/decryption
  - RSAUtil: RSA key generation and encryption
  - DigitalSignatureUtil: Digital signing and verification
  - HashUtil: Hashing functions

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Command line interface (Windows Command Prompt, PowerShell, or terminal)

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/AryanVerma06/secure-communication-system.git
   cd secure-communication-system
   ```

2. **Compile the project**:
   ```bash
   javac -encoding UTF-8 -d src src\crypto\*.java src\model\*.java src\network\*.java src\util\*.java src\main\*.java
   ```
   *Note: On Unix-based systems, use forward slashes: `src/crypto/*.java` etc.*

## Usage

1. **Start the Server**:
   ```bash
   java -cp src main.ServerMain
   ```
   The server will start listening on `localhost:5000`

2. **Start the Client** (in a separate terminal):
   ```bash
   java -cp src main.ClientMain
   ```
   The client will connect to the server and establish a secure session

3. **Communication**:
   - Messages sent between client and server are automatically encrypted using AES
   - RSA is used for initial secure key exchange
   - Digital signatures ensure message authenticity

## Project Structure

```
src/
├── crypto/          # Cryptographic utilities
│   ├── AESUtil.java
│   ├── DigitalSignatureUtil.java
│   ├── HashUtil.java
│   └── RSAUtil.java
├── main/            # Main entry points
│   ├── ClientMain.java
│   └── ServerMain.java
├── model/           # Data models
│   ├── KeyPacket.java
│   └── SecureMessage.java
├── network/         # Network communication
│   ├── SecureClient.java
│   ├── SecureServer.java
│   └── SecureSocketHandler.java
└── util/            # Utilities
    └── Constants.java
```

## Security Features

- **End-to-End Encryption**: All messages are encrypted using AES
- **Key Exchange**: RSA ensures secure initial key distribution
- **Authentication**: Digital signatures prevent tampering
- **Session-Based**: Each connection establishes its own encryption keys

## Cleanup

To remove compiled `.class` files:
```bash
# On Windows PowerShell
Get-ChildItem -Recurse -Filter *.class | Remove-Item

# On Unix/Linux
find . -name "*.class" -delete
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.