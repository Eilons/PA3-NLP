package com.Technion.ie.EM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.Technion.ie.Baseline.ParametersCounts;
import com.Technion.ie.Baseline.SentencePair;
import com.Technion.ie.Baseline.Vocabulary;
import com.Technion.ie.Utils.Utils;

public class EM {
	
	private final static Logger logger = Logger.getLogger(EM.class);
	
	//private List<String[]> englishCorpus;
	//private List<String[]> foreignCorpus;
	private Vocabulary englishVoc;
	private Vocabulary foreignVoc;
	private List<SentencePair> alignedPairs;
	private ParametersCounts parameters;
	
	public EM (Vocabulary englishVoc, Vocabulary foreignVoc,
								List<SentencePair> alignedPairs)
	{
		//this.englishCorpus = englishCorpus;
		//this.foreignCorpus = foreignCorpus;
		this.englishVoc = englishVoc;
		this.foreignVoc = foreignVoc;
		this.alignedPairs = alignedPairs;
		this.parameters = new ParametersCounts();
	}
	
	public void initializeParameters ()
	{
		logger.debug("Start initialize t parameters");
		Set<String> wordsEN = englishVoc.getAllWords();
		parameters.getTParameter().put("NULL", 1.0/foreignVoc.getSize());
		for (String wordEN : wordsEN)
		{
			if (wordEN.equals("NULL")) continue;
			Set<String> uniqueSpanishWords = new HashSet<String>();
			for (SentencePair pair : alignedPairs)
			{
				if (pair.isSentenceOneContain(wordEN))
				{
					for (String foreignword : pair.getSentenceTwo())
					{
						uniqueSpanishWords.add(foreignword);
					}
				}
			}
			int numberForeignWords = uniqueSpanishWords.size();
			for (String foreignWord : uniqueSpanishWords)
			{
				parameters.getTParameter().put(wordEN+"+"+foreignWord, 1.0/numberForeignWords);
				logger.debug("Init value of t("+foreignWord+"|"+wordEN+") = 1.0/"+numberForeignWords);
			}
		}
		
	}
}


