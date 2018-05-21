package com.piedpiper.platform.commons.utils;

import java.util.ArrayList;

public class UtilString {
	public static String removeLastChar(String str, String regex) {
		if ((str == null) || (regex == null) || (regex.equals(""))) {
			return str;
		}
		if (!str.endsWith(regex)) {
			return str;
		}
		return str.substring(0, str.length() - regex.length());
	}

	public static ArrayList<String> split(String strSuper, String strSign) {
		int begin = 0;
		int end = 0;
		ArrayList<String> vecList = new ArrayList();
		if (strSign == "") {
			for (int i = 0; i < strSuper.length(); i++) {
				vecList.add(strSuper.substring(i, i + 1));
			}
			return vecList;
		}
		end = strSuper.indexOf(strSign);
		if (end == -1) {
			vecList.add(strSuper);
			return vecList;
		}
		while (end >= 0) {
			vecList.add(strSuper.substring(begin, end));
			begin = end + strSign.length();
			end = strSuper.indexOf(strSign, begin);
		}
		vecList.add(strSuper.substring(begin, strSuper.length()));
		return vecList;
	}

	public static int matchOf(String strInit, String strBegin) {
		return matchOf(strInit, strBegin, false);
	}

	public static int matchOf(String strInit, String strBegin, boolean ignoreCase) {
		int pos = -1;
		String lowcaseSuper = strInit.toLowerCase();
		if (ignoreCase) {
			pos = lowcaseSuper.indexOf(strBegin.toLowerCase());
		} else {
			pos = strInit.indexOf(strBegin);
		}

		if (pos < 0) {
			return pos;
		}
		return pos + strBegin.length();
	}

	public static int lastMatchOf(String strInit, String strEnd) {
		return lastMatchOf(strInit, strEnd, false);
	}

	public static int lastMatchOf(String strInit, String strEnd, boolean ignoreCase) {
		String lowcaseSuper = strInit.toLowerCase();
		if (ignoreCase) {
			return lowcaseSuper.lastIndexOf(strEnd.toLowerCase());
		}
		return strInit.lastIndexOf(strEnd);
	}

	public String matchValue(String strInit, String strBegin, String strEnd) {
		return matchValue(strInit, strBegin, strEnd, false);
	}

	public static String matchValue(String strInit, String strBegin, String strEnd, boolean ignoreCase) {
		String strResult = new String();

		int posBegin = matchOf(strInit, strBegin, ignoreCase);
		int posEnd = lastMatchOf(strInit, strEnd, ignoreCase);

		if ((posEnd > posBegin) && (posBegin != -1)) {
			strResult = strInit.substring(posBegin, posEnd);
		}

		return strResult;
	}

	public static String replace(String strInit, String strOld, String strNew) {
		return replace(strInit, strOld, strNew, false);
	}

	public static String replace(String strInit, String strOld, String strNew, boolean ignoreCase) {
		String lowcaseSuper = strInit.toLowerCase();
		String strResult = new String();

		String strReplace;
		String strCore;
		if (ignoreCase) {
			strCore = lowcaseSuper;
			strReplace = strOld.toLowerCase();
		} else {
			strCore = strInit;
			strReplace = strOld;
		}

		int posBegin = 0;
		int posEnd = strCore.indexOf(strReplace);

		if (posEnd < 0) {
			return strInit;
		}

		while (posEnd >= 0) {
			strResult = strResult + strInit.substring(posBegin, posEnd);
			strResult = strResult + strNew;
			posBegin = posEnd + strReplace.length();
			posEnd = strCore.indexOf(strReplace, posBegin);
		}

		strResult = strResult + strInit.substring(posBegin);

		return strResult.toString();
	}

	public static String replace(String strInit, String strBegin, String strEnd, String strNew) {
		return replace(strInit, strBegin, strEnd, strNew, false);
	}

	public static String replace(String strInit, String strBegin, String strEnd, String strNew, boolean ignoreCase) {
		int posBegin = matchOf(strInit, strBegin, ignoreCase);
		int posEnd = lastMatchOf(strInit, strEnd, ignoreCase);
		String strResult = new String();

		if ((posEnd >= posBegin) && (posBegin != -1)) {
			strResult = strInit.substring(0, posBegin);
			strResult = strResult + strNew;
			strResult = strResult + strInit.substring(posEnd);

			return strResult;
		}
		return strInit;
	}

	public static boolean startsWith(String strInit, String strBegin) {
		return startsWith(strInit, strBegin, false);
	}

	public static boolean startsWith(String strInit, String strBegin, boolean ignoreCase) {
		String lowcaseSuper = strInit.toLowerCase();
		if (ignoreCase) {
			return lowcaseSuper.startsWith(strBegin.toLowerCase());
		}
		return strInit.startsWith(strBegin);
	}

	public static boolean endsWith(String strInit, String strEnd) {
		return endsWith(strInit, strEnd, false);
	}

	public static boolean endsWith(String strInit, String strEnd, boolean ignoreCase) {
		String lowcaseSuper = strInit.toLowerCase();
		if (ignoreCase) {
			return lowcaseSuper.endsWith(strEnd.toLowerCase());
		}
		return strInit.endsWith(strEnd);
	}

	public static String cutString(String str, int length) {
		if (str == null) {
			return "";
		}

		if ((str.length() > length) && (length > 2)) {
			return str.substring(0, length) + ".";
		}
		return str;
	}

	public static String abbreviate(String str, int maxWidth) {
		return abbreviate(str, 0, maxWidth);
	}

	public static String abbreviate(String str, int offset, int maxWidth) {
		if (str == null) {
			return null;
		}
		if (maxWidth < 4) {
			throw new IllegalArgumentException("Minimum abbreviation width is 4");
		}
		if (str.length() <= maxWidth) {
			return str;
		}
		if (offset > str.length()) {
			offset = str.length();
		}
		if (str.length() - offset < maxWidth - 3) {
			offset = str.length() - (maxWidth - 3);
		}
		if (offset <= 4) {
			return str.substring(0, maxWidth - 3) + "...";
		}
		if (maxWidth < 7) {
			throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
		}
		if (offset + (maxWidth - 3) < str.length()) {
			return "..." + abbreviate(str.substring(offset), maxWidth - 3);
		}
		return "..." + str.substring(str.length() - (maxWidth - 3));
	}
}
