package com.piedpiper.platform.api.shiro.token;
import org.apache.shiro.authc.UsernamePasswordToken;

import com.piedpiper.platform.core.rest.msg.LogBase;

public class PiedPiperUsernamePasswordToken extends UsernamePasswordToken {
	private static final long serialVersionUID = 7886357106366350864L;
	private String username;
	private char[] password;
	private LogBase logbase;
	private boolean casFlag;
	private boolean rememberMe;
	private String host;

	public PiedPiperUsernamePasswordToken() {
		this.rememberMe = false;
	}

	public PiedPiperUsernamePasswordToken(String username, char[] password) {
		this(username, password, false, null);
	}

	public PiedPiperUsernamePasswordToken(String username, String password) {
		this(username, (password != null) ? password.toCharArray() : null, false, null);
	}

	public PiedPiperUsernamePasswordToken(String username, char[] password, String host) {
		this(username, password, false, host);
	}

	public PiedPiperUsernamePasswordToken(String username, String password, String host) {
		this(username, (password != null) ? password.toCharArray() : null, false, host);
	}

	public PiedPiperUsernamePasswordToken(String username, char[] password, boolean rememberMe) {
		this(username, password, rememberMe, null);
	}

	public PiedPiperUsernamePasswordToken(String username, String password, boolean rememberMe) {
		this(username, (password != null) ? password.toCharArray() : null, rememberMe, null);
	}

	public PiedPiperUsernamePasswordToken(String username, String password, boolean casFlag, LogBase logbase) {
		this.rememberMe = false;

		this.username = username;
		this.password = ((password != null) ? password.toCharArray() : null);
		this.casFlag = casFlag;
		this.logbase = logbase;
	}

	public PiedPiperUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host) {
		this.rememberMe = false;

		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
		this.host = host;
	}

	public PiedPiperUsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
		this(username, (password != null) ? password.toCharArray() : null, rememberMe, host);
	}

	public PiedPiperUsernamePasswordToken(String username, String password, LogBase logbase) {
		this.rememberMe = false;

		this.username = username;
		this.password = ((password != null) ? password.toCharArray() : null);
		this.logbase = logbase;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return this.password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public Object getPrincipal() {
		return getUsername();
	}

	public Object getCredentials() {
		return getPassword();
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isRememberMe() {
		return this.rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public void clear() {
		this.username = null;
		this.host = null;
		this.rememberMe = false;

		if (this.password != null) {
			for (int i = 0; i < this.password.length; ++i) {
				this.password[i] = '\0';
			}
			this.password = null;
		}
	}

	public LogBase getLogbase() {
		return this.logbase;
	}

	public void setLogbase(LogBase logbase) {
		this.logbase = logbase;
	}

	public boolean getCasFlag() {
		return this.casFlag;
	}

	public void setCasFlag(boolean casFlag) {
		this.casFlag = casFlag;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getClass().getName());
		sb.append(" - ");
		sb.append(this.username);
		sb.append(", rememberMe=").append(this.rememberMe);
		if (this.host != null) {
			sb.append(" (").append(this.host).append(")");
		}
		return sb.toString();
	}
}