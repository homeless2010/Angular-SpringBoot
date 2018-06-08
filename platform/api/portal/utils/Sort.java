package com.piedpiper.platform.api.portal.utils;

import java.util.Comparator;

public class Sort<T extends Comparable<T>> implements Comparator<T> {
	public boolean equals(Object obj) {
		return equals(obj);
	}

	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}
