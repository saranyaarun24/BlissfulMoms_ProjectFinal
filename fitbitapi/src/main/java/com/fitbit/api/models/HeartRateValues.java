package com.fitbit.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HeartRateValues {

	@SerializedName("restingHeartRate")
	private int restingHeartRate;

	@SerializedName("customHeartRateZones")
	private List<Object> customHeartRateZones;

	@SerializedName("heartRateZones")
	private List<HeartRateZonesItem> heartRateZones;

	public void setRestingHeartRate(int restingHeartRate){
		this.restingHeartRate = restingHeartRate;
	}

	public int getRestingHeartRate(){
		return restingHeartRate;
	}

	public void setCustomHeartRateZones(List<Object> customHeartRateZones){
		this.customHeartRateZones = customHeartRateZones;
	}

	public List<Object> getCustomHeartRateZones(){
		return customHeartRateZones;
	}

	public void setHeartRateZones(List<HeartRateZonesItem> heartRateZones){
		this.heartRateZones = heartRateZones;
	}

	public List<HeartRateZonesItem> getHeartRateZones(){
		return heartRateZones;
	}

	@Override
 	public String toString(){
		return 
			"HeartRateValues{" +
			"restingHeartRate = '" + restingHeartRate + '\'' + 
			",customHeartRateZones = '" + customHeartRateZones + '\'' + 
			",heartRateZones = '" + heartRateZones + '\'' + 
			"}";
		}
}