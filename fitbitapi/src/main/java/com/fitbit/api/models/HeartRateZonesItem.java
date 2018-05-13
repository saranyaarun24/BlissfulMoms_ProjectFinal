package com.fitbit.api.models;

import com.google.gson.annotations.SerializedName;

public class HeartRateZonesItem{

	@SerializedName("min")
	private int min;

	@SerializedName("max")
	private int max;

	@SerializedName("caloriesOut")
	private double caloriesOut;

	@SerializedName("minutes")
	private int minutes;

	@SerializedName("name")
	private String name;

	public void setMin(int min){
		this.min = min;
	}

	public int getMin(){
		return min;
	}

	public void setMax(int max){
		this.max = max;
	}

	public int getMax(){
		return max;
	}

	public void setCaloriesOut(double caloriesOut){
		this.caloriesOut = caloriesOut;
	}

	public double getCaloriesOut(){
		return caloriesOut;
	}

	public void setMinutes(int minutes){
		this.minutes = minutes;
	}

	public int getMinutes(){
		return minutes;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"HeartRateZonesItem{" + 
			"min = '" + min + '\'' + 
			",max = '" + max + '\'' + 
			",caloriesOut = '" + caloriesOut + '\'' + 
			",minutes = '" + minutes + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}