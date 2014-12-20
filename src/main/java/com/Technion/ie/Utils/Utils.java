package com.Technion.ie.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.Technion.ie.Baseline.SentencePair;
import com.Technion.ie.Baseline.Vocabulary;

public class Utils {
	
	public static String SENTENCE_FORMAT = "%s %s\n";
	
	//Initialize vocabulary for English and foreign lang and set pairs
	public static void readTrainingCorpus (Vocabulary langVocabE, Vocabulary langVocabF,
													String pathE, String pathF,
														List<SentencePair> alignedpair) throws Exception
	{
		List<String> allLinesE = new ArrayList<String>();
		List<String> allLinesF = new ArrayList<String>();
		List<String[]> sentencesE = new ArrayList<String[]>();
		List<String[]> sentencesF = new ArrayList<String[]>();
		String[] sentence = {};
		allLinesE = readCorpus(pathE);
		allLinesF = readCorpus(pathF);
		for (String line : allLinesE) 
		{
			sentence = splitToTokens(line);
			sentencesE.add(sentence);
			initVoc(sentence, langVocabE);
		}
		
		for (String line : allLinesF) 
		{
			sentence = splitToTokens(line);
			sentencesF.add(sentence);
			initVoc(sentence, langVocabF);
		}
			
		for (int i = 0; i < sentencesE.size(); i++) 
		{
			SentencePair pair = new SentencePair(sentencesE.get(i), sentencesF.get(i));
			alignedpair.add(pair);
		}
			
	}
	
	
	//return each corpus containing only tokens without spaces
	public static List<String[]> getAllLinesTokens (List<String> allLines)
	{
		List<String[]> allLinesTokens = new ArrayList<String[]> ();
		for (String line : allLines) 
		{
			allLinesTokens.add(splitToTokens(line));
		}
		return allLinesTokens;
	}
	
	public static List<String> readCorpus (String path) throws IOException
	{
		InputStream sentencesFile = Utils.class.getResourceAsStream(path);
		List<String> allLines = Utils.readAllLines(sentencesFile);
		
		return allLines;
	}

	private static List<String> readAllLines(InputStream sentencesFile) throws IOException {
		List<String> sentenceList = new ArrayList<String> ();
		BufferedReader reader = new BufferedReader(new InputStreamReader(sentencesFile));
		String line="";
		while ((line = reader.readLine()) != null)//loop till you dont have any lines left
		{
			sentenceList.add(line);
		}
	
		return sentenceList;
	}
	// for each line return array of strings containing only tokens (no spaces)
	public static String[] splitToTokens (String line)
	{
			String[] strings = line.split("\\s+");
			return strings;
	}
	
	public static void writeToFile(String path, String content) 
	{
		try{
			File file = new File(path);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			
			System.out.println("Done");
		}
			catch (IOException e) {
					e.printStackTrace();
			}
		
	}
	
	public static String parseEnglishFiles (List<String> allLines)
	{
		String content = "";
		
		for (String sentence : allLines) {
			if (sentence.equals("")) {
			 content +=	(String.format(SENTENCE_FORMAT, "" ,""));
			}
			else {
			content += (String.format(SENTENCE_FORMAT, "NULL" , sentence));
			}
		}
		return content;
	}

	
	private static void initVoc (String[] sentence, Vocabulary langVocab)
	{
		if ((sentence.length == 1)&& (sentence[0].equals(""))) return;
		langVocab.addSentence(sentence);
	}
	
	

}
