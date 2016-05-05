package org.cms.controller.edit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditHome {

	/**
	 * @param request
	 * @param response
	 * @param idSezione
	 * @return
	 */
	@RequestMapping(value = "home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("edit/home");

		return modelAndView;
	}

	@RequestMapping(value = "edit/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("redirect:/site/logout");

		return modelAndView;

	}
}
