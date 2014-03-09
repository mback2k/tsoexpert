package de.uxnr.tsoexpert.game.communication;

import de.uxnr.amf.AMF;

public class Communication {
  private static String[][] PACKAGE_MAPPINGS =
      new String[][] {
          {"defaultGame.Communication.VO.d", "de.uxnr.tsoexpert.game.communication.vo."},
          {"defaultGame.Communication.VO.Guild.d", "de.uxnr.tsoexpert.game.communication.vo.guild."},
          {"defaultGame.Communication.VO.Mail.d", "de.uxnr.tsoexpert.game.communication.vo.mail."},
          {"defaultGame.Communication.VO.UpdateVO.d",
              "de.uxnr.tsoexpert.game.communication.vo.update."}};

  public static void register() {
    for (String[] mapping : Communication.PACKAGE_MAPPINGS) {
      AMF.registerClassMapping(mapping[0], mapping[1]);
    }
  }
}
