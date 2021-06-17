// Lepton.java
// Main source file for Lepton
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/** The Lepton singleton stores global mod data. */
public class Lepton implements ClientModInitializer {
	public static KeyBinding bindZoom = null;
	
	public static final double zoomFactor = 4.0;
	public static boolean zoom = false;
	public static boolean zoomSmoothCamera = false;
	
	@Override
	public void onInitializeClient() {
		bindZoom = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lepton.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "key.categories.misc"));
	}
}
