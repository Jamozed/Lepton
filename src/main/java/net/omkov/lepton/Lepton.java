// Lepton.java
// Singleton class for Lepton
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.omkov.lepton.module.ModuleElytra;
import net.omkov.lepton.module.ModuleFlight;
import net.omkov.lepton.module.ModuleNoFall;
import org.lwjgl.glfw.GLFW;

/** The Lepton singleton provides global data storage. */
public final class Lepton {
	public static final Lepton CS = new Lepton(); private Lepton() {}
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	
	public BindList binds = new BindList();
	public ModuList modus = new ModuList();
	
	/** Initialise the Lepton singleton. */
	public void initialize() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (binds.elytra.wasPressed()) { modus.elytra.toggle(); }
			while (binds.flight.wasPressed()) { modus.flight.toggle(); }
			while (binds.nofall.wasPressed()) { modus.nofall.toggle(); }
			
			if (modus.elytra.isEnabled()) { modus.elytra.onUpdate(); }
			if (modus.flight.isEnabled()) { modus.flight.onUpdate(); }
			if (modus.nofall.isEnabled()) { modus.nofall.onUpdate(); }
		});
	}
	
	/** The BindList class stores keybindings. */
	public final class BindList {
		public final KeyBinding elytra = bind("key.lepton.elytra", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.cheats");
		public final KeyBinding flight = bind("key.lepton.fly",    InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.cheats");
		public final KeyBinding nofall = bind("key.lepton.nofall", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.cheats");
		
		/** Construct and register a keybinding. */
		private KeyBinding bind(String key, InputUtil.Type type, int code, String category) {
			KeyBinding bind = new KeyBinding(key, type, code, category);
			return KeyBindingHelper.registerKeyBinding(bind);
		}
	}
	
	/** The ModuList class stores modules. */
	public final class ModuList {
		public final ModuleElytra elytra = new ModuleElytra();
		public final ModuleFlight flight = new ModuleFlight();
		public final ModuleNoFall nofall = new ModuleNoFall();
	}
}
