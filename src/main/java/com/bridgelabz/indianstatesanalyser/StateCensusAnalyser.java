package com.bridgelabz.indianstatesanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.csvreader.CSVBuilderFactory;
import com.bridgelabz.csvreader.CustomException;
import com.bridgelabz.csvreader.ICSVBuilder;
import com.bridgelabz.csvreader.OpenCSVBuilder;

/**
 * @author Shubham, class for analysing indian states.
 *
 */
public class StateCensusAnalyser {
	public static <T> int readStatesCensusFromCsv(String fileName, T whichClass)
			throws IOException, com.bridgelabz.csvreader.CustomException {
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		List<T> listOfObjects = new ArrayList<T>();
		Reader reader = null;
		try {
			String line = null;
			ICSVBuilder csvBuilder = CSVBuilderFactory.generateBuilder();
			List<T> list = csvBuilder.getList(fileName, reader, whichClass);
			reader = Files.newBufferedReader(Paths.get(fileName));
			BufferedReader bufferedReader = new BufferedReader(reader);
			for (T state : list) {
				if (((line = bufferedReader.readLine()) != null) && line.split(",").length != 4)
					throw new CustomException("Correct delimeters are not present");
//				T state = (T)iterator.next();
				listOfObjects.add(state);
				logger.info(state.toString());
			}
		} catch (CustomException customException) {
			logger.error(customException.getMessage());
		} finally {
			return listOfObjects.size();
		}
	}
}
