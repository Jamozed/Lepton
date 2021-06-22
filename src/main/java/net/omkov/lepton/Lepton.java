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
import net.omkov.lepton.modules.DebugSpeedModule;

import org.lwjgl.glfw.GLFW;

/** The Lepton singleton provides global data storage. */
public final class Lepton {
	public static final Lepton CS = new Lepton(); private Lepton() {}
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	
	public EventManager eventManager;
	
	public BindList bindList;
	public ModuleList moduleList;
	
	/** Initialise the Lepton singleton. */
	public void initialize() {
		eventManager = new EventManager();
		
		bindList = new BindList();
		moduleList = new ModuleList();
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (bindList.elytra.wasPressed()) { moduleList.elytraModule.toggle(); }
			while (bindList.flight.wasPressed()) { moduleList.flightModule.toggle(); }
			while (bindList.nofall.wasPressed()) { moduleList.nofallModule.toggle(); }
			// while (bindList.speed.wasPressed()) { moduleList.speedModule.toggle(); }
			
			while (bindList.debug_speed.wasPressed()) { moduleList.debugSpeedModule.toggle(); }
		});
	}
	
	/** The BindList class stores keybindings. */
	public final class BindList {
		public final KeyBinding elytra = bind("key.lepton.elytra", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.hacks");
		public final KeyBinding flight = bind("key.lepton.flight", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.hacks");
		public final KeyBinding nofall = bind("key.lepton.nofall", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.hacks");
		// public final KeyBinding speed  = bind("kep.lepton.speed",  InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.hacks");
		
		public final KeyBinding debug_speed = bind("key.lepton.debug_speed", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.debug");
		
		/** Construct and register a keybinding. */
		private KeyBinding bind(String key, InputUtil.Type type, int code, String category) {
			KeyBinding bind = new KeyBinding(key, type, code, category);
			return KeyBindingHelper.registerKeyBinding(bind);
		}
	}
	
	/** The ModuList class stores modules. */
	public final class ModuleList {
		public final ElytraModule elytraModule = new ElytraModule();
		public final FlightModule flightModule = new FlightModule();
		public final NoFallModule nofallModule = new NoFallModule();
		
		public final DebugSpeedModule debugSpeedModule = new DebugSpeedModule();
	}
}
