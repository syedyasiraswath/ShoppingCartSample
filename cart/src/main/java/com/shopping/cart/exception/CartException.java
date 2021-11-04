/**
 * 
 */
package com.shopping.cart.exception;

import java.io.Serializable;

/**
 * @author Syedyasiraswath
 * 
 * Custom Exception for Cart
 *
 */
public class CartException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7329150168706836589L;

	/**
	 * Default constructor
	 */
	public CartException() {
		super();
	}

	/**
	 * @param msg: Error message
	 * 
	 */
	public CartException(String msg) {
		super(msg);
	}

	/**
	 * @param msg: Error message
	 * @param e:   Exception type
	 */
	public CartException(String msg, Exception e) {
		super(msg, e);
	}

}
