package com.piedpiper.platform.api.syssso.filter;

import com.piedpiper.platform.api.syssso.loder.SsoPropsLoader;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.shiroSecurity.cas.util.GetterUtil;
import com.piedpiper.platform.core.shiroSecurity.cas.util.StringUtil;
import com.piedpiper.platform.core.shiroSecurity.cas.util.Validator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CASFilter extends BaseConfigFilter {
	public static String LOGIN = "LOGIN";

	public static void reload(long companyId) {
		_ticketValidators.remove(Long.valueOf(companyId));
	}

	private static Logger log = LoggerFactory.getLogger(CASFilter.class);

	protected TicketValidator getTicketValidator(long companyId) throws Exception {
		TicketValidator ticketValidator = (TicketValidator) _ticketValidators.get(Long.valueOf(companyId));

		if (ticketValidator != null) {
			return ticketValidator;
		}

		String serverName = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
				.getProperty("cas.server.name");
		String serverUrl = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
				.getProperty("cas.server.url");
		String loginUrl = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
				.getProperty("cas.login.url");

		Cas20ProxyTicketValidator cas20ProxyTicketValidator = new Cas20ProxyTicketValidator(serverUrl);

		Map<String, String> parameters = new HashMap();

		parameters.put("serverName", serverName);
		parameters.put("casServerUrlPrefix", serverUrl);
		parameters.put("casServerLoginUrl", loginUrl);
		parameters.put("redirectAfterValidation", "false");
		parameters.put("useSession", "true");

		cas20ProxyTicketValidator.setCustomParameters(parameters);

		_ticketValidators.put(Long.valueOf(companyId), cas20ProxyTicketValidator);

		return cas20ProxyTicketValidator;
	}

	protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			if (SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid) == null) {
				SsoPropsLoader.load();
			}
			String casEnaled = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
					.getProperty("cas.auth.enabled");
			if (GetterUtil.getBoolean(casEnaled, false)) {
				HttpSession session = request.getSession();

				String pathInfo = request.getPathInfo();
				pathInfo = request.getRequestURI();

				if (pathInfo.indexOf("/logout") != -1) {
					session.invalidate();

					String logoutUrl = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
							.getProperty("cas.logout.url");

					response.sendRedirect(logoutUrl);

					return;
				}
				String login = (String) session.getAttribute(LOGIN);

				String serverName = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
						.getProperty("cas.server.name");

				String serviceUrl = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
						.getProperty("cas.service.url");
				String qString = request.getQueryString();

				if (Validator.isNull(serviceUrl)) {
					serviceUrl = CommonUtils.constructServiceUrl(request, response, serviceUrl, serverName, "ticket",
							false);
				}

				String ticket = request.getParameter("ticket");
				String serviceAllUrl = serviceUrl;
				if (Validator.isNotNull(qString)) {
					serviceAllUrl = serviceAllUrl + "?" + qString;
				}

				if (Validator.isNull(ticket)) {
					if (Validator.isNotNull(login)) {
						processFilter(CASFilter.class, request, response, filterChain);

					} else {

						String loginUrl = SsoPropsLoader.getSSoProperties("cas_" + RestClientConfig.systemid)
								.getProperty("cas.login.url");

						loginUrl = addParameter(loginUrl, "service", serviceAllUrl);

						response.sendRedirect(loginUrl);
					}

					return;
				}

				String ticketString = "&ticket=" + ticket;
				String ticketString2 = "?ticket=" + ticket;
				String chkServiceAllUrl = serviceAllUrl.replace(ticketString, "");

				chkServiceAllUrl = chkServiceAllUrl.replace(ticketString2, "");

				TicketValidator ticketValidator = getTicketValidator(1L);
				log.info("------------------------ticket[" + ticket + "]");
				log.info("------------------------serviceAllUrl[" + chkServiceAllUrl + "]");

				Assertion assertion = ticketValidator.validate(ticket, chkServiceAllUrl);

				if (assertion != null) {
					AttributePrincipal attributePrincipal = assertion.getPrincipal();

					login = attributePrincipal.getName();
					session.setAttribute(LOGIN, login);
					session.setAttribute("Cas_AttributePrincipal_Attributes", attributePrincipal.getAttributes());

					request.setAttribute("_const_cas_assertion_", assertion);

					session.setAttribute("_const_cas_assertion_", assertion);
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		processFilter(CASFilter.class, request, response, filterChain);
	}

	private static Map<Long, TicketValidator> _ticketValidators = new ConcurrentHashMap();

	private String encodeURL(String url, boolean escapeSpaces) {
		if (url == null) {
			return null;
		}
		try {
			url = URLEncoder.encode(url, "UTF-8");

			if (escapeSpaces) {
			}
			return StringUtil.replace(url, "+", "%20");

		} catch (UnsupportedEncodingException uee) {

			log.error(uee.getMessage(), uee);
		}
		return "";
	}

	private String addParameter(String url, String name, String value) {
		if (url == null) {
			return null;
		}

		if (url.indexOf("?") == -1) {
			url = url + "?";
		}

		if ((!url.endsWith("?")) && (!url.endsWith("&"))) {

			url = url + "&";
		}

		return url + name + "=" + encodeURL(value, false);
	}
}
