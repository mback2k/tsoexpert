package de.uxnr.tsoexpert.model.grid;

import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.XMLHandler;

public class IsoGridPosition extends GridPosition {
	private static Integer isoGridWidth;
	private static Integer isoGridHeight;

	private final int position;
	private final int offsetX;
	private final int offsetY;

	public IsoGridPosition(int position) {
		this(position, 0, 0);
	}
	
	public IsoGridPosition(int position, int offsetX, int offsetY) {
		this.position = position;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public int getX() {
		return (int) (((this.position % 64) - 0.5 + ((Math.floor(this.position / 64) % 2) / 2)) * IsoGridPosition.getIsoGridWidth()) + this.offsetX;
	}

	public int getY() {
		return (int) ((Math.floor(this.position / 64) + 1) * (IsoGridPosition.getIsoGridHeight() / 2)) + this.offsetY;
	}

	public Layer getLayer() {
		return Layer.LANDSCAPE;
	}

	protected static synchronized int getIsoGridWidth() {
		if (IsoGridPosition.isoGridWidth == null) {
			IsoGridPosition.isoGridWidth = GameSetting.getNumber(((XMLHandler) TSOExpert.getHandler("XMLHandler")).getDocument(GameSetting.gfx_settings), "//Globals/IsoGrid/@w").intValue();
		}
		if (IsoGridPosition.isoGridWidth != null) {
			return IsoGridPosition.isoGridWidth.intValue();
		}
		return 0;
	}

	protected static synchronized int getIsoGridHeight() {
		if (IsoGridPosition.isoGridHeight == null) {
			IsoGridPosition.isoGridHeight = GameSetting.getNumber(((XMLHandler) TSOExpert.getHandler("XMLHandler")).getDocument(GameSetting.gfx_settings), "//Globals/IsoGrid/@h").intValue();
		}
		if (IsoGridPosition.isoGridHeight != null) {
			return IsoGridPosition.isoGridHeight.intValue();
		}
		return 0;
	}
}
