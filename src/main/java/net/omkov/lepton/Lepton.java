// Lepton.java
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.omkov.lepton.modules.DebugSpeedModule;
import net.omkov.lepton.modules.ElytraModule;
import net.omkov.lepton.modules.FlightModule;
import net.omkov.lepton.modules.NoFallModule;
import org.lwjgl.glfw.GLFW;

/** The Lepton singleton provides global data storage. */
public final class Lepton {
	public static final Lepton INSTANCE = new Lepton(); private Lepton() {}
	
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	public static final LeptonConfig CONFIG = AutoConfig.register(LeptonConfig.class, Toml4jConfigSerializer::new).getConfig();
	
	public static BindList bindings;
	public static ModuleList modules;
	
	/** Initialise the Lepton singleton. */
	public void init() {
		bindings = new BindList();
		modules = new ModuleList();
	}
	
	/** The BindList class stores keybindings. */
	public final class BindList {
		public final KeyBinding elytra = bind("key.lepton.elytra", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.lepton");
		public final KeyBinding flight = bind("key.lepton.flight", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.lepton");
		public final KeyBinding nofall = bind("key.lepton.nofall", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.lepton");
		
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
