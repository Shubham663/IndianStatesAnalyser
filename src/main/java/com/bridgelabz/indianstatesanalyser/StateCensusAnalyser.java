package com.bridgelabz.indianstatesanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.csvreader.CSVBuilderFactory;
import com.bridgelabz.csvreader.CustomException;
import com.bridgelabz.csvreader.ICSVBuilder;
import com.google.gson.Gson;

/**
 * @author Shubham, class for analysing indian states.
 *
 */
public class StateCensusAnalyser {
	public static <T> int readStatesCensusFromCsv(String fileName, Class whichClass)
			throws IOException, com.bridgelabz.csvreader.CustomException {
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		List<T> listOfObjects = null;
		Reader reader = null;
		try {
			String line = null;
			List<T> list = getList(fileName, reader, whichClass);
			listOfObjects = checkForCorrectDelimeters(fileName,list);
		} catch (CustomException customException) {
			logger.error(customException.getMessage());
		} finally {
			return listOfObjects == null ? 0 : listOfObjects.size();
		}
	}
	
	private static<T> List<T> checkForCorrectDelimeters(String fileName,List<T> list) throws IOException, CustomException {
		Reader reader = Files.newBufferedReader(Paths.get(fileName));
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = null;
		int count = 0;
		List<T> listOfObjects = new ArrayList<>();
		line = bufferedReader.readLine();
		while ( (line = bufferedReader.readLine()) != null ) {
			if (line.split(",").length != 4) {
				throw new CustomException("Correct delimeters are not present");
			}
			T state = list.get(count);
			count++;
			listOfObjects.add(state);
		}
		return listOfObjects;
	}

	public static<T> T[] readStatesCensusFromCsvAsJson(String fileName, Class whichClass,String field)
			throws IOException, com.bridgelabz.csvreader.CustomException {
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		List<T> listOfObjects = new ArrayList<T>();
		Reader reader = null;
		Gson gson = new Gson();
		String json = null;
		T []states = null;
		try {
			String line = null;
			List<T> list = getList(fileName, reader, whichClass);
			list = sort(list,whichClass,field);
			json = gson.toJson(list);
			if(list.get(0) instanceof States)
				states = (T[])gson.fromJson(json,States[].class);
			else
				states = (T[])gson.fromJson(json, StateCodes[].class);
			listOfObjects = checkForCorrectDelimeters(fileName,list);
		} catch (CustomException customException) {
			logger.error(customException.getMessage());
		} finally {
			return states;
		}
	}

	private static<T> List<T> getList(String fileName, Reader reader, Class whichClass) throws IOException, CustomException {
		ICSVBuilder csvBuilder = CSVBuilderFactory.generateBuilder();
		List<T> list = csvBuilder.getList(fileName, reader, whichClass);
		return list;
	}

	private static<T> List<T> sort(List<T> list,Class whichClass,String field) {
		if(whichClass.equals(States.class)) {
			if(field.equals("State Name"))
				Collections.sort(list,new CompareState.CompareStateName());
			else if(field.equals("Population"))
				Collections.sort(list,new CompareState.CompareStatePopulation());
			else if(field.equals("Population Density")) 
				Collections.sort(list,new CompareState.CompareStatePopulationDensity());
			else
				Collections.sort(list,new CompareState.CompareStateByStateArea());
		}
		else if(whichClass.equals(StateCodes.class))
			Collections.sort(list,new CompareStateCode());
		return list;
	}
}
