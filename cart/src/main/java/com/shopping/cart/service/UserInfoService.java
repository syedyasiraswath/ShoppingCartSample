/**
 * 
 */
package com.shopping.cart.service;

import com.shopping.cart.entity.UserInfoEntity;
import com.shopping.cart.form.UserInfo;

/**
 * @author Syedyasiraswath
 *
 */
public interface UserInfoService {

	/**
	 * @param userInfo
	 * @return
	 */
	public UserInfoEntity addUser(UserInfo userInfo);

	/**
	 * @param userInfo
	 * @return
	 */
	public String validateUser(UserInfo userInfo);

	/**
	 * @param userInfo
	 * @return
	 */
	public UserInfo fetchUser(UserInfo userInfo);

}
