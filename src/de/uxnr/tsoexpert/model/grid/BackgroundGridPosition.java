package de.uxnr.tsoexpert.model.grid;

import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class BackgroundGridPosition extends GridPosition {
	private static Integer backgroundGridWidth;
	private static Integer backgroundGridHeight;

	private final int position;

	public BackgroundGridPosition(int position) {
		this.position = position;
	}

	public int getX() {
		return (int) ((this.position % 34) * BackgroundGridPosition.getBackgroundGridWidth());
	}

	public int getY() {
		return (int) ((Math.round(this.position / 34)) * BackgroundGridPosition.getBackgroundGridHeight());
	}

	protected static synchronized int getBackgroundGridWidth() {
		if (BackgroundGridPosition.backgroundGridWidth == null) {
			BackgroundGridPosition.backgroundGridWidth = GameSetting.getNumber(((XMLHandler) TSOExpert.getHandler("XMLHandler")).getDocument(GameSetting.gfx_settings), "//Globals/BackgroundGrid/@w").intValue();
		}
		if (BackgroundGridPosition.backgroundGridWidth != null) {
			return BackgroundGridPosition.backgroundGridWidth.intValue();
		}
		return 0;
	}

	protected static synchronized int getBackgroundGridHeight() {
		if (BackgroundGridPosition.backgroundGridHeight == null) {
			BackgroundGridPosition.backgroundGridHeight = GameSetting.getNumber(((XMLHandler) TSOExpert.getHandler("XMLHandler")).getDocument(GameSetting.gfx_settings), "//Globals/BackgroundGrid/@h").intValue();
		}
		if (BackgroundGridPosition.backgroundGridHeight != null) {
			return BackgroundGridPosition.backgroundGridHeight.intValue();
		}
		return 0;
	}
}
