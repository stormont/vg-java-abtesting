package com.voyagegames.abtesting;


public interface IABTest extends Runnable {
	
	public String getData(ABChoice choice);
	public void doTestA();
	public void doTestB();
	public void recordResult();

}
