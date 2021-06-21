// NoFallModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;
import net.minecraft.text.TranslatableText;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.events.UpdateListener;
import net.omkov.lepton.module.Module;

public class NoFallModule extends Module implements UpdateListener {
	@Override
	public void onEnable() {
		Lepton.CS.eventManager.add(UpdateListener.class, this);
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.nofall.enable"), true);
	}
	
	@Override
	public void onDisable() {
		Lepton.CS.eventManager.remove(UpdateListener.class, this);
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
