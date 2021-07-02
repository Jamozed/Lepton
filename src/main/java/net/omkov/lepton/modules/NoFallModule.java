// NoFallModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;
import net.minecraft.text.TranslatableText;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.module.Module;

public class NoFallModule extends Module {
	public NoFallModule() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (Lepton.bindings.nofall.wasPressed()) { toggle(); }
			if (isEnabled()) { onUpdate(); }
		});
	}
	
	@Override
	public void onEnable() {
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.nofall.enable"), true);
	}
	
	@Override
	public void onDisable() {
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.nofall.disable"), true);
	}
	
	@Override
	public void onUpdate() {
		ClientPlayerEntity p = Lepton.MC.player;
		
		if (p.fallDistance <= (p.isFallFlying() ? 1.0f : 2.0f)) { return; }
		if (p.isFallFlying() && p.isSneaking() && (p.getVelocity().y < -0.5)) { return; }
		
		p.networkHandler.sendPacket(new OnGroundOnly(true));
	}
}
