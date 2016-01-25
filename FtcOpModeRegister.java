package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

public class FtcOpModeRegister implements OpModeRegister {

  public void register(OpModeManager manager) {

    manager.register("Nullop", NullOp.class);
    manager.register("TeleOpOld", TeleOpOld.class);
    manager.register("AutoOp", AutoOp.class);

  }
}
