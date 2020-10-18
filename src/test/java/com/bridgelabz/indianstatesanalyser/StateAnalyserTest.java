package com.bridgelabz.indianstatesanalyser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for StateAnalyser.
 */
public class StateAnalyserTest 
{
    @Test
    public void recordsCountTest_returnsFullRecords() throws CustomException, IOException{
        assertNotEquals(29,StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData_4.csv") );
    }
}
