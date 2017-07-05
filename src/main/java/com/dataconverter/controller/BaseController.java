/**
 * This java package is the spring framework controller, and handles GET requiests
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

package com.dataconverter.controller;

// import org.slf4j.LoggerFactory;//for testing
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
// import org.springframework.web.bind.annotation.PathVariable;//in case we have ot use some extended paths
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
import com.dataconverter.DataHandler;

@Controller
public class BaseController {

	private static String json = "";
	private static final String VIEW_PAGE = "index";
	// private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);//for testing

	// @CrossOrigin
	// @CrossOrigin(origins = "http://localhost:3000")
	// @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
	// @RestController
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String ResponseEntity(ModelMap model) {

		//some conifg
		String APIURL = "http://sumo.tv2.no/api/web/search/categories/6676672/assets?start=0&size=10&pagination=false";
		String APISUBURL = "http://sumo.tv2.no/rest/assets/<id>/related";

		//run the app and generate the API json
		json = new DataHandler().run(APIURL, APISUBURL);

		//add an attribute to replace the api in the jsp file
		model.addAttribute("json", json);

		// logger.debug("[api] json : {}", json);//for testing

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_PAGE;

	}

	// @RequestMapping(value = "/{prop}", method = RequestMethod.GET)
	// public String ResponseEntityProps(@PathVariable String prop, ModelMap model) {
	// 	return VIEW_PAGE;
	// }

}
