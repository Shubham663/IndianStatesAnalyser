package com.bridgelabz.indianstatesanalyser;

import java.util.Comparator;

public class CompareState {
	
	static class CompareStateByStateArea implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			return (int)(((States)o2).getAreaInSquareKm()-((States)o1).getAreaInSquareKm());
		}

	}

	static class CompareStatePopulationDensity implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			return (int)(((States)o2).getDensityPerSquareKm()-((States)o1).getDensityPerSquareKm());
		}

	}
	
	static class CompareStatePopulation implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			return ((States)o2).getPopulation()-((States)o1).getPopulation();
		}

	}

	static class CompareStateName implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			return ((States) o1).getName().compareTo(((States) o2).getName());
		}
	}
}
