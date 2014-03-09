package de.uxnr.tsoexpert.proxy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.uxnr.proxy.Headers;
import de.uxnr.proxy.HostHandler;
import de.uxnr.tsoexpert.TSOHandler;
import de.uxnr.tsoexpert.file.FileData;
import de.uxnr.tsoexpert.file.FileDecrypter;

public class StaticHandler implements TSOHandler, HostHandler {
  public static final String RESOURCE_PREFIX = "SWMMO/";
  public static final String RESOURCE_PATH = "res/";

  private final Map<String, IResourceHandler> resourceHandlers =
      new HashMap<String, IResourceHandler>();
  private final Map<String, FileData> fileCache = new HashMap<String, FileData>();

  private final FileDecrypter fileDecrypter;

  private final boolean holdFiles = true;
  private final boolean saveFiles = false;

  public StaticHandler() throws IOException {
    this.fileDecrypter = new FileDecrypter();
  }

  @Override
  public void handleRequest(String requestMethod, URI requestURI, Headers requestHeaders,
      InputStream body) throws IOException {
    // Nothing to do here
  }

  @Override
  public void handleResponse(String requestMethod, URI requestURI, Headers requestHeaders,
      Headers responseHeaders, InputStream body) throws IOException {

    String path = requestURI.getPath();
    int prefix = path.indexOf(RESOURCE_PREFIX);

    if (prefix > 0) {
      prefix += RESOURCE_PREFIX.length();
      path = path.substring(prefix);
    }

    if (body.available() > 0) {
      FileData fd = new FileData(path, body);
      if (path.endsWith("_enc")) {
        fd = this.fileDecrypter.decryptFile(fd);
      }
      if (this.saveFiles) {
        this.saveResource(fd);
      }
      this.handleResource(fd);
    }
  }

  private void saveResource(FileData fd) throws IOException {
    File file = new File(RESOURCE_PATH, fd.getPath());
    file.getParentFile().mkdirs();

    ByteArrayInputStream input = fd.getInputStream();
    FileOutputStream output = new FileOutputStream(file);

    int length = input.available();
    byte[] bytearr = new byte[length];
    while ((length = input.read(bytearr)) != -1) {
      output.write(bytearr, 0, length);
    }
    output.close();
  }

  public synchronized void handleResource(FileData fd) throws IOException {
    String path = fd.getPath();

    if (this.holdFiles) {
      this.fileCache.put(path, fd);
    }

    for (Entry<String, IResourceHandler> handler : this.resourceHandlers.entrySet()) {
      if (path.matches(handler.getKey())) {
        try {
          handler.getValue().handleResource(fd);
        } catch (Exception e) {
          throw new IOException(e);
        }
      }
    }
  }

  public synchronized void addResourceHandler(String path, IResourceHandler resourceHandler) {
    this.resourceHandlers.put(path, resourceHandler);
  }

  public synchronized void removeResourceHandler(String path) {
    this.resourceHandlers.remove(path);
  }

  public synchronized FileData getFile(String path) {
    return this.fileCache.get(path);
  }

  public synchronized FileData purgeFile(String path) {
    return this.fileCache.remove(path);
  }

  public synchronized int getTotalFileCount() {
    return this.fileCache.size();
  }

  public synchronized long getTotalFileSize() {
    long size = 0;
    for (FileData fileData : this.fileCache.values()) {
      size += fileData.length();
    }
    return size;
  }
}
