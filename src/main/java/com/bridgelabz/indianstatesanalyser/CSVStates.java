package com.bridgelabz.indianstatesanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * @author Shubham, parses the states code csv file for operations
 */
public class CSVStates {
	public static int readStatesCensusFromCsv(String fileName) throws IOException {
		Logger logger = LogManager.getLogger(CSVStates.class);
		int count = 1, val = 0;
		Reader reader = null;
		try {
			count = 1;
			reader = Files.newBufferedReader(Paths.get(fileName));
			CsvToBean<StateCodes> csvToBean = new CsvToBeanBuilder<StateCodes>(reader).withType(StateCodes.class)
					.withThrowExceptions(false).withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCodes> iterator = csvToBean.iterator();
			while (iterator.hasNext()) {
				StateCodes states = iterator.next();
				logger.info(states + " got from " + (count++) + " object");
				val = count - 1;

			}
		} catch (RuntimeException exception) {
			logger.error(exception.getMessage());
			throw new CustomException(exception.getMessage());
		} finally {
			return val;
		}
	}
}