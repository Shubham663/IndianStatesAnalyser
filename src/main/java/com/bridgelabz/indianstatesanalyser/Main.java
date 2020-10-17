package com.bridgelabz.indianstatesanalyser;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main ( String[] args ) throws CustomException{
        System.out.println( "Hello World!" );
        StateCensusAnalyser.readStatesCensusFromCsv("IndiaStateCensusData - IndiaStateCensusData.csv");
    }
}
