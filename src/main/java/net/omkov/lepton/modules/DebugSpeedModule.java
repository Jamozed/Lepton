// SpeedModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Vec3d;
import net.omkov.lepton.events.UpdateListener;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.module.Module;

public class DebugSpeedModule extends Module implements UpdateListener {
	@Override
	public void onEnable() { Lepton.CS.eventManager.add(UpdateListener.class, this); }
	
	@Override
	public void onDisable() { Lepton.CS.eventManager.remove(UpdateListener.class, this); }
	
	@Override
	public void onUpdate() {
		Vec3d vel = Lepton.MC.player.getVelocity();
		Lepton.MC.player.sendMessage(new LiteralText(String.valueOf(vel.length())), true);
	}
}
