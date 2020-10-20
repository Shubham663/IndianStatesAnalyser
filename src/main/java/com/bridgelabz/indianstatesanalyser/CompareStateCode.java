package com.bridgelabz.indianstatesanalyser;

import java.util.Comparator;

public class CompareStateCode<T> implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		return ((StateCodes)o1).getStateCode().compareTo(((StateCodes)o2).getStateCode());
	}

}
