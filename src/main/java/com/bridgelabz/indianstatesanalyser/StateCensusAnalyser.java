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

/**
 * @author Shubham, class for analysing indian states.
 *
 */
public class StateCensusAnalyser {
	public static <T> int readStatesCensusFromCsv(String fileName, T whichClass) throws IOException, CustomException {
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		List<T> listOfObjects = new ArrayList<T>();
		Reader reader = null;
		try {
			String line = null;
			ICSVBuilder csvBuilder = CSVBuilderFactory.generateBuilder();
			Iterator iterator = csvBuilder.getIterator(fileName,reader,whichClass);
			reader = Files.newBufferedReader(Paths.get(fileName));
			BufferedReader bufferedReader = new BufferedReader(reader);
			while (iterator.hasNext()) {
				if ( ((line = bufferedReader.readLine()) != null) && line.split(",").length != 4)
					throw new CustomException("Correct delimeters are not present");
				T state = (T)iterator.next();
				listOfObjects.add(state);
				logger.info(state.toString());
			}
		} catch (CustomException customException) {
			logger.error(customException.getMessage());
		}
		return listOfObjects.size();
	}
}
