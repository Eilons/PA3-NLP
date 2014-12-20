package com.Technion.ie.Baseline;

import java.util.HashMap;
import java.util.Set;

public class Vocabulary {

	private HashMap<String, Integer> vocabularyMap;
	
	public Vocabulary ()
	{
		this.vocabularyMap = new HashMap<String,Integer> ();
	}
	
	public void addWord (String word)
	{
		if (this.vocabularyMap.containsKey(word) )
		{
			int oldCount = (int)this.vocabularyMap.get(word);
			int newCount = oldCount + 1;
			this.vocabularyMap.put(word, newCount);
		}
		else 
		{
			this.vocabularyMap.put(word, 1);
		}
	}
	
	public int getSize()
	{
		return this.vocabularyMap.size();
	}
	
	public Set<String> getAllWords()
	{
		return this.vocabularyMap.keySet();
	}
	
	public int getCountForWord (String word)
	{
		int count = 0;
		if (vocabularyMap.containsKey(word))
		{
			count = (int)vocabularyMap.get(word);
		}
		return count;
	}
	
	public int getVocabularySize ()
	{
		return this.vocabularyMap.size();
	}
	
	public void addSentence (String[] sentence)
	{
		for (String word : sentence) 
		{
			this.addWord(word);
		}
	}
	
	public boolean containsWord (String word)
	{
		return this.vocabularyMap.containsKey(word);
	}
	
	
}
