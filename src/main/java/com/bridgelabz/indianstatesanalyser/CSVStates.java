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
 * @author Shubham, parses the states code csv file for operations
 */
public class CSVStates {
	public static int readStatesCensusFromCsv(String fileName, Class whichClass) throws IOException {
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
				if (headers.length != 4 || !headers[0].equals("SrNo") || !headers[1].equals("State Name")
						|| !headers[2].equals("TIN") || !headers[3].equals("StateCode")) {
					throw new CustomException("The headers are not correct for the CSV file");
				}
			}
			reader = Files.newBufferedReader(Paths.get(fileName));
			if (!whichClass.equals(StateCodes.class)) {
				throw new CustomException("The type of class specified is not correct");
			}
			CsvToBean<StateCodes> csvToBean = new CsvToBeanBuilder<StateCodes>(reader).withType((Class) whichClass)
					.withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).withThrowExceptions(false).build();
			Iterator<StateCodes> iterator = csvToBean.iterator();
			while (iterator.hasNext()) {
				count++;
				StateCodes state = iterator.next();
				if (state.getName() == null || state.getSerialNo() == 0 || state.getStateCode() == null
						|| state.getTin() == 0) {
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