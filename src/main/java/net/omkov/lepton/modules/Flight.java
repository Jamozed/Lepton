// Flight.java
// Flight module for Lepton
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.modules;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Vec3d;
import net.omkov.lepton.Lepton;

public class Flight {
	private static boolean enabled = false;
	
	public static boolean isEnabled() { return enabled; }
	
	public static void setEnabled(boolean enabled) {
		if (Flight.enabled == enabled) { return; }
		else { Flight.enabled = enabled; }
		
		if (enabled) { onEnable(); }
		else { onDisable(); }
	}
	
	public static void onEnable() {
		Lepton.MC.player.sendMessage(new LiteralText("Flight enabled"), false);
	}
	
	public static void onDisable() {
		Lepton.MC.player.sendMessage(new LiteralText("Flight disabled"), false);
	}
	
	public static void onUpdate() {
		ClientPlayerEntity player = Lepton.MC.player;
		
		player.getAbilities().flying = false;
		player.flyingSpeed = 1.0f;
		
		player.setVelocity(0, 0, 0);
		Vec3d velocity = player.getVelocity();
		
		if(Lepton.MC.options.keyJump.isPressed()) {
			player.setVelocity(velocity.add(0, 1.0f, 0));
		}
		
		if(Lepton.MC.options.keySneak.isPressed()) {
			player.setVelocity(velocity.subtract(0, 1.0f, 0));
		}
	}
}
