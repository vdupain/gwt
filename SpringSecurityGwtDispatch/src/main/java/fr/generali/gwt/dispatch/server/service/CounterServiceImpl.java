package fr.generali.gwt.dispatch.server.service;

import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
	
	private int current = 0;
	
	@Override
	public synchronized int increment(int amount) {
		current += amount;
		return current;
	}

	@Override
	public synchronized int decrement(int amount) {
		current -= amount;
		return current;
	}

	@Override
	public synchronized  void reset() {
		current = 0;
	}

}
