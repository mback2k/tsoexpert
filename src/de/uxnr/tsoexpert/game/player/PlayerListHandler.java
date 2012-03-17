package de.uxnr.tsoexpert.game.player;

import java.io.IOException;

import de.uxnr.tsoexpert.game.communication.vo.PlayerListVO;
import de.uxnr.tsoexpert.proxy.IDataHandler;

public class PlayerListHandler implements IDataHandler<PlayerListVO> {
	@Override
	public void handleData(PlayerListVO data) throws IOException {
		// TODO Auto-generated method stub
		// System.out.println(data.getPlayers());
	}
}
