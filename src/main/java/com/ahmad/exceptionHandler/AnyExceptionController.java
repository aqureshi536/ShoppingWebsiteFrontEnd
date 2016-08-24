package com.ahmad.exceptionHandler;

import javax.servlet.http.HttpServletRequest;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class AnyExceptionController {

	/*@ExceptionHandler(Exception.class)*/
	public ModelAndView handleException(HttpServletRequest request,Exception exception,Model model){
		ModelAndView mv = new ModelAndView("index");
		model.addAttribute("error", exception);
		mv.addObject("navigate403", "true");
//		mv.setViewName("403");
		return mv;
	}
}


