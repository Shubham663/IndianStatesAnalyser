package com.bridgelabz.indianstatesanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.commons.beanutils.ConversionException;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class OpenCSVBuilder implements ICSVBuilder{

	public <T> Iterator getIterator(String fileName, Reader reader, T whichClass)
			throws IOException, CustomException {
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
		for (CsvException csvException : csvToBean.getCapturedExceptions()) {
			if (csvException.getCause() instanceof ConversionException) {
				throw new CustomException("Invalid data present inside CSV file, " + csvException.getMessage());
			}
		}
		return iterator;
	}

	private static <T> void checkHeadersCorrectness(BufferedReader bufferedReader, T whichClass)
			throws IOException, CustomException {
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
