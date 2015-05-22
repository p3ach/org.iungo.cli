package org.iungo.cli.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.iungo.lifecycle.api.AbstractLifecycle;

public class FlowLifecycle extends AbstractLifecycle {
	
	public static final Integer CREATED = 2 << 1;
	
	public static final Integer NORMAL = 2 << 5;

	public static final Integer EXCEPTION = 2 << 6;
	
	public static final Integer RETURN = 2 << 7;
	
	public static final Integer BREAK = 2 << 8;

	public static final Integer CONTINUE = 2 << 9;

	public static final Set<Integer> FLOW_STATES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new Integer[]{NORMAL, EXCEPTION, RETURN, BREAK, CONTINUE})));
	
	private static final long serialVersionUID = 1L;

	public FlowLifecycle() {
		final TransitionMap transitionMap = getTransitionMap();
		
		StatesSet states;
		
		states = new StatesSet();
		states.add(CREATED);
		states.addAll(FLOW_STATES);
		transitionMap.put(NORMAL, states);

		states = new StatesSet();
		states.addAll(FLOW_STATES);
		transitionMap.put(EXCEPTION, states);

		states = new StatesSet();
		states.addAll(FLOW_STATES);
		transitionMap.put(RETURN, states);

		states = new StatesSet();
		states.addAll(FLOW_STATES);
		transitionMap.put(BREAK, states);

		states = new StatesSet();
		states.addAll(FLOW_STATES);
		transitionMap.put(CONTINUE, states);
		
		state = CREATED;
	}
	
	public void setNormal() {
		setState(NORMAL);
	}
	
	public void setException() {
		setState(EXCEPTION);
	}

	public void setReturn() {
		setState(RETURN);
	}

	public void setBreak() {
		setState(BREAK);
	}

	public void setContinue() {
		setState(CONTINUE);
	}
}
