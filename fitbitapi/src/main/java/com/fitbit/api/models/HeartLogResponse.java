package com.fitbit.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HeartLogResponse {

	@SerializedName("activities-heart")
	private List<ActivitiesHeartItem> activitiesHeart;

	public void setActivitiesHeart(List<ActivitiesHeartItem> activitiesHeart){
		this.activitiesHeart = activitiesHeart;
	}

	public List<ActivitiesHeartItem> getActivitiesHeart(){
		return activitiesHeart;
	}

	@Override
 	public String toString(){
		return 
			"HeartLogResponse{" +
			"activities-heart = '" + activitiesHeart + '\'' + 
			"}";
		}
}