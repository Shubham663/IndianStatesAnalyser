package com.bridgelabz.indianstatesanalyser;

import java.util.Comparator;

public class CompareStateName<T extends Comparable<T>> implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		return ((States) o1).getName().compareTo(((States) o2).getName());
	}
}
