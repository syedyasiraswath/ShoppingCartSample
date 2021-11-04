/**
 * 
 */
package com.shopping.cart.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.cart.entity.UserInfoEntity;
import com.shopping.cart.form.UserInfo;
import com.shopping.cart.repository.UserInfoRepository;
import com.shopping.cart.service.UserInfoService;

/**
 * @author Syedyasiraswath
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	/**
	 *
	 */
	@Override
	public UserInfoEntity addUser(UserInfo userInfo) {
		UserInfoEntity userEntity = new UserInfoEntity();
		BeanUtils.copyProperties(userInfo, userEntity);
		userInfoRepository.save(userEntity);
		return userEntity;
	}

	/**
	 *
	 */
	@Override
	public String validateUser(UserInfo userInfo) {
		Optional<UserInfoEntity> userEntity = userInfoRepository.findById(userInfo.getUserId());
		if (userEntity.isPresent()) {
			return "Success";
		} else {
			return "Failure";
		}
	}

	/**
	 *
	 */
	@Override
	public UserInfo fetchUser(UserInfo userInfo) {
		Optional<UserInfoEntity> entity = userInfoRepository.findById(userInfo.getUserId());
		if (entity.isPresent()) {
			BeanUtils.copyProperties(entity.get(), userInfo);
		} else {
			userInfo = new UserInfo();
		}
		return userInfo;
	}

}
