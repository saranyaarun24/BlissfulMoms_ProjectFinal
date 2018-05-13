package com.fitbit.api.models;

import com.google.gson.annotations.SerializedName;

public class ActivitiesHeartItem{

	@SerializedName("dateTime")
	private String dateTime;

	@SerializedName("value")
	private HeartRateValues value;

	public void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}

	public String getDateTime(){
		return dateTime;
	}

	public void setValue(HeartRateValues heartRateValues){
		this.value = heartRateValues;
	}

	public HeartRateValues getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"ActivitiesHeartItem{" + 
			"dateTime = '" + dateTime + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}