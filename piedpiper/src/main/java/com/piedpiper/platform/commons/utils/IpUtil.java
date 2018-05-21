package com.piedpiper.platform.commons.utils;

import org.springframework.util.Assert;

public class IpUtil {
	public static boolean ipIsValid(String startSectionIp, String endSectionIp, String ip) {
		if ((startSectionIp == null) || (endSectionIp == null))
			throw new NullPointerException("IP段不能为空！");
		if (ip == null)
			throw new NullPointerException("IP不能为空！");
		startSectionIp = startSectionIp.trim();
		endSectionIp = endSectionIp.trim();
		ip = ip.trim();
		String REGX_IP = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

		if ((!startSectionIp.matches(
				"^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$"))
				|| (!endSectionIp.matches(
						"^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$"))
				|| (!ip.matches(
						"^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$")))
			return false;
		String[] sips = startSectionIp.split("\\.");
		String[] sipe = endSectionIp.split("\\.");
		String[] sipt = ip.split("\\.");
		long ips = 0L;
		long ipe = 0L;
		long ipt = 0L;
		for (int i = 0; i < 4; i++) {
			ips = ips << 8 | Integer.parseInt(sips[i]);
			ipe = ipe << 8 | Integer.parseInt(sipe[i]);
			ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		}
		if (ips > ipe) {
			long t = ips;
			ips = ipe;
			ipe = t;
		}
		return (ips <= ipt) && (ipt <= ipe);
	}

	public static boolean isValidIp4(String ipaddress) {
		Assert.notNull(ipaddress);
		if (ipaddress.replaceAll("\\d", "").length() == 3) {
			return true;
		}
		return false;
	}
}
