// FlightModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Vec3d;
import net.omkov.lepton.events.UpdateListener;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.module.Module;

public class FlightModule extends Module implements UpdateListener {
	@Override
	public void onEnable() {
		Lepton.CS.eventManager.add(UpdateListener.class, this);
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.flight.enable"), true);
	}
	
	@Override
	public void onDisable() {
		Lepton.CS.eventManager.remove(UpdateListener.class, this);
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
