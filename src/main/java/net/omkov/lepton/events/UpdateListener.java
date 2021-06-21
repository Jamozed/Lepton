// UpdateListener.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.events;

import java.util.EventListener;
import java.util.List;

import net.omkov.lepton.event.Event;

/** Listener triggered on game tick. */
public interface UpdateListener extends EventListener {
	public void onUpdate();
	
	public static class UpdateEvent extends Event<UpdateListener> {
		public static final UpdateEvent INSTANCE = new UpdateEvent();
		
		@Override
		public void fire(List<UpdateListener> listeners) {
			for (UpdateListener listener : listeners) { listener.onUpdate(); }
		}
		
		@Override
		public Class<UpdateListener> type() { return UpdateListener.class; }
	}
}
