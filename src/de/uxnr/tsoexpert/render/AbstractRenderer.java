package de.uxnr.tsoexpert.render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import org.w3c.dom.Document;

import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.resource.GameSetting;
import de.uxnr.tsoexpert.resource.SpriteHandler;
import de.uxnr.tsoexpert.resource.XMLHandler;

public abstract class AbstractRenderer {
	public enum Mode {
		HIDE, SHOW, DEBUG
	}

	private final XMLHandler xmlHandler;
	private final SpriteHandler spriteHandler;

	private Document gfxSettingsDoc;
	private Mode mode = Mode.SHOW;
	
	private Double backgroundGridWidth;
	private Double backgroundGridHeight;
	private Double isoGridWidth;
	private Double isoGridHeight;

	public AbstractRenderer() {
		this.xmlHandler = (XMLHandler) TSOExpert.getHandler("XMLHandler");
		this.spriteHandler = (SpriteHandler) TSOExpert.getHandler("SpriteHandler");
	}
	
	protected Document getGfxSettings() {
		if (this.gfxSettingsDoc == null) {
			this.gfxSettingsDoc = this.xmlHandler.getDocument(GameSetting.gfx_settings);
		}
		if (this.gfxSettingsDoc == null) {
			throw new RuntimeException("GFX settings not available");
		}
		return this.gfxSettingsDoc;
	}

	protected int getBackgroundGridWidth() {
		if (this.backgroundGridWidth == null)
			this.backgroundGridWidth = GameSetting.getNumber(this.getGfxSettings(), "//Globals/BackgroundGrid/@w");
		if (this.backgroundGridWidth != null)
			return this.backgroundGridWidth.intValue();
		return 0;
	}

	protected int getBackgroundGridHeight() {
		if (this.backgroundGridHeight == null)
			this.backgroundGridHeight = GameSetting.getNumber(this.getGfxSettings(), "//Globals/BackgroundGrid/@h");
		if (this.backgroundGridHeight != null)
			return this.backgroundGridHeight.intValue();
		return 0;
	}

	protected int getIsoGridWidth() {
		if (this.isoGridWidth == null)
			this.isoGridWidth = GameSetting.getNumber(this.getGfxSettings(), "//Globals/IsoGrid/@w");
		if (this.isoGridWidth != null)
			return this.isoGridWidth.intValue();
		return 0;
	}

	protected int getIsoGridHeight() {
		if (this.isoGridHeight == null)
			this.isoGridHeight = GameSetting.getNumber(this.getGfxSettings(), "//Globals/IsoGrid/@h");
		if (this.isoGridHeight != null)
			return this.isoGridHeight.intValue();
		return 0;
	}

	protected String getSpriteFilename(String xpath) {
		return GameSetting.getString(this.getGfxSettings(), xpath);
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
