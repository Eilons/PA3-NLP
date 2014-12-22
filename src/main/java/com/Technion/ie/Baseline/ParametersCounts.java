package com.Technion.ie.Baseline;

import java.util.HashMap;

public class ParametersCounts {
	
	private HashMap <String, Double> tParameter;// (NULL,0.5), (cat+acat,0.3),(table+atable,0.1)
	private HashMap <String, Double> eParameter;//c(e) - expectedCountsEnglish
	private HashMap <String, Double> efParameter;//c(e,f) - expectedCountsEnglishForeign
	private HashMap <String,Double> qParameter;//q(j|i,l,m) - (j+i+l+m,0.02)
	private HashMap <String,Double> jiPositionsParameter;//c(j|i,l,m)
	private HashMap <String,Double> iPositionsParameter;//c(i,l,m)
	
	public ParametersCounts()
	{
		this.tParameter = new HashMap<String,Double>();
		this.eParameter = new HashMap<String,Double>();
		this.efParameter = new HashMap<String,Double>();
		this.qParameter = new HashMap<String,Double>();
		this.jiPositionsParameter = new HashMap<String,Double>();
		this.iPositionsParameter = new HashMap<String,Double>();
		
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
	
	public HashMap<String,Double> getQParameter()
	{
		return this.qParameter;
	}
	
	public HashMap<String,Double> get_jiPositionsParameter()
	{
		return this.jiPositionsParameter;
	}
	
	public HashMap<String,Double> get_iPositionsParameter()
	{
		return this.iPositionsParameter;
	}
	
	public void set_eParameter (String word, Double value)
	{
		this.eParameter.put(word, value);
	}
	
	public void set_efParameter (String key, Double value)
	{
		this.efParameter.put(key, value);
	}
	
	public void set_jiPositionsParameter (String key, Double value)
	{
		this.jiPositionsParameter.put(key, value);
	}
	
	public void set_iPositionsParameter (String key, Double value)
	{
		this.iPositionsParameter.put(key, value);
	}
	
	
	
	
}
