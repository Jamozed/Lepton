// LeptonConfig.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.ConfigData;

@Config(name = "alum")
public class LeptonConfig implements ConfigData {
	public double elytraSpeed = 4.2;
}
