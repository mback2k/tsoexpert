package de.uxnr.tsoexpert.game.communication.vo.update;

import de.uxnr.amf.v3.AMF3_Object;
import de.uxnr.tsoexpert.game.communication.vo.SpecialistVO;

public class TravellingSpecialistArivalVO extends AMF3_Object {
	private SpecialistVO specialistVO;

	public SpecialistVO getSpecialistVO() {
		return this.specialistVO;
	}
}
