package de.uxnr.tsoexpert.nlib;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BinaryStream {
	private DataInputStream stream;
	private final byte[] bytes;
	private int offset;

	public BinaryStream(InputStream stream, int offset) throws IOException {
		int length = stream.available();
		this.bytes = new byte[length];
		stream.read(this.bytes, 0, length);

		this.stream = new DataInputStream(new ByteArrayInputStream(this.bytes));
		this.stream.skip(offset);
		this.offset = offset;
	}

	public BinaryStream(InputStream stream) throws IOException {
		this(stream, 0);
	}

	public void setOffset(int offset) throws IOException {
		this.stream = new DataInputStream(new ByteArrayInputStream(this.bytes));
		this.stream.skip(offset);
		this.offset = offset;
	}

	public int getOffset() {
		return this.offset;
	}

	public int readShort() throws IOException {
		int value = Short.reverseBytes(this.stream.readShort());
		this.offset += 2;
		return value;
	}

	public int readInt() throws IOException {
		int value = Integer.reverseBytes(this.stream.readInt());
		this.offset += 4;
		return value;
	}

	public String readCStringAtPos_string(int offset) {
		int length = 0;
		for (int i = offset; i < this.bytes.length; i++) {
			if (this.bytes[i] != 0) {
				length++;
			} else {
				break;
			}
		}
		return new String(this.bytes, offset, length).intern();
	}
}
