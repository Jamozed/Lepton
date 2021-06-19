// InputHandler.java
// Input handler for Lepton
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.omkov.lepton.modules.Flight;

/** The InputHandler class handles bindings and inputs. */
public class InputHandler {
	private static final InputHandler INSTANCE = new InputHandler();
	
	public KeyBinding bZoom = null;
	public KeyBinding bFly = null;
	
	/** Construct an InputHandler object. */
	private InputHandler() {}
	
	/** Get the InputHandler instance. */
	public static InputHandler getInstance() { return INSTANCE; }
	
	/** Initialise the InputHandler. */
	public void init() { initBindings(); initHandlers(); }
	
	/** Initialise key bindings. */
	private void initBindings() {
		bZoom = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lepton.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C,       "key.categories.misc"));
		bFly  = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lepton.fly",  InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.categories.cheats"));
	}
	
	/** Initialise input handlers. */
	private void initHandlers() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (bFly.wasPressed()) { Flight.setEnabled(!Flight.isEnabled()); }
			if (Flight.isEnabled()) { Flight.onUpdate(); }
		});
	}
}
