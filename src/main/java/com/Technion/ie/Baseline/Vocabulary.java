package com.Technion.ie.Baseline;

import java.util.HashMap;

public class Vocabulary {

	private HashMap<String, Integer> vocabularyMap;
	
	public Vocabulary ()
	{
		this.vocabularyMap = new HashMap<String,Integer> ();
	}
	
	public void addNewWord (String word)
	{
		this.vocabularyMap.put(word, 1);
	}
	
	public void addExistingWord (String word)
	{
		int oldCount = (int)this.vocabularyMap.get(word);
		int newCount = oldCount + 1;
		this.vocabularyMap.put(word, newCount);
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
	
	
}
