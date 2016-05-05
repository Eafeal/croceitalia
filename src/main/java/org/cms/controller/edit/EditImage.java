/**
 * 
 */
package org.cms.controller.edit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author consdonzellipaolo
 * 
 */
@Controller
public class EditImage {

	/**
	 * 
	 */
	@RequestMapping(value = "image/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") String id) {

		ModelAndView modelAndView = new ModelAndView();

		String viewName = "redirect:/edit/oggetto/update/" + id;
		modelAndView.setViewName(viewName);

		return modelAndView;

	}

	@RequestMapping(value = "image/newRelation", method = RequestMethod.POST)
	public ModelAndView addObjectNewRelation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();

		String viewName = "forward:/edit/oggetto/newRelation/";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
}
