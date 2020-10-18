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
	public static int readStatesCensusFromCsv(String fileName){
		Logger logger = LogManager.getLogger(CSVStates.class);
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(fileName));
		} catch (IOException e) {
			logger.error("Unable to open the mentioned file " + fileName);
			return 0;
		}
		CsvToBean<StateCodes> csvToBean = new CsvToBeanBuilder<StateCodes>(reader).withType(StateCodes.class).withIgnoreLeadingWhiteSpace(true).build();

		Iterator<StateCodes> iterator = csvToBean.iterator();
		int count = 1;
		while(iterator.hasNext()) {
			StateCodes states = iterator.next();
			logger.info(states + " got from " + (count++) + " object");
		}
		return (count-1);
	}
}