package com.ahmad.exceptionHandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HTTPErrorHandler {

	@RequestMapping("/400")
	public ModelAndView error400(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("navigate403", "true");		
		return mv;
	}
	
	@RequestMapping("/500")
	public ModelAndView error500(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("navigate403", "true");		
		return mv;
	}
	
	@RequestMapping("/404")
	public ModelAndView error404(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("navigate403", "true");
		
		return mv;
	}
	
	@RequestMapping("/410")
	public ModelAndView error410(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("navigate403", "true");
		
		return mv;
	}
}
