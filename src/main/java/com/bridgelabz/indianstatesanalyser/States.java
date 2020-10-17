package com.bridgelabz.indianstatesanalyser;

import com.opencsv.bean.CsvBindByName;

public class States {
	@CsvBindByName(column = "State")
	private String name;
	@CsvBindByName(column = "Population")
	private int population;
	@CsvBindByName(column = "AreaInSqKm")
	private double areaInSquareKm;
	@CsvBindByName(column = "DensityPerSqKm")
	private double densityPerSquareKm;
	/**
	 * @param name, name of the state
	 * @param population, population of the state
	 * @param areaInSquareKm, area in square km's of the state
	 * @param densityPerSquareKm, density per square km for the state
	 */
	public States(String name, int population, double areaInSquareKm, double densityPerSquareKm) {
		super();
		this.name = name;
		this.population = population;
		this.areaInSquareKm = areaInSquareKm;
		this.densityPerSquareKm = densityPerSquareKm;
	}
	/**
	 * Empty constructor for use by the opencsv library while parsing data
	 */
	public States() {
	}
	
	@Override
	public String toString() {
		return "States [name=" + name + ", population=" + population + ", areaInSquareKm=" + areaInSquareKm
				+ ", densityPerSquareKm=" + densityPerSquareKm + "]";
	}
}
