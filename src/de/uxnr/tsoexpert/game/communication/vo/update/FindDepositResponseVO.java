package de.uxnr.tsoexpert.game.communication.vo.update;

import java.util.Collection;

import de.uxnr.amf.v3.AMF3_Object;

public class FindDepositResponseVO extends AMF3_Object {
	private Collection<FoundDepositVO> foundDeposits_vector;

	public Collection<FoundDepositVO> getFoundDeposits_vector() {
		return this.foundDeposits_vector;
	}
}
