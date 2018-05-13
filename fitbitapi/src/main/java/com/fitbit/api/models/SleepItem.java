package com.fitbit.api.models;

import com.google.gson.annotations.SerializedName;

public class SleepItem{

	@SerializedName("efficiency")
	private int efficiency;

	@SerializedName("minutesAsleep")
	private int minutesAsleep;

	@SerializedName("infoCode")
	private int infoCode;

	@SerializedName("minutesAwake")
	private int minutesAwake;

	@SerializedName("type")
	private String type;

	@SerializedName("duration")
	private int duration;

	@SerializedName("minutesToFallAsleep")
	private int minutesToFallAsleep;

	@SerializedName("minutesAfterWakeup")
	private int minutesAfterWakeup;

	@SerializedName("dateOfSleep")
	private String dateOfSleep;

	@SerializedName("timeInBed")
	private int timeInBed;

	@SerializedName("logId")
	private long logId;

	@SerializedName("startTime")
	private String startTime;

	@SerializedName("endTime")
	private String endTime;

	@SerializedName("levels")
	private Levels levels;

	public void setEfficiency(int efficiency){
		this.efficiency = efficiency;
	}

	public int getEfficiency(){
		return efficiency;
	}

	public void setMinutesAsleep(int minutesAsleep){
		this.minutesAsleep = minutesAsleep;
	}

	public int getMinutesAsleep(){
		return minutesAsleep;
	}

	public void setInfoCode(int infoCode){
		this.infoCode = infoCode;
	}

	public int getInfoCode(){
		return infoCode;
	}

	public void setMinutesAwake(int minutesAwake){
		this.minutesAwake = minutesAwake;
	}

	public int getMinutesAwake(){
		return minutesAwake;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getDuration(){
		return duration;
	}

	public void setMinutesToFallAsleep(int minutesToFallAsleep){
		this.minutesToFallAsleep = minutesToFallAsleep;
	}

	public int getMinutesToFallAsleep(){
		return minutesToFallAsleep;
	}

	public void setMinutesAfterWakeup(int minutesAfterWakeup){
		this.minutesAfterWakeup = minutesAfterWakeup;
	}

	public int getMinutesAfterWakeup(){
		return minutesAfterWakeup;
	}

	public void setDateOfSleep(String dateOfSleep){
		this.dateOfSleep = dateOfSleep;
	}

	public String getDateOfSleep(){
		return dateOfSleep;
	}

	public void setTimeInBed(int timeInBed){
		this.timeInBed = timeInBed;
	}

	public int getTimeInBed(){
		return timeInBed;
	}

	public void setLogId(long logId){
		this.logId = logId;
	}

	public long getLogId(){
		return logId;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getStartTime(){
		return startTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return endTime;
	}

	public void setLevels(Levels levels){
		this.levels = levels;
	}

	public Levels getLevels(){
		return levels;
	}

	@Override
 	public String toString(){
		return 
			"SleepItem{" + 
			"efficiency = '" + efficiency + '\'' + 
			",minutesAsleep = '" + minutesAsleep + '\'' + 
			",infoCode = '" + infoCode + '\'' + 
			",minutesAwake = '" + minutesAwake + '\'' + 
			",type = '" + type + '\'' + 
			",duration = '" + duration + '\'' + 
			",minutesToFallAsleep = '" + minutesToFallAsleep + '\'' + 
			",minutesAfterWakeup = '" + minutesAfterWakeup + '\'' + 
			",dateOfSleep = '" + dateOfSleep + '\'' + 
			",timeInBed = '" + timeInBed + '\'' + 
			",logId = '" + logId + '\'' + 
			",startTime = '" + startTime + '\'' + 
			",endTime = '" + endTime + '\'' + 
			",levels = '" + levels + '\'' + 
			"}";
		}
}