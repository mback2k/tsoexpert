package de.uxnr.tsoexpert.game;

import java.io.IOException;

import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;

public class ZoneHandler implements DataHandler<ZoneVO> {
	@Override
	public void handleData(ZoneVO data) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(data.getZoneMapName());
	}
}
