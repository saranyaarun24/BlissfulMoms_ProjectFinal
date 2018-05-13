package com.fitbit.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SleepRate{

	@SerializedName("sleep")
	private List<SleepItem> sleep;

	public void setSleep(List<SleepItem> sleep){
		this.sleep = sleep;
	}

	public List<SleepItem> getSleep(){
		return sleep;
	}

	@Override
 	public String toString(){
		return 
			"SleepRate{" + 
			"sleep = '" + sleep + '\'' + 
			"}";
		}
}