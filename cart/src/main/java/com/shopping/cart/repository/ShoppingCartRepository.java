/**
 * 
 */
package com.shopping.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shopping.cart.entity.ShoppingCartEntity;
import com.shopping.cart.form.ShoppingCart;

/**
 * @author Syedyasiraswath
 *
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long>, JpaSpecificationExecutor<ShoppingCartEntity> {

	
	public ShoppingCart findByUserIdAndProductInfoId(Long userId, Long productInfoId);

	public Optional<List<ShoppingCartEntity>> findAllByUserId(Long userId);

}
