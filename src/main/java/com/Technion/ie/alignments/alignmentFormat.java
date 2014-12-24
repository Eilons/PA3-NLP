package com.Technion.ie.alignments;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.Technion.ie.Baseline.SentencePair;
import com.Technion.ie.EM.EM;
import com.Technion.ie.exception.KeyNotFound;

public class alignmentFormat {
	private final static Logger logger = Logger.getLogger(alignmentFormat.class);
	public static String ALIGNMENT_FORMAT = "%s %s %s\n";
	private List<SentencePair> alignedPairs;
	private HashMap<String,Double> tParameter;
	private HashMap<String,Double> qParameter;
	
	public alignmentFormat (List<SentencePair> alignedSentencePairs, HashMap<String,Double> tParameterCorpus,
																	 HashMap<String,Double> qParameterCorpus)
	{
		this.alignedPairs = alignedSentencePairs;
		this.tParameter = tParameterCorpus;
		this.qParameter = qParameterCorpus;
	}
	
	public alignmentFormat (List<SentencePair> alignedSentencePairs, HashMap<String,Double> tParameterCorpus)
	
	{
		this.alignedPairs = alignedSentencePairs;
		this.tParameter = tParameterCorpus;

	}
	
	public void writeAlignmentModel1File (String path)
	{
 		System.out.println("write max alignments to file..."); 
 		try { 
 			BufferedWriter wr = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));			 	 
 			
 			int sentenceIndex = 1;
 			String key = "";
 			
 			for (SentencePair pair : alignedPairs) 
 			{
 				String[] sentenceE = pair.getSentenceOne();
 				String[] sentenceF = pair.getSentenceTwo();
 				
 				Double maxParam = 0.0;
 				int maxWordEN = 0;
 				int indexEN = 1;
 				int indexFN = 1;
 				
 				for (String wordFN : sentenceF) 
 				{
 					logger.debug("key:" + key);
 					key = "NULL+"+wordFN;
					if (!this.tParameter.containsKey(key))
					{
						throw new KeyNotFound("t parameter not contain key: NULL+"+wordFN);
					}
					else {
						maxParam = this.tParameter.get(key);
						logger.debug("value is:" + maxParam);
					}
					maxWordEN = 0;
					indexEN = 1;
					
					for (String wordEN : sentenceE)//loop over English words in current paired sentence
					{
						if (wordEN.equals("NULL")) continue;
						key = wordEN+"+"+wordFN;
						if (!this.tParameter.containsKey(key))
						{
							throw new KeyNotFound ("t parameter doesnt contain value for key:" + wordEN+"+"+wordFN);
						}
						else {
							if (this.tParameter.get(key) > maxParam) {
								maxParam = this.tParameter.get(key);
								maxWordEN = indexEN;
							}
						}
						indexEN ++;
					}
					if (!(maxWordEN == 0)) {//dont insert alignment between foreign word to NULL value
						wr.write(sentenceIndex + " " + maxWordEN + " " + indexFN);
						wr.newLine();
						wr.flush();
					}
					indexFN ++;
				}
 				
 				sentenceIndex ++;
			}
 			wr.close(); 
 		}
 		catch(Exception ex) { 
 			System.out.println("Error while trying to write parameter file: " + ex.toString()); 
 		} 
 
 
 		System.out.println("calc max alignment...DONE"); 
 	 
	
	}
	
	public void writeAlignmentModel2File (String path)
	{
 		System.out.println("write max alignments to file..."); 
 		try { 
 			BufferedWriter wr = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));			 	 
 			
 			int sentenceIndex = 1;
 			String keyT = "";
 			String keyQ = "";
 			
 			for (SentencePair pair : alignedPairs) 
 			{
 				String[] sentenceE = pair.getSentenceOne();
 				String[] sentenceF = pair.getSentenceTwo();
 				
 				Double maxParam = 0.0;
 				int maxWordEN = 0;
 				int indexEN = 1;
 				int indexFN = 1;
 				int l = sentenceE.length;
 				int m = sentenceF.length;
 				
 				for (String wordFN : sentenceF) 
 				{
 					keyT = "NULL+"+wordFN;
 					keyQ = 0+"+"+(indexFN-1)+"+"+l+"+"+m;
 					logger.debug("keyT:" + keyT);
 					logger.debug("keyQ:" + keyQ);
					if (!this.tParameter.containsKey(keyT) || !this.qParameter.containsKey(keyQ) )
					{
						throw new KeyNotFound("t parameter not contain key: "+ keyT + " " + 
												"or q parameter not contain key: " + keyQ);
					}
					else {
						maxParam = this.tParameter.get(keyT) * this.qParameter.get(keyQ);
						logger.debug("value is:" + maxParam);
					}
					maxWordEN = 0;
					indexEN = 1;
					
					for (String wordEN : sentenceE)//loop over English words in current paired sentence
					{
						if (wordEN.equals("NULL")) continue;
						keyT = wordEN+"+"+wordFN;
						keyQ = indexEN+"+"+(indexFN-1)+"+"+l+"+"+m;
						logger.debug("keyT:" + keyT);
						logger.debug("keyQ:" + keyQ);
						if (!this.tParameter.containsKey(keyT) || !this.qParameter.containsKey(keyQ))
						{
							throw new KeyNotFound ("t parameter doesnt contain value for key:" + keyT +
													"or q parameter not contain key: " + keyQ);
						}
						else {
							if ((this.tParameter.get(keyT)*this.qParameter.get(keyQ)) > maxParam) {
								maxParam = this.tParameter.get(keyT)*this.qParameter.get(keyQ);
								maxWordEN = indexEN;
								logger.debug("value is:" + maxParam);
							}
						}
						indexEN ++;
					}
					if (!(maxWordEN == 0)) {//dont insert alignment between foreign word to NULL value
						wr.write(sentenceIndex + " " + maxWordEN + " " + indexFN);
						wr.newLine();
						wr.flush();
					}
					indexFN ++;
				}
 				
 				sentenceIndex ++;
			}
 			wr.close(); 
 		}
 		catch(Exception ex) { 
 			System.out.println("Error while trying to write parameter file: " + ex.toString()); 
 		} 
 
 
 		System.out.println("calc max alignment...DONE"); 
 	 
	
	}

	
}
