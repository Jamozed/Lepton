// LeptonModMenu.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

/** Provides the modmenu entrypoint. */
@Environment(EnvType.CLIENT)
public final class LeptonModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return (parent -> AutoConfig.getConfigScreen(LeptonConfig.class, parent).get());
	}
}
