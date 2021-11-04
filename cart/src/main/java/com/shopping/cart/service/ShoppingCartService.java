/**
 * 
 */
package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.form.ProductInfo;
import com.shopping.cart.form.ShoppingCart;

/**
 * @author Syedyasiraswath Askar Basha
 *
 */
public interface ShoppingCartService {

	/**
	 * @param shoppingCart
	 * @return
	 */
	public void addToCart(ShoppingCart shoppingCart);
	
	/**
	 * @param shoppingCart
	 * @return
	 */
	public List<ShoppingCart> fetchShopCartDet(ShoppingCart shoppingCart);

}
