package de.uxnr.tsoexpert;

import java.util.HashMap;
import java.util.Map;

public class TSORegistry {
	private final Map<String, TSOHandler> handlers = new HashMap<String, TSOHandler>();

	public void register(TSOHandler handler) {
		this.handlers.put(handler.getClass().getSimpleName(), handler);
	}

	public void unregister(TSOHandler handler) {
		this.handlers.remove(handler.getClass().getSimpleName());
	}

	public TSOHandler lookup(String handlerName) {
		return this.handlers.get(handlerName);
	}
}
