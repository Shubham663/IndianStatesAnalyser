package com.bridgelabz.indianstatesanalyser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.apache.logging.log4j.LogManager;

/**
 * @author Shubham, class for analysing indian states.
 *
 */
public class StateCensusAnalyser {
	public static int readStatesCensusFromCsv(String fileName) throws CustomException{
		Logger logger = LogManager.getLogger(StateCensusAnalyser.class);
		int val = 0,count;
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(fileName));
			CsvToBean<States> csvToBean = new CsvToBeanBuilder<States>(reader).withType(States.class).withIgnoreLeadingWhiteSpace(true).build();
			
			Iterator<States> iterator = csvToBean.iterator();
			count = 1;
			while(iterator.hasNext()) {
				States states = iterator.next();
				logger.info(states + " got from " + (count++) + " object");
			}
			val = count-1;
		} catch (IOException e) {
			logger.error("Unable to open the mentioned file " + fileName);
			throw new CustomException("Unable to open the mentioned file " + fileName);
		}
		finally {
			return val;
		}
	}
}
