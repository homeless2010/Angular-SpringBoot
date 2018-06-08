package com.piedpiper.platform.core.rest.client;

import com.piedpiper.platform.commons.utils.EncryptUtil;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.piedpiper.platform.core.shiroSecurity.contextThread.ContextCommonHolder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestClient {
	static Logger logger = LoggerFactory.getLogger(RestClient.class);

	private static <T, D> ResponseMsg<T> execute(String url, String methodType,
			GenericType<ResponseMsg<T>> typeReference, D data) throws RestException {
		logger.debug("request: " + url + " @ " + methodType + " @ " + data);
		ResteasyClient client = null;
		Response response = null;
		ResponseMsg<T> t = null;
		try {
			client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(url);

			if ((methodType != null) && (methodType.equals("POST"))) {
				response = target.request().header("SessionId", ContextCommonHolder.getCookeMid())
						.header("Basic", RestClientConfig.username)
						.header("Sign", EncryptUtil.MD5(RestClientConfig.username + ":" + RestClientConfig.password))
						.post(Entity.entity(data, "application/json"));
			} else if ((methodType != null) && (methodType.equals("GET"))) {
				response = target.request().header("SessionId", ContextCommonHolder.getCookeMid())
						.header("Basic", RestClientConfig.username)
						.header("Sign", EncryptUtil.MD5(RestClientConfig.username + ":" + RestClientConfig.password))
						.get();
			}
			t = (ResponseMsg) response.readEntity(typeReference);
		} catch (Exception e) {
			logger.error("HTTP " + methodType + " Failure", e);
			t = new ResponseMsg();
			t.setRetCode("500");
		} finally {
			if (response != null)
				response.close();
			if (client != null)
				client.close();
		}
		return t;
	}

	public static <T> ResponseMsg<T> doGet(String url, GenericType<ResponseMsg<T>> typeReference) throws RestException {
		ResponseMsg<T> t = execute(url, "GET", typeReference, "");
		return t;
	}

	public static <T, D> ResponseMsg<T> doPost(String url, D data, GenericType<ResponseMsg<T>> typeReference)
			throws RestException {
		ResponseMsg<T> t = execute(url, "POST", typeReference, data);
		return t;
	}

	public static <T, D> ResponseMsg<T> doPost(String url, D data, GenericType<ResponseMsg<T>> typeReference,
			String sessionId) throws RestException {
		ResponseMsg<T> t = execute(url, "POST", typeReference, data, sessionId);
		return t;
	}

	private static <T, D> ResponseMsg<T> execute(String url, String methodType,
			GenericType<ResponseMsg<T>> typeReference, D data, String sessionId) throws RestException {
		logger.debug("request: " + url + " @ " + methodType + " @ " + data);
		ResteasyClient client = null;
		Response response = null;
		ResponseMsg<T> t = null;
		try {
			client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(url);

			if ((methodType != null) && (methodType.equals("POST"))) {
				response = target.request().header("SessionId", sessionId).header("Basic", RestClientConfig.username)
						.header("Sign", EncryptUtil.MD5(RestClientConfig.username + ":" + RestClientConfig.password))
						.post(Entity.entity(data, "application/json"));
			} else if ((methodType != null) && (methodType.equals("GET"))) {
				response = target.request().get();
			}
			t = (ResponseMsg) response.readEntity(typeReference);
		} catch (Exception e) {
			logger.error("HTTP " + methodType + " Failure", e);
		} finally {
			if (response != null)
				response.close();
			if (client != null)
				client.close();
		}
		return t;
	}

	public static <T> ResponseMsg<T> doGet(String url, GenericType<ResponseMsg<T>> typeReference, String sessionId)
			throws RestException {
		ResponseMsg<T> t = execute(url, "GET", typeReference, "", sessionId);
		return t;
	}
}
