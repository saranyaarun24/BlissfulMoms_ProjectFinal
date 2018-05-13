package com.fitbit.api.models;

import com.google.gson.annotations.SerializedName;

public class Light{

	@SerializedName("minutes")
	private int minutes;

	@SerializedName("count")
	private int count;

	@SerializedName("thirtyDayAvgMinutes")
	private int thirtyDayAvgMinutes;

	public void setMinutes(int minutes){
		this.minutes = minutes;
	}

	public int getMinutes(){
		return minutes;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setThirtyDayAvgMinutes(int thirtyDayAvgMinutes){
		this.thirtyDayAvgMinutes = thirtyDayAvgMinutes;
	}

	public int getThirtyDayAvgMinutes(){
		return thirtyDayAvgMinutes;
	}

	@Override
 	public String toString(){
		return 
			"Light{" + 
			"minutes = '" + minutes + '\'' + 
			",count = '" + count + '\'' + 
			",thirtyDayAvgMinutes = '" + thirtyDayAvgMinutes + '\'' + 
			"}";
		}
}