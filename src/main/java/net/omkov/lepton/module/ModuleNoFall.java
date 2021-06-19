// ModuleNoFall.java
// NoFall module class for Lepton
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.module;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;
import net.minecraft.text.LiteralText;
import net.omkov.lepton.Lepton;

public class ModuleNoFall extends Module {
	@Override
	public void onEnable() {
		Lepton.MC.player.sendMessage(new LiteralText("NoFall enabled"), false);
	}
	
	@Override
	public void onDisable() {
		Lepton.MC.player.sendMessage(new LiteralText("NoFall disabled"), false);
	}
	
	@Override
	public void onUpdate() {
		ClientPlayerEntity p = Lepton.MC.player;
		
		if (p.fallDistance <= (p.isFallFlying() ? 1.0f : 2.0f)) { return; }
		if (p.isFallFlying() && p.isSneaking() && (p.getVelocity().y < -0.5)) { return; }
		
		p.networkHandler.sendPacket(new OnGroundOnly(true));
	}
}
