/**
 * A custom origin filter, to let all origins access this api
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

package com.dataconverter.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
public class CORSFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

            //to make sure origin http://localhost:3000 can get data, even if the this server runs at :8080
        	HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
	        httpResponse.setHeader("Access-Control-Allow-Methods", "GET");
        	httpResponse.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type");
        	httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
	        httpResponse.setHeader("Access-Control-Max-Age", "4800");
	        chain.doFilter(request, response);

	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
}
