// EventManager.java
// Copyright (C) 2021, Jakob Wakeling
// All rights reserved.

package net.omkov.lepton.event;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** The EventManager class manages events. */
public final class EventManager {
	private final Map<Class<? extends EventListener>, List<? extends EventListener>> map = new HashMap<>();
	
	public EventManager() {}
	
	/** Fire the given event. */
	public <L extends EventListener, E extends Event<L>> void fire(E event) {
		@SuppressWarnings("unchecked")
		List<L> listeners = (List<L>)map.get(event.type());
		if (listeners == null || listeners.isEmpty()) { return; }
		
		synchronized (this) { event.fire(listeners); }
	}
	
	/** Add an event listener to the EventManager. */
	public <L extends EventListener> void add(Class<L> type, L listener) {
		@SuppressWarnings("unchecked")
		List<L> listeners = (List<L>)map.get(type);
		
		if (listeners == null) { listeners = new ArrayList<>(); map.put(type, listeners); }
		
		synchronized (this) { listeners.add(listener); }
	}
	
	/** Remove an event listener from the EventManager. */
	public <L extends EventListener> void remove(Class<L> type, L listener) {
		@SuppressWarnings("unchecked")
		List<L> listeners = (List<L>)map.get(type);
		
		synchronized (this) { if (listeners != null) { listeners.remove(listener); } }
	}
}
