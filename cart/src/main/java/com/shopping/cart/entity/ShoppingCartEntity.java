/**
 * 
 */
package com.shopping.cart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Syedyasiraswath
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SHOPPING_CART")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "ShoppingCartEntity.findAll", query = "SELECT a FROM ShoppingCartEntity a")
public class ShoppingCartEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874685489793596397L;

	@Id
	@Column(name = "CART_ID")
	@GeneratedValue(generator = "seq_cartID")
	@SequenceGenerator(name = "seq_cartID", sequenceName = "SEQ_CARTID", allocationSize = 1)
	public Long cartId;
	
	@Column(name = "USER_ID")
	public Long userId;
	
	@Column(name = "PRODUCT_ID")
	public Long productInfoId;
	
	@Column(name = "PRODUCT_NAME")
	public String productName;
	
	@Column(name = "PRODUCT_RATE")
	public Integer productRate;
	
	@Column(name = "QUANTITY")
	public Integer quantity;

}
