
package com.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.ProductInfoEntity;

/**
 * @author Syedyasiraswath
 *
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, Long>, JpaSpecificationExecutor<ProductInfoEntity> {

}
