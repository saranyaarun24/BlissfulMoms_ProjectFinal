package com.fitbit.api.models;

import com.google.gson.annotations.SerializedName;

public class ActivitiesStepsItem{

	@SerializedName("dateTime")
	private String dateTime;

	@SerializedName("value")
	private String value;

	public void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}

	public String getDateTime(){
		return dateTime;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"ActivitiesStepsItem{" + 
			"dateTime = '" + dateTime + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}