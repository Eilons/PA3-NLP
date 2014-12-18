package com.Technion.ie.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	
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

}
