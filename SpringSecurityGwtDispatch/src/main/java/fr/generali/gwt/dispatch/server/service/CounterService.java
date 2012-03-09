package fr.generali.gwt.dispatch.server.service;


public interface CounterService {
	
	//@PreAuthorize("hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_TELLER') and (#account.balance + #amount >= -#account.overdraft)")
	int increment(int amount);
	
	int decrement(int amount);
	
	void reset();
}