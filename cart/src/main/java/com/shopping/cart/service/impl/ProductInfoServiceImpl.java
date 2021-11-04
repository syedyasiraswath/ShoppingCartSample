/**
 * 
 */
package com.shopping.cart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.entity.ProductInfoEntity;
import com.shopping.cart.repository.ProductInfoRepository;
import com.shopping.cart.service.ProductInfoService;

/**
 * @author Syedyasiraswath
 *
 */
@Service
public class ProductInfoServiceImpl  implements ProductInfoService {
	
	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Override
	public List<ProductInfoEntity> getProducts() {
		return productInfoRepository.findAll();
	}


}
