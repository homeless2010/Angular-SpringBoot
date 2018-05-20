package com.piedpiper.platform.api.syspermissionresource.dto;

public enum PERMISSION {
	PERSONAL,

	DEPTLEADER,

	CROSSDEPTLEADER,

	GLOBLE;

	private PERMISSION() {
	}

	public static PERMISSION valueOf(int i) {
		if ((i < 0) || (i >= values().length)) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}

		return values()[i];
	}

	public static PERMISSION valueOfString(String st) {
		return valueOf(Integer.valueOf(st).intValue());
	}
}
