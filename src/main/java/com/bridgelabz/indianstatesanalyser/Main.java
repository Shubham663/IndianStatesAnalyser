package com.bridgelabz.indianstatesanalyser;

import java.io.IOException;

import com.bridgelabz.commonscsvreader.CustomException;

/**
 * Hello world!
 *
 */
public class Main {
	public static void main(String[] args) throws IOException, com.bridgelabz.csvreader.CustomException {
		System.out.println("Hello World!");
		StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv", States.class);
	}
}
