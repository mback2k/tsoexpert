package de.uxnr.tsoexpert.game;

import java.io.IOException;

import de.uxnr.tsoexpert.TSOExpert;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.ui.MainWindow;

public class ZoneHandler implements IDataHandler<ZoneVO> {
	private static MainWindow window;

	public static void setWindow(MainWindow window) {
		ZoneHandler.window = window;
	}

	@Override
	public void handleData(ZoneVO data) throws IOException {
		final ZoneVO zoneVO = data;
		if (ZoneHandler.window != null) {
			TSOExpert.run(new Runnable() {
				@Override
				public void run() {
					ZoneHandler.window.setZoneVO(zoneVO);
				}
			});
		}
	}
}
