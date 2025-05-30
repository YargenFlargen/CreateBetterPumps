package com.yargenflargen.createbetterpumps.config;

import net.createmod.catnip.config.ConfigBase;

public class ServerConfig extends ConfigBase {
    public final PumpStress stressValues = (PumpStress) this.nested(0, PumpStress::new, new String[]{"Create Better Pumps kinetics tuning"});

    public String getName() {
        return "server";
    }
}
