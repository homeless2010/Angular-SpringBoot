package com.piedpiper.platform.core.filter.session;

import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class HttpSessionSidWrapper extends HttpSessionWrapper {
	private String sid = "";

	private Map map = null;

	private int sessionTimeout = 1800;

	public HttpSessionSidWrapper(String sid, HttpSession session) {
		super(session);
		this.sid = sid;
		if (session != null) {
			this.sessionTimeout = session.getMaxInactiveInterval();
		}
		this.map = RedisSessionService.getInstance().getSession(sid, this.sessionTimeout);
	}

	public Object getAttribute(String arg0) {
		return this.map.get(arg0);
	}

	public Enumeration getAttributeNames() {
		return new Enumerator(this.map.keySet(), true);
	}

	public void invalidate() {
		this.map.clear();
		RedisSessionService.getInstance().removeSession(this.sid);
	}

	public void removeAttribute(String arg0) {
		this.sessionTimeout = getMaxInactiveInterval();
		this.map.remove(arg0);
		RedisSessionService.getInstance().saveSession(this.sid, this.map, this.sessionTimeout);
	}

	public void setAttribute(String arg0, Object arg1) {
		this.sessionTimeout = getMaxInactiveInterval();
		if ((arg1 instanceof LoginSessionInfo)) {
			LoginSessionInfo a = (LoginSessionInfo) arg1;
			this.map.putAll(a);
		} else {
			this.map.put(arg0, arg1);
		}
		RedisSessionService.getInstance().saveSession(this.sid, this.map, this.sessionTimeout);
	}

	public String getId() {
		return this.sid;
	}
}
