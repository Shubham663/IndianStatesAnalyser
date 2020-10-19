package com.bridgelabz.indianstatesanalyser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main ( String[] args ) throws CustomException, IOException{
        System.out.println( "Hello World!" );
        StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv",States.class);
    }
}
