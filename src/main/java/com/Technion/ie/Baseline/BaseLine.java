package com.Technion.ie.Baseline;

import java.util.ArrayList;
import java.util.List;

import com.Technion.ie.EM.EM;
import com.Technion.ie.Utils.Utils;
import com.Technion.ie.alignments.alignmentFormat;

public class BaseLine {
	
	public static final String corpusEnNULL = "c:\\H3p-NLP\\corpusEnNULL.txt";
	public static final String corpusFn = "c:\\H3p-NLP\\corpusFn.txt";
	
	// in orde to check my t parameters results
	public static final String corpusEnNULLCheck = "c:\\H3p-NLP\\corpusEnNULLCheck.txt";
	public static final String corpusFnCheck = "c:\\H3p-NLP\\corpusFnCheck.txt";
	
	public static final String devEnNULL = "c:\\H3p-NLP\\devEnNULL.txt";
	public static final String devFn = "c:\\H3p-NLP\\devFn.txt";
	
	public static final String devEnNULLCheck = "c:\\H3p-NLP\\devEnNULLCheck.txt";
	public static final String devFnCheck = "c:\\H3p-NLP\\devFnCheck.txt";
	
	public static final String IBM_MODEL1_OUT = "c:\\H3p-NLP\\IBM_MODEL1_OUT.txt";
	
	
	public void EMModel1 () throws Exception
	{
		Vocabulary langVocabE = new Vocabulary();
		Vocabulary langVocabF = new Vocabulary();
		List<SentencePair> alignedCorpuspair = new ArrayList<SentencePair>();
		List<SentencePair> alignedDevpair = new ArrayList<SentencePair>();
		//Training parameters
		Utils.readTrainingCorpus(langVocabE, langVocabF, corpusEnNULL, corpusFn, alignedCorpuspair);
		EM emAlgModel1 = new EM(langVocabE, langVocabF, alignedCorpuspair);
		emAlgModel1.EM_alg_model1();
		
		//reading Dev files and creating sentences pair
		Utils.readTrainingCorpus(devEnNULL, devFn, alignedDevpair);
		alignmentFormat alignedMaxDev = new alignmentFormat(alignedDevpair, emAlgModel1.get_tParameters());
		
		alignedMaxDev.writeAlignmentFile(IBM_MODEL1_OUT);
		
		
		
		
		
	}

}
