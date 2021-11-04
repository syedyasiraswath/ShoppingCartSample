/**
 * 
 */
package com.shopping.cart.form;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Syedyasiraswath
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874685489793596397L;

	@JsonProperty("cartId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long cartId;
	
	@JsonProperty("userId")
	public Long userId;
	
	@JsonProperty("productInfoId")
	public Long productInfoId;
	
	@JsonProperty("productName")
	public String productName;
	
	@JsonProperty("productRate")
	public Integer productRate;
	
	@JsonProperty("quantity")
	public Integer quantity;
	
	@JsonProperty("message")
	public String message;

}
