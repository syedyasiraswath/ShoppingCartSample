/**
 * 
 */
package com.shopping.cart.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Syedyasiraswath
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ CartException.class })
	protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, "Error occured while accessing the cart. Please try again later!",
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
	public ModelAndView handleBadRequest(Exception ex, WebRequest request) {
		ModelAndView model = new ModelAndView();
		if (((ServletWebRequest) request).getRequest().getRequestURL().toString().contains("register")) {
			model.setViewName("/register");
			model.addObject("errorMessage", ex.getMessage());
		}
		return model;
	}
}
