package de.uxnr.tsoexpert.proxy;

import java.io.IOException;

import de.uxnr.tsoexpert.file.FileData;

public interface IResourceHandler {
	public void handleResource(FileData fd) throws IOException;
}
