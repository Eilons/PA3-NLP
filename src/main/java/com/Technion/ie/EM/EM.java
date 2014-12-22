package com.Technion.ie.EM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.Technion.ie.Baseline.ParametersCounts;
import com.Technion.ie.Baseline.SentencePair;
import com.Technion.ie.Baseline.Vocabulary;
import com.Technion.ie.Utils.Utils;
import com.Technion.ie.exception.KeyNotFound;

public class EM {
	
	private final static Logger logger = Logger.getLogger(EM.class);
	public static final Integer ITERATIONS = 5;
	public static String PARAMETER_FORMAT = "%s %s\n";
	public static final String PARAMETEROutPath = "/c:/h3p-NLP/parameterOut.txt";
	
	
	private Vocabulary englishVoc;
	private Vocabulary foreignVoc;
	private List<SentencePair> alignedSentencesPairs;
	private ParametersCounts parameters;
	
	public EM (Vocabulary englishVoc, Vocabulary foreignVoc,
								List<SentencePair> alignedPairs)
	{
		this.englishVoc = englishVoc;
		this.foreignVoc = foreignVoc;
		this.alignedSentencesPairs = alignedPairs;
		this.parameters = new ParametersCounts();
	}
	
	public EM (Vocabulary englishVoc, Vocabulary foreignVoc,
			List<SentencePair> alignedPairs, ParametersCounts pc)
{
this.englishVoc = englishVoc;
this.foreignVoc = foreignVoc;
this.alignedSentencesPairs = alignedPairs;
this.parameters = pc;
}
	/**
	 * 
	 * @param wordEN
	 * @param wordFN
	 * @throws KeyNotFound 
	 */
	
	public void EM_alg_model1 () throws KeyNotFound
	{
		initialize_t_Parameters();
		runInitialIterations();
		saveParametersToFile();
	}
	
	private void runInitialIterations () throws KeyNotFound
	{
		for (int iteration = 1; iteration <= ITERATIONS; iteration++)
		{
			System.out.println("running iteration:" + iteration);
			initializeExpectedCounts();
			System.out.println("running algo");
			runEMalgorithm();
			System.out.println("updating parameters");
			updateParameters();
		}
		System.out.println("finish EM algo!");
		
	}
	
	
	private void updateParameters() {
		double param = 0.0;
		for (String key : this.parameters.getTParameter().keySet())
		{
			if (this.parameters.getEFParameter().containsKey(key))
			{
				String wordEN = (key.split("\\+"))[0];
				param = (this.parameters.getEFParameter().get(key)) / (this.parameters.getEParameter().get(wordEN)); 
			}
		this.parameters.getTParameter().put(key, param);
		}
		
	}
	private void runEMalgorithm() throws KeyNotFound {
		for (int k=1; k<= this.alignedSentencesPairs.size(); k++ )
		{
			String[] senEN = this.alignedSentencesPairs.get(k-1).getSentenceOne();
			String[] senFN = this.alignedSentencesPairs.get(k-1).getSentenceTwo();
			int l = senEN.length;
			int m = senFN.length;
			//handling case of empty lines from training sets
			//if ( ((senEN[0].equals("") && l==1)) || ((senFN[0].equals("") && m==1)) ) continue;
			
			for (int i=1; i<=m; i++)//loop over all foreign words
			{
				String wordFN = senFN[i-1];
				double deltaSum = getDeltaSum(senEN, wordFN);
				
				for (int j=0; j<l; j++)// loop over all english words in the sentence, j=0 mean NULL
				{
					String wordEN = senEN[j];
					String key = wordEN+"+"+wordFN;
					
					double delta = (this.parameters.getTParameter().get(key)) / deltaSum;
					
					//update c(e,f) values
					double cEF = this.parameters.getEFParameter().get(key) + delta;
					this.parameters.set_efParameter(key, cEF);
					//update c(e) values
					//System.out.println("delta value is:" + delta);
					if (!this.parameters.getEParameter().containsKey(wordEN))
					{
						throw new KeyNotFound("the hash map for c(e) doesnt contain" + wordEN);
					}
					double cE = this.parameters.getEParameter().get(wordEN) + delta;
					this.parameters.set_eParameter(wordEN, cE);
					
				}
			}
		}
	}
	
	
	private Double getDeltaSum(String[] senEN, String wordFN) {
		double sum = 0.0;
		for (String wordEN : senEN)
		{
			String key = wordEN+"+"+wordFN;
			//System.out.println("key:" + key);
			sum += this.parameters.getTParameter().get(key);
		}
		
		return sum;
	}
	private void initializeExpectedCounts()
	{
		logger.debug("Initalize expected counts c(e) and c(e,f)");
		for (String wordEn : englishVoc.getAllWords())
		{
			this.parameters.set_eParameter(wordEn, 0.0);
		}
		for (String key : parameters.getTParameter().keySet())//return key of aligned english and foreign words
		{
			this.parameters.set_efParameter(key, 0.0);
		}
		logger.debug("Initalize expected counts c(j|i,l,m) and c(i,l,m)");
		if (!parameters.getQParameter().isEmpty())//if not empty- dealing with IBM_model 2
		{
			Set<String> iParameterSet = new HashSet<String>();//keeping all seen i,l,m
			for (String key : parameters.getQParameter().keySet())
			{
				//j +"+"+ i +"+"+ l +"+"+ m;
				String[] keyline =key.split("\\+");
				String key2 = keyline[1] +"+"+ keyline[2] +"+"+ keyline[3];
				if (!iParameterSet.contains(key2))
				{
					iParameterSet.add(key2);
					this.parameters.set_iPositionsParameter(key2, 0.0);
				}
				this.parameters.set_jiPositionsParameter(key, 0.0);
				
			}
		}
		logger.debug("Initialization finished");
	}
	
	
	private void initialize_t_Parameters ()
	{
		System.out.println("Initalize t values = 1/n(e)");
		logger.debug("Start initialize t parameters");
		Set<String> wordsEN = englishVoc.getAllWords();
		for (String wordEN : wordsEN)
		{
			Set<String> uniqueSpanishWords = new HashSet<String>();
			for (SentencePair pair : this.alignedSentencesPairs)
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
	
	private void initial_q_parameters()
	{
		System.out.println("Initalize q values = 1/l+1");
		Set<String> sentencesLength = new HashSet<String>();
		
		for (SentencePair pair : this.alignedSentencesPairs )
		{
			int l = pair.getSentenceOne().length;
			int m = pair.getSentenceTwo().length;
			String key = l +"+"+ m;
			if (!sentencesLength.contains(key))
			{
				
				sentencesLength.add(key);
				for (int j = 0; j < l; j++)//iterate over english sentence 
				{
					for (int i = 0; i < m; i++)//iterate over foreign sentece 
					{
						String keyQ = j +"+"+ i +"+"+ l +"+"+ m; 
						double value = 1.0/(double)l;//we use l and not l+1 because our sentences already include NULL
						parameters.getQParameter().put(keyQ, value);
					}
				}
			}
		}
	}
	
	private void saveParametersToFile ()
	{
/*		System.out.println("save new t values into file");
		String content = "";
		for (Entry<String,Double> entry : this.parameters.getTParameter().entrySet())
		{
			String key = entry.getKey();
			double value = entry.getValue();
			
			content += (String.format(PARAMETER_FORMAT,key, String.valueOf(value)));
			System.out.println("content = " + key + String.valueOf(value));
		}
		Utils.writeToFile(PARAMETEROutPath, content);*/
		
		Utils.saveParametersToFile(PARAMETEROutPath, this.parameters.getTParameter());
	}
	
	public HashMap<String,Double> get_tParameters ()
	{
		return this.parameters.getTParameter();
	}
}


