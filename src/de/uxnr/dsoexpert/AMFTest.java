package de.uxnr.dsoexpert;

import java.io.FileInputStream;
import java.io.IOException;

import de.uxnr.amf.AMF;

public class AMFTest {
	public static void main(String[] args) throws IOException {
		FileInputStream input = new FileInputStream("2.amf");
		
		AMF amf = new AMF(input);
		
		System.out.println(amf);
	}
}
