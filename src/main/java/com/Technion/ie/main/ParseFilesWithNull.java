package com.Technion.ie.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.Technion.ie.Utils.Utils;

public class ParseFilesWithNull {

	public static final String corpusEN = "/corpusEN.txt";
	public static final String devEN = "/devEN.txt";
	public static final String testEN = "/testEN.txt";
	
	public static final String corpusENOutPath = "/c:/h3p-NLP/corpusEnNULL.txt";
	public static final String devENOutPath = "/c:/h3p-NLP/devEnNULL.txt";
	public static final String testENOutPath = "/c:/h3p-NLP/testEnNULL.txt";
	
	public static void main(String[] args) throws IOException {
		
		List<String> corpusENAllLines = new ArrayList<String>();
		List<String> devENAllLines = new ArrayList<String>();
		List<String> testENAllLines = new ArrayList<String>();
				
		corpusENAllLines = Utils.readCorpus(corpusEN);
		devENAllLines = Utils.readCorpus(devEN);
		testENAllLines = Utils.readCorpus(testEN);
		
		String corpusENContent = Utils.parseEnglishFiles(corpusENAllLines);
		String devENContent = Utils.parseEnglishFiles(devENAllLines);
		String testENContent = Utils.parseEnglishFiles(testENAllLines);
		
		//write new files with NULL as beginning of each sentence
		Utils.writeToFile(corpusENOutPath, corpusENContent);
		Utils.writeToFile(devENOutPath, devENContent);
		Utils.writeToFile(testENOutPath, testENContent);
		

	}

}
