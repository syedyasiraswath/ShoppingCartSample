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
@Table(name = "USER_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "UserInfoEntity.findAll", query = "SELECT a FROM UserInfoEntity a")
public class UserInfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582643521107597037L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(generator = "seq_userID")
	@SequenceGenerator(name = "seq_userID", sequenceName = "SEQ_USERID", allocationSize = 1)
	public Long userId;

	@Column(name = "USER_NAME")
	public String userName;

	@Column(name = "AGE")
	public Integer age;

	@Column(name = "GENDER")
	public String gender;

	@Column(name = "EMAIL")
	public String email;

	@Column(name = "ADDRESS")
	public String address;

	@Column(name = "PASSWORD")
	public String password;

}
