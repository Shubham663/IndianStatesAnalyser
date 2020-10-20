package com.bridgelabz.indianstatesanalyser;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
	public<T> Iterator getIterator(String fileName, Reader reader,T whichClass) throws IOException, CustomException;
}
