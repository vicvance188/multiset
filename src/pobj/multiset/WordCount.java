package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordCount {

	/**
	 * charger le fichier ligne par ligne te le découper en mots
	 * 
	 * @throws Exception FileNotFoundException, IOException
	 */
	public static void main(String[] args) throws Exception {
		MultiSet<String> ms = new HashMultiSet<String>();
		String file = "C:\\Users\\alioc\\git\\multiset\\data\\WarAndPeace.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			String line;

			while ((line = br.readLine()) != null) {
				for (String word : line.split("\\P{L}+")) {
					if (word.equals(""))
						continue;
					ms.add(word); // Ajout du mot dans ms
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		wordcount(ms);
	}

	public static void wordcount(MultiSet<String> ms) {
		List<String> listElem = ms.elements();
		Comparateur.setMs(ms);
		Collections.sort(listElem, Comparateur.Instance);
		for (int i = 0; i < 10; i++) {
			System.out.println(listElem.get(i));
		}
	}

	static class Comparateur implements Comparator<String>{
		public static final Comparateur Instance = new Comparateur();
		public static MultiSet<String> ms;
		public static void setMs(MultiSet<String> ensemble) {ms=ensemble;}
		@Override
		public int compare(String arg0, String arg1) {
			return Integer.compare(ms.count(arg0), ms.count(arg1));
		}
	}
}

/*
 * private static void wordcount(MultiSet<String> ms) throws Exception {
 * 
 * 
 * try { String file = "data/WarAndPiece.txt"; BufferedReader br = new
 * BufferedReader(new FileReader(file)); String line; while ((line =
 * br.readLine()) != null) { for (String word : line.split("\\P{L}+")) { if
 * (word.equals("")) continue; ms.add(word); // Ajout du mot dans ms } }
 * br.close(); } catch (Exception e) { e.printStackTrace(); } List<String>
 * listElem = ms.elements(); // liste des mots sans doublons
 * listElem.sort(ms.getComp()); // pour le tri, utilisé la methode count for
 * (int i = 0; i < 10; i++) { System.out.println(listElem.get(i)); } }
 * 
 * public static void madain(String[] args) { MultiSet ms = new HashMultiSet();
 * file = "data/WarAndPiece.txt"; try { wordcount(ms); } catch (Exception e) {
 * e.printStackTrace(); } } }
 */