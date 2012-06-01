package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.SpriteHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;

public abstract class AbstractRenderer {
	public enum Mode {
		HIDE, SHOW, DEBUG
	}

	protected final XMLHandler xmlHandler;
	protected final SpriteHandler spriteHandler;

	private Mode mode = Mode.SHOW;

	public AbstractRenderer() {
		this.xmlHandler = (XMLHandler) TSOExpert.getHandler("XMLHandler");
		this.spriteHandler = (SpriteHandler) TSOExpert.getHandler("SpriteHandler");
	}

	protected String getSpriteFilename(String xpath) {
		return GameSetting.getString(this.xmlHandler.getDocument(GameSetting.gfx_settings), xpath);
	}

	protected Sprite getSprite(String folder, String filename) {
		return this.spriteHandler.getSprite(folder, filename);
	}

	protected boolean drawImage(Graphics2D graphics, Image image, Rectangle dst, Rectangle src) {
		return graphics.drawImage(image, dst.x, dst.y, dst.x+dst.width, dst.y+dst.height, src.x, src.y, src.x+src.width, src.y+src.height, null);
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Mode getMode() {
		return this.mode;
	}
}
