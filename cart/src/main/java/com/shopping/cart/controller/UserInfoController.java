/**
 * 
 */
package com.shopping.cart.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.cart.entity.ProductInfoEntity;
import com.shopping.cart.entity.UserInfoEntity;
import com.shopping.cart.exception.CartException;
import com.shopping.cart.form.ProductInfo;
import com.shopping.cart.form.ShoppingCart;
import com.shopping.cart.form.UserInfo;
import com.shopping.cart.service.ProductInfoService;
import com.shopping.cart.service.UserInfoService;
import com.shopping.cart.service.impl.MailService;

/**
 * @author Syedyasiraswath
 *
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ProductInfoService productInfoService; 
	
	@Autowired
	private MailService mailService;

	@GetMapping(value = "/welcome")
	public ModelAndView getWelcomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/welcome");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/register")
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserInfo user) {
		modelAndView.addObject("userInfo", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param userInfo
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws UserInfoException
	 */
	@PostMapping(value = "/register")
	public ModelAndView addUser(ModelAndView modelAndView, @Valid UserInfo userInfo, BindingResult bindingResult,
			HttpServletRequest request) throws CartException {
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
			return modelAndView;
		}
		UserInfoEntity userInfoEntity = userInfoService.addUser(userInfo);
		if (Objects.nonNull(userInfoEntity) && null != userInfoEntity.getUserId()) {
			modelAndView.addObject("confirmationMessage", "User Id has been successfully generated. User ID : "+userInfoEntity.getUserId());
			//sendAsyncMail(userInfo);
		} else {
			modelAndView.addObject("errorMessage", "Error occured while registering the user!");
		}
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@GetMapping(value="/login")
	public ModelAndView showLoginnPage(ModelAndView modelAndView, UserInfo user){
		modelAndView.addObject("userInfo", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	/**
	 * @param modelAndView
	 * @param product
	 * @param userInfo
	 * @param request
	 * @return
	 * @throws UserInfoException
	 */
	@GetMapping(value="/validate/login")
	public ModelAndView validateUser(ModelAndView modelAndView,@ModelAttribute ProductInfo product, UserInfo userInfo, HttpServletRequest request) throws CartException{
		String message = userInfoService.validateUser(userInfo);
		if(message.contains("Success")) {
			List<ProductInfoEntity> infoList = productInfoService.getProducts();
			if(infoList.isEmpty()) {
				modelAndView.addObject("errorMessage", "Product list is empty. Please Try again later!");
			}else {
				modelAndView.addObject("productList", infoList);
				modelAndView.addObject("productInfoList", infoList);
			}
			userInfo = userInfoService.fetchUser(userInfo);
			modelAndView.addObject("confirmationMessage", "Products loaded Successfully!");
			modelAndView.addObject("userName", userInfo.getUserName());
			modelAndView.addObject("userId", userInfo.getUserId());
			modelAndView.addObject("email", userInfo.getEmail());
			modelAndView.addObject("shoppingCart", new ShoppingCart());
			modelAndView.setViewName("dashboard");
		}else {
			modelAndView.addObject("errorMessage", message);
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	/**
	 * @param userInfo
	 */
	public void sendAsyncMail(UserInfo userInfo) {
		try {
			mailService.sendASynchronousMail(userInfo.getEmail(), "Cart Registration!",
					"Successfully registered Mr." + userInfo.getUserName());
		} catch (MailException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
