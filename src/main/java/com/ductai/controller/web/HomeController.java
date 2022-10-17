package com.ductai.controller.web;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ductai.dto.NewsDTO;
import com.ductai.service.ICategoryService;
import com.ductai.service.INewService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private INewService newsService;
	
	@Autowired 
	private ICategoryService categoryService;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	   public ModelAndView homePage() {
	      ModelAndView mav = new ModelAndView("web/home");
	      NewsDTO model = new NewsDTO();
	      model.setListResult(newsService.getAllItemsToWebPage());
	      mav.addObject("hotNews",newsService.getTheHOTNews());
	      mav.addObject("model",model);
	      return mav;
	   }
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	   public ModelAndView loginPage() {
	      ModelAndView mav = new ModelAndView("login");
	      return mav;
	   }
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	   public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	      return new ModelAndView("redirect:/trang-chu");
	   }
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	   public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	   }
	
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	   public ModelAndView detailPage(@RequestParam(value = "id") Long id) {
		ModelAndView mav = new ModelAndView("web/detail");
		NewsDTO model = newsService.findByID(id);
		mav.addObject("model",model);
		mav.addObject("category",categoryService.findAll().get(model.getCategoryCode()));
		mav.addObject("createdDate",new SimpleDateFormat("dd/MM/yyyy").format(newsService.getModifiedDate(id)));
		return mav;
	   }
}
