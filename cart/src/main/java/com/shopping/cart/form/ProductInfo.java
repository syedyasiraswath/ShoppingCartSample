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
public class ProductInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874685489793596397L;

	@JsonProperty("productInfoId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long productInfoId;
	
	@JsonProperty("productName")
	public String productName;
	
	@JsonProperty("productRate")
	public Integer productRate;
	
	@JsonProperty("productAvailQty")
	public Integer productAvailQty;

}
