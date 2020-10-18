package com.bridgelabz.indianstatesanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.beanutils.ConversionException;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * @author Shubham, class for analysing indian states.
 *
 */
public class StateCensusAnalyser {
	public static int readStatesCensusFromCsv(String fileName) throws IOException, CustomException {
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		int val = 0, count = 0;
		Reader reader = null;
		count = 1;
		reader = Files.newBufferedReader(Paths.get(fileName));
		CsvToBean<States> csvToBean = new CsvToBeanBuilder<States>(reader).withType(States.class).withThrowExceptions(false)
				.withIgnoreLeadingWhiteSpace(true).build();
		Iterator<States> iterator = csvToBean.iterator();
		while (iterator.hasNext()) {
			States states = iterator.next();
			if (states.getName() == null || states.getAreaInSquareKm() == 0 || states.getDensityPerSquareKm() == 0
					|| states.getPopulation() == 0)
				throw new CustomException("The csv file does not contain the data correctly.");
			logger.info(states + " got from " + (count++) + " object");
			val = count - 1;

		}
		csvToBean.getCapturedExceptions().stream().forEach(exception -> {
			try {
				throw new CustomException(exception.getMessage());
			}catch(CustomException customException) {
				logger.error("The delimiter is incorrect and therefor " + customException.getMessage());
			}
		});
		return val;
	}

}
