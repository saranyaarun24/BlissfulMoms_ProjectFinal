package com.fitbit.api.models;

import com.google.gson.annotations.SerializedName;

public class Summary{

	@SerializedName("deep")
	private Deep deep;

	@SerializedName("wake")
	private Wake wake;

	@SerializedName("light")
	private Light light;

	@SerializedName("rem")
	private Rem rem;

	public void setDeep(Deep deep){
		this.deep = deep;
	}

	public Deep getDeep(){
		return deep;
	}

	public void setWake(Wake wake){
		this.wake = wake;
	}

	public Wake getWake(){
		return wake;
	}

	public void setLight(Light light){
		this.light = light;
	}

	public Light getLight(){
		return light;
	}

	public void setRem(Rem rem){
		this.rem = rem;
	}

	public Rem getRem(){
		return rem;
	}

	@Override
 	public String toString(){
		return 
			"Summary{" + 
			"deep = '" + deep + '\'' + 
			",wake = '" + wake + '\'' + 
			",light = '" + light + '\'' + 
			",rem = '" + rem + '\'' + 
			"}";
		}
}