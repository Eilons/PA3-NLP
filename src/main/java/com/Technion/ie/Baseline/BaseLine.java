package com.Technion.ie.Baseline;

import java.util.ArrayList;
import java.util.List;

import com.Technion.ie.EM.EM;
import com.Technion.ie.Utils.Utils;

public class BaseLine {
	
	public static final String corpusEnNULL = "c:\\H3p-NLP\\corpusEnNULL.txt";
	public static final String corpusFn = "c:\\H3p-NLP\\corpusFn.txt";
	
	public void EMModel1 () throws Exception
	{
		Vocabulary langVocabE = new Vocabulary();
		Vocabulary langVocabF = new Vocabulary();
		List<SentencePair> alignedpair = new ArrayList<SentencePair>();
		
		Utils.readTrainingCorpus(langVocabE, langVocabF, corpusEnNULL, corpusFn, alignedpair);
		EM emAlgModel1 = new EM(langVocabE, langVocabF, alignedpair);
		emAlgModel1.EM_alg();
		
		
	}

}
