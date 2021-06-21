// ClientPlayerEntityMixin.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.omkov.lepton.events.UpdateListener.UpdateEvent;
import net.omkov.lepton.Lepton;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Inject(method = "tick()V", at = @At(value = "INVOKE"))
	private void onTick(CallbackInfo ci) {
		Lepton.CS.eventManager.fire(UpdateEvent.INSTANCE);
	}
}
