package de.uxnr.tsoexpert.game;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

public class Creation implements Parsable{

	private static Map<Integer, Creation> CreationMap = new HashMap<Integer, Creation>();

	private CreationTyp typ;
	private final int id;
	private float time;
	private Resource externalResource;
	private Building building;

	private Creation(int id) {
		this.id = id;
	}

	public static Creation getById(int id) {
		Creation c = CreationMap.get(id);
		if (c == null) {
			c = new Creation(id);
		}
		return c;
	}

	@Override
	public void parse(Node node) throws InvalidGameSettingsException {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(" - ");
//		sb.append(this.typ.toString());
//		sb.append(" by ");
//		sb.append(this.building.toString());
//		sb.append(" with ");
//		sb.append(externalResource);
//		sb.append(" within ");
//		sb.append(time);
//		sb.append(" seconds");
		
		return sb.toString();
	}


}
