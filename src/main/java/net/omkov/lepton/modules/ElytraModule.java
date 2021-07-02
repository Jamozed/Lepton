// ElytraModule.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.omkov.lepton.Lepton;
import net.omkov.lepton.module.Module;

public class ElytraModule extends Module {
	private static final double multiplier = 0.1;
	
	public ElytraModule() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (Lepton.bindings.elytra.wasPressed()) { toggle(); }
			if (isEnabled()) { onUpdate(); }
		});
	}
	
	@Override
	public void onEnable() {
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.elytra.enable"), true);
	}
	
	@Override
	public void onDisable() {
		Lepton.MC.player.sendMessage(new TranslatableText("message.lepton.elytra.disable"), true);
	}
	
	@Override
	public void onUpdate() {
		if (Lepton.MC.player.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA) { return; }
		if (!Lepton.MC.player.isFallFlying()) { return; }
		
		float pitch = (float)Math.toRadians(Lepton.MC.player.getPitch());
		float yaw = (float)Math.toRadians(Lepton.MC.player.getYaw());
		
		float x = -MathHelper.sin(yaw) * MathHelper.cos(pitch);
		float y = -MathHelper.sin(pitch);
		float z = MathHelper.cos(yaw) * MathHelper.cos(pitch);
		
		Vec3d vec = new Vec3d(x * multiplier, y * multiplier, z * multiplier);
		Vec3d vel = Lepton.MC.player.getVelocity();
		
		if (vel.length() < 4.2) {
			if (Lepton.MC.options.keyForward.isPressed()) { Lepton.MC.player.setVelocity(vel.add(vec)); }
			if (Lepton.MC.options.keyBack.isPressed()) { Lepton.MC.player.setVelocity(vel.subtract(vec)); }
		}
	}
}
