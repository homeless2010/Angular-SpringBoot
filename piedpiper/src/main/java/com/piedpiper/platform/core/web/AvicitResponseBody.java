package com.piedpiper.platform.core.web;

import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.OpResult;
import java.util.HashMap;

public class AvicitResponseBody extends HashMap<String, Object> {
	private static final long serialVersionUID = 3345449986172551035L;
	private static final String FLAG = "flag";
	private static final String ERROR = "e";

	public AvicitResponseBody(PlatformConstant.OpResult op) {
		super.put("flag", op);
	}

	public AvicitResponseBody(PlatformConstant.OpResult op, String error) {
		super.put("flag", op);
		super.put("e", error);
	}

	public AvicitResponseBody(int i, String msg) {
		super.put("flag", Integer.valueOf(i));
		super.put("e", msg);
	}

	public AvicitResponseBody(int i) {
		super.put("flag", Integer.valueOf(i));
	}

	public AvicitResponseBody() {
	}

	public static String getFlag() {
		return "flag";
	}
}
