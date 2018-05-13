package com.fitbit.api.models;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("dateTime")
	private String dateTime;

	@SerializedName("seconds")
	private int seconds;

	@SerializedName("level")
	private String level;

	public void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}

	public String getDateTime(){
		return dateTime;
	}

	public void setSeconds(int seconds){
		this.seconds = seconds;
	}

	public int getSeconds(){
		return seconds;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"dateTime = '" + dateTime + '\'' + 
			",seconds = '" + seconds + '\'' + 
			",level = '" + level + '\'' + 
			"}";
		}
}