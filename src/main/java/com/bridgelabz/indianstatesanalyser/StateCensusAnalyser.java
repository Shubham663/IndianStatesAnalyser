package com.bridgelabz.indianstatesanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

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
	public static int readStatesCensusFromCsv(String fileName, Class whichClass) throws IOException, CustomException {
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		int val = 0, count = 0;
		Reader reader = null;
		try {
			count = 1;
			String line = null;
			reader = Files.newBufferedReader(Paths.get(fileName));
			BufferedReader bufferedReader = new BufferedReader(reader);
			if ((line = bufferedReader.readLine()) != null) {
				String[] headers = line.split(",");
				if (headers.length != 4 || !headers[0].equals("State") || !headers[1].equals("Population")
						|| !headers[2].equals("AreaInSqKm") || !headers[3].equals("DensityPerSqKm")) {
					throw new CustomException("The headers are not correct for the CSV file");
				}
			}
			reader = Files.newBufferedReader(Paths.get(fileName));
			if (!whichClass.equals(States.class)) {
				throw new CustomException("The type of class specified is not correct");
			}
			CsvToBean<States> csvToBean = new CsvToBeanBuilder(reader).withType((Class) whichClass)
					.withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).withThrowExceptions(false).build();
			Iterator<States> iterator = csvToBean.iterator();
			while (iterator.hasNext()) {
				count++;
				States state = iterator.next();
				if (state.getName() == null || state.getDensityPerSquareKm() == 0 || state.getAreaInSquareKm() == 0
						|| state.getPopulation() == 0) {
					throw new CustomException("Correct delimeters are not present");
				}
				logger.info(state.toString());
			}
			val = count - 1;
			for (CsvException csvException : csvToBean.getCapturedExceptions()) {
				if (csvException.getCause() instanceof ConversionException) {
					throw new CustomException("Invalid data present inside CSV file, " + csvException.getMessage());
				}
				;
				throw new CustomException(csvException.getMessage());
			}
		} catch (CustomException customException) {
			logger.error(customException.getMessage());
		} finally {
			return val;
		}
	}
}
