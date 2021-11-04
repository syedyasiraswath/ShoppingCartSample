/**
 * 
 */
package com.shopping.cart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.cart.exception.CartException;
import com.shopping.cart.form.ProductInfo;
import com.shopping.cart.form.UserInfo;
import com.shopping.cart.service.ProductInfoService;

/**
 * @author Syedyasiraswath
 *
 */
@RestController
@RequestMapping(value = "/productInfo")
public class ProductInfoController {
	
	@Autowired
	private ProductInfoService productInfoService;
	
	/**
	 * @param modelAndView
	 * @return
	 */
/*	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public ModelAndView getWelcomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/login");
		return modelAndView;
	}*/

	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public ModelAndView getProducts(ModelAndView modelAndView, ProductInfo productInfo, HttpServletRequest request) throws CartException{
		modelAndView.addObject("errorMessage", "Session not found :( Please Login to see your dashboard");
		modelAndView.addObject("userInfo", new UserInfo());
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
