package com.bridgelabz.indianstatesanalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCodes {
	@CsvBindByName(column = "SrNo")
	private int serialNo;
	@CsvBindByName(column = "State Name")
	private String name;
	@CsvBindByName(column = "TIN")
	private int tin;
	@CsvBindByName(column = "StateCode")
	private String stateCode;

	/**
	 * @return the serialNo
	 */
	public int getSerialNo() {
		return serialNo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the tin
	 */
	public int getTin() {
		return tin;
	}

	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	@Override
	public String toString() {
		return "StateCodes [serialNo=" + serialNo + ", name=" + name + ", tin=" + tin + ", stateCode=" + stateCode
				+ "]";
	}

	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param tin the tin to set
	 */
	public void setTin(int tin) {
		this.tin = tin;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
