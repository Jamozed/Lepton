// GameRendererMixin.java
// GameRenderer mixin for Lepton

package net.omkov.lepton.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import net.omkov.lepton.Lepton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	private MinecraftClient mc = MinecraftClient.getInstance();
	
	@Redirect(
		method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/option/GameOptions;fov:D",
			ordinal = 0
		)
	)
	private double getFov(GameOptions options) {
		if (Lepton.bindingZoom.isPressed()) {
			if (!Lepton.zoom) {
				Lepton.zoom = true;
				Lepton.zoomSmoothCamera = options.smoothCameraEnabled;
				options.smoothCameraEnabled = true;
				
				mc.worldRenderer.scheduleTerrainUpdate();
			}
			
			return options.fov / Lepton.zoomFactor;
		}
		else if (Lepton.zoom) {
			Lepton.zoom = false;
			options.smoothCameraEnabled = Lepton.zoomSmoothCamera;
			
			mc.worldRenderer.scheduleTerrainUpdate();
		}
		
		return options.fov;
	}
}
