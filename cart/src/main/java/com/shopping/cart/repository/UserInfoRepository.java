/**
 * 
 */
package com.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shopping.cart.entity.UserInfoEntity;

/**
 * @author Syedyasiraswath
 *
 */
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long>, JpaSpecificationExecutor<UserInfoEntity> {

}
