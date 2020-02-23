package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {

	/**
	 * charger le fichier ligne par ligne te le découper en mots
	 * 
	 * @throws Exception FileNotFoundException, IOException
	 */
	public static void main(String[] args) throws Exception {
		MultiSet<String> msHash = new HashMultiSet<String>();
		MultiSet<String> msNaif = new NaiveMultiSet<String>();

		String file = "data/WarAndPeace.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			String line;

			while ((line = br.readLine()) != null) {
				for (String word : line.split("\\P{L}+")) {
					if (word.equals(""))
						continue;
					msHash.add(word); // Ajout du mot dans ms
					msNaif.add(word); 
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Chrono chronoHash = new Chrono();
		wordcount(msHash);
		chronoHash.stop();
		
		Chrono chronoNaive = new Chrono();
		wordcount(msNaif);
		chronoNaive.stop();
	}

	public static void wordcount(MultiSet<String> ms) {
		List<String> listElem = ms.elements();
		Comparateur.setMs(ms);
		Collections.sort(listElem, Comparateur.Instance);
		for (int i = 0; i < 10; i++) {
			System.out.println(listElem.get(i));
		}
		/*for(String word : ms) {
			System.out.println(word);
		}*/
	}

	static class Comparateur implements Comparator<String>{
		public static final Comparateur Instance = new Comparateur();
		public static MultiSet<String> ms;
		public static void setMs(MultiSet<String> ensemble) {ms=ensemble;}
		@Override
		public int compare(String arg0, String arg1) {
			return Integer.compare(ms.count(arg1), ms.count(arg0));
		}
	}
}
