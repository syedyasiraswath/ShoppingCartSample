/**
 * 
 */
package com.shopping.cart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.shopping.cart.entity.ProductInfoEntity;
import com.shopping.cart.entity.ShoppingCartEntity;
import com.shopping.cart.form.ProductInfo;
import com.shopping.cart.form.ShoppingCart;
import com.shopping.cart.repository.ProductInfoRepository;
import com.shopping.cart.repository.ShoppingCartRepository;
import com.shopping.cart.service.ShoppingCartService;

/**
 * @author Syedyasiraswath
 *
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private Environment environment;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private ProductInfoRepository productInfoRepository;

	/**
	 *
	 */
	@Override
	public void addToCart(ShoppingCart shoppingCart) {
		Optional<ProductInfoEntity> productInfoOptional = null;
		try {
			productInfoOptional = productInfoRepository.findById(shoppingCart.getProductInfoId());
			if(!productInfoOptional.isPresent()) {
				shoppingCart.setMessage("Product Id Unavailable. Try with a valid Product Id!");
			}
			ShoppingCart shoppingCartVerify = shoppingCartRepository
					.findByUserIdAndProductInfoId(shoppingCart.getUserId(), shoppingCart.getProductInfoId());
			if (shoppingCartVerify != null && shoppingCartVerify.getCartId() != null) {
				if (productInfoOptional.isPresent() && ((productInfoOptional.get().getProductAvailQty() > shoppingCartVerify.getQuantity())
						|| (productInfoOptional.get().getProductAvailQty().equals(shoppingCartVerify.getQuantity())))) {
					shoppingCart.setCartId(shoppingCartVerify.getCartId());
					shoppingCart.setQuantity(shoppingCart.getQuantity() + shoppingCartVerify.getQuantity());
				} else if (productInfoOptional.get().getProductAvailQty() < shoppingCart.getQuantity()) {
					shoppingCart.setMessage("Maximum amount for this product reached in your cart!");
				}
			}
			ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
			BeanUtils.copyProperties(shoppingCart, shoppingCartEntity);
			shoppingCartEntity.setProductName(productInfoOptional.get().getProductName());
			shoppingCartEntity.setProductRate(productInfoOptional.get().getProductRate());
			shoppingCartRepository.saveAndFlush(shoppingCartEntity);
			updateProductInfo(shoppingCart, productInfoOptional);
			BeanUtils.copyProperties(shoppingCartEntity, shoppingCart);
			shoppingCart.setMessage("Successfully added the product into your cart!");
		} catch (DataIntegrityViolationException e) {
			shoppingCart.setMessage("Duplicate entry, Please contact the IT team!");
		}
	}

	/**
	 * @param shoppingCart
	 * @param productInfoOptional
	 */
	private void updateProductInfo(ShoppingCart shoppingCart, Optional<ProductInfoEntity> productInfoOptional) {
		if (productInfoOptional.isPresent()) {
			ProductInfoEntity productInfoEntity = productInfoOptional.get();
			if (productInfoOptional.get().getProductAvailQty() > shoppingCart.getQuantity()) {
				productInfoEntity.setProductAvailQty(
						productInfoOptional.get().getProductAvailQty() - shoppingCart.getQuantity());
				productInfoRepository.saveAndFlush(productInfoEntity);
			} else if (productInfoOptional.get().getProductAvailQty() < shoppingCart.getQuantity()
					|| productInfoOptional.get().getProductAvailQty().equals(shoppingCart.getQuantity())) {
				productInfoEntity.setProductAvailQty(0);
				productInfoRepository.saveAndFlush(productInfoEntity);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alt.shop.service.ShoppingCartService#fetchShopCartDet(com.alt.shop.entity
	 * .ShoppingCart)
	 */
	@Override
	public List<ShoppingCart> fetchShopCartDet(ShoppingCart shoppingCart) {
		List<ShoppingCart> carts = new ArrayList<>();
		Optional<List<ShoppingCartEntity>> entity = shoppingCartRepository.findAllByUserId(shoppingCart.getUserId());
		if (entity.isEmpty()) {
			return carts;
		} else {
			List<ShoppingCartEntity> shoppingCartEntities = entity.get();
			for (ShoppingCartEntity cartEntity : shoppingCartEntities) {
				ShoppingCart cart = new ShoppingCart();
				BeanUtils.copyProperties(cartEntity, cart);
				carts.add(cart);
			}
		}
		return carts;
	}

}
