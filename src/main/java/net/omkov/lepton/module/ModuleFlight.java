// ModuleFlight.java
// Flight module class for Lepton
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.module;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Vec3d;

public class ModuleFlight extends Module {
	@Override
	public void onEnable() {
		MC.player.sendMessage(new TranslatableText("message.lepton.flight.enable"), true);
	}
	
	@Override
	public void onDisable() {
		MC.player.sendMessage(new TranslatableText("message.lepton.flight.disable"), true);
	}
	
	@Override
	public void onUpdate() {
		ClientPlayerEntity p = MC.player;
		
		if (p == null) { return; }
		
		p.getAbilities().flying = false;
		p.flyingSpeed = 1.0f;
		
		p.setVelocity(0.0, 0.0, 0.0);
		Vec3d vel = p.getVelocity();
		
		if (MC.options.keyJump.isPressed()) { p.setVelocity(vel.add(0.0, 1.0, 0.0)); }
		if (MC.options.keySneak.isPressed()) { p.setVelocity(vel.subtract(0.0, 1.0, 0.0)); }
	}
}
