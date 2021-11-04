/**
 * 
 */
package com.shopping.cart.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Syedyasiraswath
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6727084817250279223L;

	@JsonProperty("userId")
	public Long userId;

	@JsonProperty("userName")
	@NotNull
	@Size(min = 3, max = 45, message = "Please provide a valid User Name!")
	public String userName;

	@JsonProperty("age")
	@NotNull
	@Positive(message = "Please provide a valid Age!")
	public Integer age;

	@JsonProperty("gender")
	public String gender;

	@JsonProperty("email")
	@NotNull(message = "Please provide a valid Email!")
	@Email
	public String email;

	@JsonProperty("address")
	@NotNull(message = "Please provide a valid Address!")
	public String address;

	@JsonProperty("password")
	@NotNull
	@Size(min = 6, max = 6, message = "Password should be 6 characters")
	public String password;

}
