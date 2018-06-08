package com.piedpiper.platform.commons.utils.id;

public class UUIDHexGenerator extends AbstractUUIDGenerator {
	private String sep = "";

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public String generate() {
		return 36 + format(getIP()) + this.sep + format(getJVM()) + this.sep + format(getHiTime()) + this.sep
				+ format(getLoTime()) + this.sep + format(getCount());
	}
}
