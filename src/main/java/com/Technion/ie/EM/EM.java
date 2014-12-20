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
	public static final Integer ITERATIONS = 5;
	
	private Vocabulary englishVoc;
	private Vocabulary foreignVoc;
	private List<SentencePair> alignedPairs;
	private ParametersCounts parameters;
	
	public EM (Vocabulary englishVoc, Vocabulary foreignVoc,
								List<SentencePair> alignedPairs)
	{
		this.englishVoc = englishVoc;
		this.foreignVoc = foreignVoc;
		this.alignedPairs = alignedPairs;
		this.parameters = new ParametersCounts();
	}
	/**
	 * 
	 * @param wordEN
	 * @param wordFN
	 */
	public void runInitialIterations (Set<String> wordEN , Set<String> wordFN )
	{
		
	}
	
	
	public void initializeParameters ()
	{
		logger.debug("Start initialize t parameters");
		Set<String> wordsEN = englishVoc.getAllWords();
		for (String wordEN : wordsEN)
		{
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


