package com.piedpiper.platform.core.filter.session;

import com.piedpiper.platform.core.properties.PlatformProperties;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedisSessionFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = -365105405910803550L;
	private String sessionId = "sid";

	private String cookieDomain = "";

	private String cookiePath = "/";
	private static final String aotu = "platform.enable.autoRedirect";

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		Cookie[] cookies = request.getCookies();
		Cookie sCookie = null;

		String sid = "";
		String firstUrl = "";
		if ((cookies != null) && (cookies.length > 0)) {
			for (int i = 0; i < cookies.length; i++) {
				sCookie = cookies[i];
				if (sCookie.getName().equals(this.sessionId)) {
					sid = sCookie.getValue();
				} else if (sCookie.getName().equals("firstUrl")) {
					firstUrl = sCookie.getValue();
				}
			}
		}
		if ((Boolean.valueOf(PlatformProperties.getProperty("platform.enable.autoRedirect")).booleanValue())
				&& ((firstUrl.length() == 0) || ("false".equals(firstUrl)))) {
			String uri = request.getRequestURI();
			String query = request.getQueryString();
			String context = request.getContextPath();
			String curl = uri.substring(context.length(), uri.length());

			if ((!curl.equals("/login/logout_forCas.jsp"))
					&& (!curl.equals("/platform/sysmessage/sysMessageController/getSysMessageCount"))
					&& (!curl.endsWith(".js"))) {
				if (query != null) {
					curl = curl + "?" + query;
				}

				Cookie mycookies1 = new Cookie("firstUrl", curl);
				mycookies1.setMaxAge(-1);
				if ((this.cookieDomain != null) && (this.cookieDomain.length() > 0)) {
					mycookies1.setDomain(this.cookieDomain);
				}
				mycookies1.setPath(this.cookiePath);
				response.addCookie(mycookies1);
			}
		}

		if ((sid == null) || (sid.length() == 0)) {
			sid = UUID.randomUUID().toString();
			Cookie mycookies = new Cookie(this.sessionId, sid);
			mycookies.setMaxAge(-1);
			if ((this.cookieDomain != null) && (this.cookieDomain.length() > 0)) {
				mycookies.setDomain(this.cookieDomain);
			}
			mycookies.setPath(this.cookiePath);
			response.addCookie(mycookies);
		}

		String APPLICTIONID = RestClientConfig.systemid;
		ServletRequest requestWrapper = new HttpServletRequestWrapper(sid + APPLICTIONID + "_SESSION", request);
		filterChain.doFilter(requestWrapper, servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.sessionId = filterConfig.getInitParameter("sessionId");
		this.cookieDomain = filterConfig.getInitParameter("cookieDomain");
		if (this.cookieDomain == null) {
			this.cookieDomain = "";
		}

		this.cookiePath = filterConfig.getInitParameter("cookiePath");
		if ((this.cookiePath == null) || (this.cookiePath.length() == 0)) {
			this.cookiePath = "/";
		}
	}
}
