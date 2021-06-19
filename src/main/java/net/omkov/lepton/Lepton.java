// Lepton.java
// Main source file for Lepton
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.omkov.lepton.module.ModuleFlight;
import net.omkov.lepton.module.ModuleNoFall;

import org.lwjgl.glfw.GLFW;

/** The Lepton class stores global mod data. */
public class Lepton implements ClientModInitializer {
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	
	public static KeyBinding bindZoom = null;
	public static KeyBinding bindFly = null;
	
	private static ModuleFlight mFlight = new ModuleFlight();
	private static ModuleNoFall mNoFall = new ModuleNoFall();
	
	public static final double zoomFactor = 4.0;
	
	public static boolean zoomEnabled = false;
	
	@Override
	public void onInitializeClient() {
		bindZoom = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lepton.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C,       "key.categories.misc"));
		bindFly  = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lepton.fly",  InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.cheats"));
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (bindFly.wasPressed()) { mFlight.toggle(); mNoFall.toggle(); }
			
			if (mFlight.isEnabled()) { mFlight.onUpdate(); }
			if (mNoFall.isEnabled()) { mNoFall.onUpdate(); }
		});
	}
}
