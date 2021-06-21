// Event.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.event;

import java.util.EventListener;
import java.util.List;

/** An event that triggers listener updates. */
public abstract class Event<T extends EventListener> {
	public abstract void fire(List<T> listeners);
	public abstract Class<T> type();
}
