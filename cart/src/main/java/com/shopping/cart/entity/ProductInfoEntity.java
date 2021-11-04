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
@Table(name = "PRODUCT_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "ProductInfoEntity.findAll", query = "SELECT a FROM ProductInfoEntity a")
public class ProductInfoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874685489793596397L;

	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(generator = "seq_productID")
	@SequenceGenerator(name = "seq_productID", sequenceName = "SEQ_PRODUCTID", allocationSize = 1)
	public Long productInfoId;
	
	@Column(name = "PRODUCT_NAME")
	public String productName;
	
	@Column(name = "PRODUCT_RATE")
	public Integer productRate;
	
	@Column(name = "PRODUCT_AVAIL_QTY")
	public Integer productAvailQty;

}
