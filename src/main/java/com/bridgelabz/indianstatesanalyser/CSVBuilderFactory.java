package com.bridgelabz.indianstatesanalyser;

public class CSVBuilderFactory {
	public static ICSVBuilder generateBuilder() {
		return new OpenCSVBuilder();
	}
}
