// ModuleNoFall.java
// NoFall module class for Lepton
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.module;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;
import net.minecraft.text.TranslatableText;
import net.omkov.lepton.Lepton;

public class ModuleNoFall extends Module {
	@Override
	public void onEnable() {
		MC.player.sendMessage(new TranslatableText("message.lepton.nofall.enable"), true);
	}
	
	@Override
	public void onDisable() {
		MC.player.sendMessage(new TranslatableText("message.lepton.nofall.disable"), true);
	}
	
	@Override
	public void onUpdate() {
		ClientPlayerEntity p = Lepton.MC.player;
		
		if (p == null) { return; }
		
		if (p.fallDistance <= (p.isFallFlying() ? 1.0f : 2.0f)) { return; }
		if (p.isFallFlying() && p.isSneaking() && (p.getVelocity().y < -0.5)) { return; }
		
		p.networkHandler.sendPacket(new OnGroundOnly(true));
	}
}
