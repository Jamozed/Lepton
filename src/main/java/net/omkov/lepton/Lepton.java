// Lepton.java
// Main source file for Lepton
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

/** The Lepton class stores global mod data. */
public class Lepton implements ClientModInitializer {
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	public static final InputHandler IH = InputHandler.getInstance();
	
	public static final double zoomFactor = 4.0;
	
	public static boolean zoomEnabled = false;
	
	@Override
	public void onInitializeClient() { IH.init(); }
}
