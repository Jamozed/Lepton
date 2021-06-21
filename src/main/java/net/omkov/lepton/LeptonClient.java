// LeptonClient.java
// Client entrypoint class for Lepton
// Copyright (C) 2020, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton;

import net.fabricmc.api.ClientModInitializer;

/** The LeptonClient class provides a client entrypoint for Lepton. */
public final class LeptonClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { Lepton.CS.initialize(); }
}
