               
/**
 * 
 */
package com.shopping.cart.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.cart.entity.ProductInfoEntity;
import com.shopping.cart.entity.UserInfoEntity;
import com.shopping.cart.exception.CartException;
import com.shopping.cart.form.ProductInfo;
import com.shopping.cart.form.ShoppingCart;
import com.shopping.cart.repository.UserInfoRepository;
import com.shopping.cart.service.ProductInfoService;
import com.shopping.cart.service.ShoppingCartService;

/**
 * @author Syedyasiraswath Askar Basha
 *
 */
@RestController
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private Environment environment;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private ProductInfoService productInfoService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addToCart(ModelAndView modelAndView,
			ShoppingCart shoppingCart, HttpServletRequest request) throws CartException {
		Optional<UserInfoEntity> userInfoOptional = userInfoRepository.findById(shoppingCart.getUserId());
		if (userInfoOptional.isPresent()) {
			modelAndView.addObject("userName", userInfoOptional.get().getUserName());
			modelAndView.addObject("userId", userInfoOptional.get().getUserId());
			modelAndView.addObject("email", userInfoOptional.get().getEmail());
		}
		shoppingCartService.addToCart(shoppingCart);
		if (shoppingCart.getMessage().equalsIgnoreCase(environment.getProperty("ShoppingCartServiceImpl.ALREADY_ADDED"))
				|| shoppingCart.getMessage().equalsIgnoreCase(environment.getProperty("ShoppingCartServiceImpl.CART_DUP"))
				|| shoppingCart.getMessage().contains(environment.getProperty("ShoppingCartServiceImpl.CART_FAILURE"))
				|| shoppingCart.getMessage().contains(environment.getProperty("ShoppingCartServiceImpl.PRODUCT_NOT_FOUND"))) {
			modelAndView.addObject("cartErrorMessage", shoppingCart.getMessage());
			List<ProductInfoEntity> productInfoEntities = productInfoService.getProducts();
			if (CollectionUtils.isEmpty(productInfoEntities)) {
				modelAndView.addObject("errorMessage", environment.getProperty("UserInfoController.CATELOGUE_EMPTY"));
			} else {
				modelAndView.addObject("productList", productInfoEntities);
				modelAndView.addObject("productInfoList", productInfoEntities);
			}
			modelAndView.setViewName("dashboard");
			return modelAndView;
		}
		modelAndView.addObject("confirmationMessage", shoppingCart.getQuantity() + " item has been added into your cart!");
		List<ShoppingCart> infoList = shoppingCartService.fetchShopCartDet(shoppingCart);
		modelAndView.addObject("shoppingCartList", infoList);
		modelAndView.setViewName("shoppingCart");
		return modelAndView;
	}
}
