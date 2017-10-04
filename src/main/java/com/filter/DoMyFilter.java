package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

public class DoMyFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// String string[]=((HttpServletRequest)
		// request).getRequestURL().toString().split("/");
		// for(String s : string)
		// {
		// System.out.println("sdf "+ s);
		// }
		// System.out.println("hiii " + ((HttpServletRequest)
		// request).getRequestURI().toString());
		/*System.out.println(((HttpServletRequest) request).getRequestURI().toString() + " URI");
		System.out.println(((HttpServletRequest) request).getRequestURL().toString() + " URL");
		System.out.println(
				"URI length - > " + ((HttpServletRequest) request).getRequestURI().toString().split("/").length);*/

		int a = ((HttpServletRequest) request).getRequestURI().toString().split("/").length;
		String s[] = ((HttpServletRequest) request).getRequestURI().toString().split("/");

		if (a == 2 && !(s[1].contains(".jsp") || s[1].contains("Servlet") || s[1].contains(".html")
				|| s[1].contains(".htm") || s[1].contains("servlet"))) {
		//	System.out.println("ddddddd "+s[1]);
			String str= "/photographerDetail.html?id=" + s[1];
		//	System.out.println(str + " <----");
			request.getRequestDispatcher(str).forward(request, response);
			/*request.getRequestDispatcher("/index.html").forward(request, response);*/
		} else {
			/*
			 * for (String str : s) { System.out.println("arr  " + str); }
			 * 
			 */
			/*
			 * if (s.length == 2) {
			 * request.getRequestDispatcher("index.html").forward(request,
			 * response); chain.doFilter(request, response); } else if
			 * (s[1].contains(".jsp") || s[1].contains("Servlet") ||
			 * s[1].contains("servlet") || s[1].contains(".html") ||
			 * s[1].contains(".htm") || s[1].contains(".ico") ||
			 * s[1].contains(".js") || s[1].contains(".svg") ||
			 * s[1].contains(".png")|| s[1].contains(".jpg")||
			 * s[1].contains(".jpeg") || s[1].contains(".css")) {
			 * System.out.println("skskks"); chain.doFilter(request, response);
			 * } else {
			 * request.getRequestDispatcher("/photographerDetail.html?id=" +
			 * s[2]).forward(request, response);
			 * 
			 * // request.getRequestDispatcher(
			 * "http://www.fotocourtesy.com/photographerDetail.html?id="+s[2]).
			 * forward(request, // response); }
			 */
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
