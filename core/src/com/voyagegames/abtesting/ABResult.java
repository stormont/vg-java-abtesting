package com.voyagegames.abtesting;

import java.io.Serializable;

// A class for defining the result of an A/B test
public class ABResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1513164948307836644L;
	
	// The time at which this result was created
	public final long timestamp;
	// The resulting A/B choice for the test
	public final ABChoice choice;
	// The identifier for the test that created this result
	public final String identifier;
	// The user-specified data attached to the test result
	public final Object data;
	
	// Creates the A/B test result
	//  choice     - the resulting A/B choice for the test
	//  identifier - the identifier for the test that created this result
	//  data       - the user-specified data attached to the test result
	public ABResult(final ABChoice choice, final String identifier, final Object data) {
		this.timestamp = System.currentTimeMillis();
		this.choice = choice;
		this.identifier = identifier;
		this.data = data;
	}

}
