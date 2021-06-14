package net.omkov.lepton;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Lepton implements ClientModInitializer {
	public static KeyBinding bindingZoom;
	
	public static final double zoomFactor = 4.0;
	public static boolean zoom = false;
	public static boolean zoomSmoothCamera = false;
	
	@Override
	public void onInitializeClient() {
		bindingZoom = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lepton.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "key.categories.misc"));
	}
}
