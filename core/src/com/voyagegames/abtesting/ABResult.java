package com.voyagegames.abtesting;

import java.io.Serializable;

public class ABResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1513164948307836644L;
	
	public final long timestamp;
	public final ABChoice choice;
	public final String identifier;
	public final String data;
	
	public ABResult(final ABChoice choice, final String identifier, final String data) {
		this.timestamp = System.currentTimeMillis();
		this.choice = choice;
		this.identifier = identifier;
		this.data = data;
	}

}
