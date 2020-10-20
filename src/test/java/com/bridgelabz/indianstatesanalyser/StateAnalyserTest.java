package com.bridgelabz.indianstatesanalyser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Test;

import com.bridgelabz.csvreader.CustomException;

/**
 * Unit test for StateAnalyser.
 */
public class StateAnalyserTest {
	@Test
	public void recordsCountTest_returnsFullRecords() throws CustomException, IOException {
		assertEquals(29, StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv",
				States.class));
	}

	@Test
	public void invalidRecordsTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData_2.csv", States.class));
	}

	@Test
	public void invalidClassTypeTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv", StateCensusAnalyser.class));
	}

	@Test
	public void incorrectDelimetersTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData_3.csv", States.class));
	}

	@Test
	public void incorrectHeadersTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(29, StateCensusAnalyser
				.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData_4.csv", States.class));
	}

	@Test
	public void recordsCountStateCodeTest_returnsFullRecords() throws CustomException, IOException {
		assertEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode.csv", StateCodes.class));
	}

	@Test
	public void invalidRecordsStateCodeTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode_2.csv", StateCodes.class));
	}

	@Test
	public void invalidClassTypeStateCodeTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode.csv", StateAnalyserTest.class));
	}

	@Test
	public void incorrectDelimetersStateCodeTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode_3.csv", StateCodes.class));
	}

	@Test
	public void incorrectHeadersStateCodeTest_doesNotReadAllRecords() throws CustomException, IOException {
		assertNotEquals(37,
				StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCode - IndiaStateCode_4.csv", StateCodes.class));
	}
}
