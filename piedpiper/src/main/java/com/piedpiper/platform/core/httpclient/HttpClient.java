package com.piedpiper.platform.core.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.piedpiper.platform.commons.utils.JsonHelper;

public class HttpClient {
	private static Logger logger = LoggerFactory.getLogger(HttpClient.class);

	public static void doSSL() {
		CloseableHttpClient httpclient = null;
		SSLContext sslcontext = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
			try {
				trustStore.load(instream, "123456".toCharArray());

				try {
					instream.close();
				} catch (Exception ignore) {
				}

				sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
			} catch (CertificateException e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

			HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
			logger.debug("executing request" + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				logger.debug(response.getStatusLine().toString());
				if (entity != null) {
					logger.debug("Response content length: " + entity.getContentLength());
					logger.debug(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
			return;
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (KeyStoreException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	public static void doPostForm() {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");

		List<NameValuePair> formparams = new ArrayList();
		formparams.add(new BasicNameValuePair("username", "admin"));
		formparams.add(new BasicNameValuePair("password", "admin"));
		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			logger.debug("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					logger.debug("Response content: " + EntityUtils.toString(entity, "UTF-8"));
				}
			} finally {
				response.close();
			}
			return;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public static <T> T doPost(String url, Map<String, String> data, Class<T> c) {
		T t = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(url);

		List<NameValuePair> formparams = new ArrayList();
		if (data != null) {
			for (Map.Entry<String, String> d : data.entrySet()) {
				formparams.add(new BasicNameValuePair((String) d.getKey(), (String) d.getValue()));
			}
		}
		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			logger.debug("executing request: " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String json = EntityUtils.toString(entity, "UTF-8");
					logger.debug("Response content: " + json);
					JsonHelper.getInstance().readValue(json, new TypeReference() {
					});
					t = JsonHelper.getInstance().readValue(json, c);
				}
			} finally {
				response.close();
			}

			return t;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return t;
	}

	public static <T> T doPost(String url, Map<String, String> data, TypeReference<T> c) {
		T t = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(url);

		List<NameValuePair> formparams = new ArrayList();
		if (data != null) {
			for (Map.Entry<String, String> d : data.entrySet()) {
				formparams.add(new BasicNameValuePair((String) d.getKey(), (String) d.getValue()));
			}
		}
		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			logger.debug("executing request: " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String json = EntityUtils.toString(entity, "UTF-8");
					logger.debug("Response content: " + json);
					json = HtmlUtils.htmlUnescape(json);
					t = JsonHelper.getInstance().readValue(json, c);
				}
			} finally {
				response.close();
			}

			return t;
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static <T> T doGet(String Url, Class<T> c) {
		T t = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(Url);
			logger.debug("executing request " + httpget.getURI());

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					logger.debug("Response content length: " + entity.getContentLength());

					String json = EntityUtils.toString(entity);
					logger.debug("Response content: " + EntityUtils.toString(entity));
					t = JsonHelper.getInstance().readValue(json, c);
				}
			} finally {
				response.close();
			}

			return t;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return t;
	}
}
