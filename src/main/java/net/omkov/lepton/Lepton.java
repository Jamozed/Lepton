// Lepton.java
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.omkov.lepton.event.EventManager;
import net.omkov.lepton.modules.ElytraModule;
import net.omkov.lepton.modules.FlightModule;
import net.omkov.lepton.modules.NoFallModule;

import org.lwjgl.glfw.GLFW;

/** The Lepton singleton provides global data storage. */
public final class Lepton {
	public static final Lepton CS = new Lepton(); private Lepton() {}
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	
	public EventManager eventManager;
	
	public BindList binds;
	public ModuList modus;
	
	/** Initialise the Lepton singleton. */
	public void initialize() {
		eventManager = new EventManager();
		
		binds = new BindList();
		modus = new ModuList();
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (binds.elytra.wasPressed()) { modus.elytraModule.toggle(); }
			while (binds.flight.wasPressed()) { modus.flightModule.toggle(); }
			while (binds.nofall.wasPressed()) { modus.nofallModule.toggle(); }
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
		public final ElytraModule elytraModule = new ElytraModule();
		public final FlightModule flightModule = new FlightModule();
		public final NoFallModule nofallModule = new NoFallModule();
	}
}
