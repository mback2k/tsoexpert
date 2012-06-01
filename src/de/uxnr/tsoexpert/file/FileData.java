package de.uxnr.tsoexpert.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileData extends File {
	private static final long serialVersionUID = -7204196626218042143L;

	private final byte[] bytearr;

	public FileData(String pathname, byte[] bytearr) {
		super(pathname);
		this.bytearr = bytearr;
	}

	public FileData(String pathname, InputStream input) throws IOException {
		this(pathname, input, input.available());
	}

	public FileData(String pathname, InputStream input, int size) throws IOException {
		super(pathname);
		this.bytearr = new byte[size];
		if (input.read(bytearr) != size) {
			throw new IOException("Not enough bytes available");
		}
	}

	public long length() {
		return this.bytearr.length;
	}

	public byte[] getBytes() {
		return this.bytearr;
	}

	public ByteArrayInputStream getInputStream() {
		return new ByteArrayInputStream(this.bytearr);
	}
}
