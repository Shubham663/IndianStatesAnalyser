package com.bridgelabz.indianstatesanalyser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class StateAnalyserTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void recordsCountTest_returnsFullRecords(){
        assertEquals(29,StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv") );
    }
    
    @Test
    public void recordsCountTest_returnsZero() {
        assertEquals(0,StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData") );
    }
}
