package com.Technion.ie.Baseline;

import java.util.HashMap;

public class ParametersCounts {
	
	private HashMap <String, Double> tParameter;// (NULL,0.5), (cat+acat,0.3),(table+atable,0.1)
	private HashMap <String, Double> eParameter;//c(e) - expectedCountsEnglish
	private HashMap <String, Double> efParameter;//c(e,f) - expectedCountsEnglishForeign
	
	public ParametersCounts()
	{
		this.tParameter = new HashMap<String,Double>();
		this.eParameter = new HashMap<String,Double>();
		this.efParameter = new HashMap<String,Double>();
	}
	
	public HashMap<String, Double> getTParameter()
	{
		return this.tParameter;
	}
	
	public HashMap<String, Double> getEParameter()
	{
		return this.eParameter;
	}
	
	public HashMap<String, Double> getEFParameter()
	{
		return this.efParameter;
	}
	
	public void set_eParameter (String word, Double value)
	{
		this.eParameter.put(word, value);
	}
	
	public void set_efParameter (String key, Double value)
	{
		this.efParameter.put(key, value);
	}
}
