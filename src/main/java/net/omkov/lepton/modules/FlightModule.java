// FlightModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.module.Module;

public class FlightModule extends Module {
	public FlightModule() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (Lepton.bindings.flight.wasPressed()) { toggle(); }
			if (isEnabled() && MC.player != null) { onUpdate(); }
		});
	}
	
	@Override
	public void onEnable() {
		MC.player.sendMessage(Text.translatable("message.lepton.flight.enable"), true);
	}
	
	@Override
	public void onDisable() {
		MC.player.sendMessage(Text.translatable("message.lepton.flight.disable"), true);
	}
	
	@Override
	public void onUpdate() {
		ClientPlayerEntity p = MC.player;
		
		p.getAbilities().flying = false;
		p.airStrafingSpeed = 1.0f;
		
		p.setVelocity(0.0, 0.0, 0.0);
		Vec3d vel = p.getVelocity();
		
		if (MC.options.jumpKey.isPressed()) { p.setVelocity(vel.add(0.0, 1.0, 0.0)); }
		if (MC.options.sneakKey.isPressed()) { p.setVelocity(vel.subtract(0.0, 1.0, 0.0)); }
	}
}
