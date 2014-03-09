package de.uxnr.tsoexpert.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

public class FileDecrypter {
  public static final String PRIVATE_KEY_FILE = "PRIVATE_KEY";

  private final byte[] keyarr;
  private final Key key;

  public FileDecrypter() throws IOException {
    this.keyarr = this.readPrivateKey();
    this.key = new SecretKeySpec(this.keyarr, "Blowfish");
  }

  public FileData decryptFile(FileData fd) throws IOException {
    ByteArrayInputStream input = fd.getInputStream();
    ByteArrayOutputStream output = new ByteArrayOutputStream();

    BASE64Decoder base64 = new BASE64Decoder();
    byte[] bytearr = base64.decodeBuffer(input);
    input = new ByteArrayInputStream(bytearr);

    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
    } catch (Exception e) {
      throw new IOException(e);
    }

    try {
      cipher.init(Cipher.DECRYPT_MODE, key);
    } catch (Exception e) {
      throw new IOException(e);
    }

    int length = -1;
    CipherInputStream stream = new CipherInputStream(input, cipher);
    while ((length = stream.read(bytearr)) != -1) {
      output.write(bytearr, 0, length);
    }
    output.close();
    stream.close();

    String path = fd.getPath().replaceAll("\\.(.*)_enc", "\\.$1");
    return new FileData(path, output.toByteArray());
  }

  private byte[] readPrivateKey() throws IOException {
    File keyFile = new File(PRIVATE_KEY_FILE);

    FileInputStream input = new FileInputStream(keyFile);
    ByteArrayOutputStream output = new ByteArrayOutputStream();

    int length = -1;
    byte[] bytearr = new byte[1024];
    while ((length = input.read(bytearr)) != -1) {
      output.write(bytearr, 0, length);
    }
    output.close();
    input.close();

    String key = new String(output.toByteArray());
    return key.trim().getBytes("UTF8");
  }

  public static void main(String[] args) throws IOException {
    File file = new File("res/GFX/game_settings.xml_enc");
    FileInputStream fileStream = new FileInputStream(file);
    FileDecrypter fileDecrypter = new FileDecrypter();
    FileData fileData = new FileData(file.getPath(), fileStream);
    fileDecrypter.decryptFile(fileData);
  }
}
