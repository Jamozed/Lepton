// SpeedModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Vec3d;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.module.Module;

public class DebugSpeedModule extends Module {
	public DebugSpeedModule() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (Lepton.bindings.debug_speed.wasPressed()) { toggle(); }
			if (isEnabled()) { onUpdate(); }
		});
	}
	
	@Override
	public void onUpdate() {
		Vec3d vel = Lepton.MC.player.getVelocity();
		Lepton.MC.player.sendMessage(new LiteralText(String.valueOf(vel.length())), true);
	}
}
