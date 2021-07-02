// FlightModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Vec3d;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.module.Module;

public class FlightModule extends Module {
	public FlightModule() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (Lepton.bindings.flight.wasPressed()) { toggle(); }
			if (isEnabled()) { onUpdate(); }
		});
	}
	
	@Override
	public void onEnable() {
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.flight.enable"), true);
	}
	
	@Override
	public void onDisable() {
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.flight.disable"), true);
	}
	
	@Override
	public void onUpdate() {
		ClientPlayerEntity p = Lepton.MC.player;
		
		p.getAbilities().flying = false;
		p.flyingSpeed = 1.0f;
		
		p.setVelocity(0.0, 0.0, 0.0);
		Vec3d vel = p.getVelocity();
		
		if (Lepton.MC.options.keyJump.isPressed()) { p.setVelocity(vel.add(0.0, 1.0, 0.0)); }
		if (Lepton.MC.options.keySneak.isPressed()) { p.setVelocity(vel.subtract(0.0, 1.0, 0.0)); }
	}
}
