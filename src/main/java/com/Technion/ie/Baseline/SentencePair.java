package com.Technion.ie.Baseline;

public class SentencePair {
	
	private String[] sentenceOne;
	private String[] sentenceTwo;
	
	public SentencePair ()
	{
		this.sentenceOne = new String[] {};
		this.sentenceTwo = new String[] {};
	}
	
	public SentencePair (String[] s1 , String[] s2)
	{
		this.sentenceOne = s1;
		this.sentenceTwo = s2;
	}
	
	public void setSentenceOne (String[] s1)
	{
		this.sentenceOne =s1;
	}
	
	public String[] getSentenceOne()
	{
		return this.sentenceOne;
	}
	
	public void setSentenceTwo (String[] s2)
	{
		this.sentenceTwo =s2;
	}
	
	public String[] getSentenceTwo ()
	{
		return this.sentenceTwo;
	}
	
	public boolean isSentenceOneContain (String word)
	{
		for (String token : this.sentenceOne) 
		{
			if (token.equals(word))
				return true;
		}
		
		return false;
	}
	
	

}
