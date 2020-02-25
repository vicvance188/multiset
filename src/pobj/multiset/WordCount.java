package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import pobj.util.Chrono;

public class WordCount {

	public static void main(String[] args) {

		MultiSet<String> ms = new HashMultiSet<String>();
		// MultiSet<String> ms = new NaiveMultiSet<String>();

		try {
			Chrono chronoHash = new Chrono();
			wordcount(ms);
			chronoHash.stop();
		} catch (FileNotFoundException e) {
			System.err.println("erreur: fichier introuvable");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("erreur E/S");
			e.printStackTrace();
		}
	}

	/**
	 * charger le fichier ligne par ligne te le découper en mots
	 * @throws Exception FileNotFoundException, IOException
	 */
  public static void wordcount(MultiSet<String> ms) throws FileNotFoundException, IOException{
		String file = "data/WarAndPeace.txt";
		// String file = "data/petittexte.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
  	String line;
  	try {
  		while ((line = br.readLine()) != null) {
  			for (String word : line.split("\\P{L}+")) {
  				if (word.equals(""))
  					continue;
					ms.add(word); // Ajout du mot dans ms
				}
			}
		} finally {
			if (br != null) br.close();
		}

		List<String> listElem = ms.elements();
		/*for(String item : listElem) {
			System.out.println(item);
		}*/
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

		public static void setMs(MultiSet<String> ensemble) {
			ms = ensemble;
		}

		public int compare(String arg0, String arg1) {
			return Integer.compare(ms.count(arg1), ms.count(arg0));
		}
	}
}
