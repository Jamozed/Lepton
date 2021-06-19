// GameRendererMixin.java
// GameRenderer mixin for Lepton
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import net.omkov.lepton.Lepton;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Redirect(
		method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/option/GameOptions;fov:D",
			ordinal = 0
		)
	)
	private double getFov(GameOptions options) {
		if (Lepton.bindZoom.isPressed()) {
			if (!Lepton.zoomEnabled) {
				Lepton.zoomEnabled = true;
				Lepton.MC.worldRenderer.scheduleTerrainUpdate();
			}
			
			return options.fov / Lepton.zoomFactor;
		}
		else if (Lepton.zoomEnabled) {
			Lepton.zoomEnabled = false;
			Lepton.MC.worldRenderer.scheduleTerrainUpdate();
		}
		
		return options.fov;
	}
}
