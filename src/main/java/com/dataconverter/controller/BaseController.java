/**
 * This java package is the spring framework controller, and handles GET requiests
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

package com.dataconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dataconverter.DataHandler;

@Controller
public class BaseController {

	private static String json = "";
	private static final String VIEW_PAGE = "index";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String ResponseEntity(ModelMap model) {

		//some conifg
		String APIURL = "http://sumo.tv2.no/api/web/search/categories/6676672/assets?start=0&size=10&pagination=false";
		String APISUBURL = "http://sumo.tv2.no/rest/assets/<id>/related";

		//run the app and generate the API json
		json = new DataHandler().run(APIURL, APISUBURL);

		//add an attribute to replace the api in the jsp file
		model.addAttribute("json", json);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_PAGE;

	}

	// @RequestMapping(value = "/{prop}", method = RequestMethod.GET)
	// public String ResponseEntityProps(@PathVariable String prop, ModelMap model) {
	// 	return VIEW_PAGE;
	// }

}
