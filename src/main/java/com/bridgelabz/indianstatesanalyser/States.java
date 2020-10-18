package com.bridgelabz.indianstatesanalyser;

import com.opencsv.bean.CsvBindByName;

public class States {
	@CsvBindByName(column = "State", required = true)
	private String name;
	@CsvBindByName(column = "Population", required = true)
	private int population;
	@CsvBindByName(column = "AreaInSqKm", required = true)
	private double areaInSquareKm;
	@CsvBindByName(column = "DensityPerSqKm", required = true)
	private double densityPerSquareKm;

	/**
	 * @param name,               name of the state
	 * @param population,         population of the state
	 * @param areaInSquareKm,     area in square km's of the state
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * @return the areaInSquareKm
	 */
	public double getAreaInSquareKm() {
		return areaInSquareKm;
	}

	/**
	 * @return the densityPerSquareKm
	 */
	public double getDensityPerSquareKm() {
		return densityPerSquareKm;
	}

	@Override
	public String toString() {
		return "States [name=" + name + ", population=" + population + ", areaInSquareKm=" + areaInSquareKm
				+ ", densityPerSquareKm=" + densityPerSquareKm + "]";
	}
}
