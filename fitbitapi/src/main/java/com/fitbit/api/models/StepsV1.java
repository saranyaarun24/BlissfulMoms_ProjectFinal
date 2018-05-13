package com.fitbit.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class StepsV1{

	@SerializedName("activities-steps")
	private List<ActivitiesStepsItem> activitiesSteps;

	public void setActivitiesSteps(List<ActivitiesStepsItem> activitiesSteps){
		this.activitiesSteps = activitiesSteps;
	}

	public List<ActivitiesStepsItem> getActivitiesSteps(){
		return activitiesSteps;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"activities-steps = '" + activitiesSteps + '\'' + 
			"}";
		}
}