// Module.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.module;

import net.minecraft.client.MinecraftClient;

/** The Module abstract class provides a base for modules. */
public abstract class Module {
	protected static final MinecraftClient MC = MinecraftClient.getInstance();
	
	private boolean enabled = false;
	
	/** Return true if the module is enabled. */
	public final boolean isEnabled() { return enabled; }
	
	/** Enable or disable the module. */
	public final void setEnabled(boolean enabled) {
		if (this.enabled == enabled) { return; } this.enabled = enabled;
		if (enabled) { onEnable(); } else { onDisable(); }
	}
	
	/** Toggle the enabled state of the module. */
	public final void toggle() { setEnabled(!enabled); }
	
	/** Called when the module has been enabled. */
	protected void onEnable() {};
	
	/** Called when the module has been disabled. */
	protected void onDisable() {};
	
	/** Called on game update. */
	protected void onUpdate() {};
}
