package com.bridgelabz.indianstatesanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.ConversionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

/**
 * @author Shubham, class for analysing indian states.
 *
 */
public class StateCensusAnalyser {
	public static <T> int readStatesCensusFromCsv(String fileName, T whichClass) throws IOException, CustomException {
		List<T> listOfObjects = getData(fileName, whichClass);
		return listOfObjects.size();
	}
	
	private static <T> List<T> getData(String fileName, T whichClass) {
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		List<T> listOfObjects = new ArrayList<T>();
		Reader reader = null;
		try {
			String line = null;
			reader = Files.newBufferedReader(Paths.get(fileName));
			BufferedReader bufferedReader = new BufferedReader(reader);
			checkHeadersCorrectness(bufferedReader, whichClass);
			reader = Files.newBufferedReader(Paths.get(fileName));
			if (!whichClass.equals(States.class) && !whichClass.equals(StateCodes.class)) {
				throw new CustomException("The type of class specified is not correct");
			}
			CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader).withType((Class) whichClass)
					.withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).withThrowExceptions(false).build();
			Iterator<T> iterator = csvToBean.iterator();
			while (iterator.hasNext()) {
				if ( ((line = bufferedReader.readLine()) != null) && (!line.contains(",") || line.split(",").length != 4))
					throw new CustomException("Correct delimeters are not present");
				T state = iterator.next();
				listOfObjects.add(state);
				logger.info(state.toString());
			}
			for (CsvException csvException : csvToBean.getCapturedExceptions()) {
				if (csvException.getCause() instanceof ConversionException) {
					throw new CustomException("Invalid data present inside CSV file, " + csvException.getMessage());
				}
			}
		} catch (CustomException customException) {
			logger.error(customException.getMessage());
		} finally {
			return listOfObjects;
		}
	}
	
	private static<T> void checkHeadersCorrectness(BufferedReader bufferedReader, T whichClass) throws IOException, CustomException {
		String line = null;
		if ((line = bufferedReader.readLine()) != null) {
			String[] headers = line.split(",");
			if ((whichClass.equals(States.class)
					&& (headers.length != 4 || !headers[0].equals("State") || !headers[1].equals("Population")
							|| !headers[2].equals("AreaInSqKm") || !headers[3].equals("DensityPerSqKm")))
					|| (whichClass.equals(StateCodes.class)
							&& (!headers[0].equals("SrNo") || !headers[1].equals("State Name")
									|| !headers[2].equals("TIN") || !headers[3].equals("StateCode")))) {
				throw new CustomException("The headers are not correct for the CSV file");
			}
		}
	}
}
