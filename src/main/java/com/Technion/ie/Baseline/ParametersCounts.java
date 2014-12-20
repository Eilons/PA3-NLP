package com.Technion.ie.Baseline;

import java.util.HashMap;

public class ParametersCounts {
	
	private HashMap<String, Double> tParameter;// (NULL,0.5), (cat+acat,0.3),(table+atable,0.1)
	
	public ParametersCounts()
	{
		this.tParameter = new HashMap<String,Double>();
	}
	
	public HashMap<String, Double> getTParameter()
	{
		return this.tParameter;
	}

}
