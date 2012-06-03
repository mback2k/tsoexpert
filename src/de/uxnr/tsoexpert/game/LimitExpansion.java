package de.uxnr.tsoexpert.game;

import org.w3c.dom.Node;

public class LimitExpansion implements Parsable {
	private Building building;
	private int amount;

	public LimitExpansion() {

	}

	public LimitExpansion(Building building, int amount) {
		this.building = building;
		this.amount = amount;
	}

	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void parse(Node node) throws InvalidGameSettingsException {
		Building b = null;
		int amount = 0;
		for (int y = 0; y < node.getAttributes().getLength(); y++) {
			Node attribute = node.getAttributes().item(y);
			if (attribute.getNodeName().equalsIgnoreCase("ByBuilding")) {
				b = Building.getByName(attribute.getNodeValue());
			} else if (attribute.getNodeName().equalsIgnoreCase("Amount")) {
				amount = Integer.parseInt(attribute.getNodeValue());
			}
		}

		if (b == null) {
			throw new InvalidGameSettingsException(
					"Invalide ExpandMaxLimit Node");
		}
		this.building = b;
		this.amount = amount;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Limit expanded by ");
		sb.append(this.building.toString());
		sb.append(" with an amount of ");
		sb.append(this.amount);
		return sb.toString();
	}
}
