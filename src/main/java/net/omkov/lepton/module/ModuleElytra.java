// ModuleElytra.java
// Elytra module class for Lepton
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.module;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ModuleElytra extends Module {
	@Override
	public void onEnable() {
		MC.player.sendMessage(new TranslatableText("message.lepton.elytra.enable"), true);
	}
	
	@Override
	public void onDisable() {
		MC.player.sendMessage(new TranslatableText("message.lepton.elytra.disable"), true);
	}
	
	@Override
	public void onUpdate() {
		if (MC.player == null) { return; }
		
		if (MC.player.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA) { return; }
		if (!MC.player.isFallFlying()) { return; }
		
		float yaw = (float)Math.toRadians(MC.player.getYaw());
		Vec3d vfw = new Vec3d(-MathHelper.sin(yaw) * 0.05f, 0, MathHelper.cos(yaw) * 0.05f);
		Vec3d vel = MC.player.getVelocity();
		
		if (MC.options.keyForward.isPressed()) { MC.player.setVelocity(vel.add(vfw)); }
		if (MC.options.keyBack.isPressed()) { MC.player.setVelocity(vel.subtract(vfw)); }
	}
}
