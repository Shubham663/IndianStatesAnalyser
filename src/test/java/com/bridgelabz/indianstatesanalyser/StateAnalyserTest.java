package com.bridgelabz.indianstatesanalyser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.bridgelabz.csvreader.CustomException;
/**
 * Unit test for StateAnalyser.
 */
public class StateAnalyserTest {
	@Test
	public void recordsCountTest_returnsFullRecords() throws IOException, CustomException {
		assertEquals(29, StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv",
				States.class));
	}

	@Test
	public void invalidRecordsTest_doesNotReadAllRecords() throws IOException, CustomException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData", States.class));
	}

	@Test
	public void invalidClassTypeTest_doesNotReadAllRecords() throws IOException, CustomException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv", StateCensusAnalyser.class));
	}

	@Test
	public void incorrectDelimetersTest_doesNotReadAllRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData_3.csv", States.class));
	}

	@Test
	public void incorrectHeadersTest_doesNotReadAllRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData_4.csv", States.class));
	}

	@Test
	public void recordsCountStateCodeTest_returnsFullRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		assertEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode.csv", StateCodes.class));
	}

	@Test
	public void invalidRecordsStateCodeTest_doesNotReadAllRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode_2", StateCodes.class));
	}

	@Test
	public void invalidClassTypeStateCodeTest_doesNotReadAllRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode.csv", StateAnalyserTest.class));
	}

	@Test
	public void incorrectDelimetersStateCodeTest_doesNotReadAllRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode_3.csv", StateCodes.class));
	}

	@Test
	public void incorrectHeadersStateCodeTest_doesNotReadAllRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode_4.csv", StateCodes.class));
	}
	
	@Test
	public void correctSortedOrderTest_SortsAllRecords() throws IOException, com.bridgelabz.csvreader.CustomException {
		String []vals = StateCensusAnalyser.readStatesCensusFromCsvAsJson("IndiaStateCensusData - IndiaStateCensusData.csv", States.class)
				.split(",");
		assertTrue( vals[0].split(":")[1].equals("\"Andhra Pradesh\"") );
		assertTrue( vals[vals.length - 4].split(":")[1].equals("\"West Bengal\"") );
	}
}
