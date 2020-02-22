package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class WordCount {
	  static String file;


  private static void wordcount(MultiSet<String> ms) throws Exception {
    
    /**
     * charger le fichier ligne par ligne te le découper en mots
     * @param file nom du fichier
     * @throws Exception  FileNotFoundException, IOException
     */
    try {
  	  String file = "C:\\Users\\alioc\\git\\multiset\\src\\pobj\\multiset\\testDataWordCount.txt";
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      while((line = br.readLine()) != null) {
        for(String word : line.split("\\P{L}+")) {
          if(word.equals("")) continue;
        ms.add(word);   // Ajout du mot dans ms
        }
      }
      br.close();
    }catch(Exception e) {
      e.printStackTrace();
    }
    List<String> listElem = ms.elements();  // liste des mots sans doublons
    listElem.sort(ms.getComp());
    // pour le tri, utilisé la methode count
    for(int i=0; i< 10; i++) {
    	System.out.println(listElem.get(i));
    }
  }
  
  public static void main(String[] args) {
	  MultiSet ms = new HashMultiSet();
	  file = "/MultiSet/src/pobj/multiset/testDataWordCount.txt";
	  try {
		wordcount(ms);
	} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
