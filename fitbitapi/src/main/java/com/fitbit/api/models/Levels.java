package com.fitbit.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Levels{

	@SerializedName("summary")
	private Summary summary;

	@SerializedName("shortData")
	private List<ShortDataItem> shortData;

	@SerializedName("data")
	private List<DataItem> data;

	public void setSummary(Summary summary){
		this.summary = summary;
	}

	public Summary getSummary(){
		return summary;
	}

	public void setShortData(List<ShortDataItem> shortData){
		this.shortData = shortData;
	}

	public List<ShortDataItem> getShortData(){
		return shortData;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Levels{" + 
			"summary = '" + summary + '\'' + 
			",shortData = '" + shortData + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}