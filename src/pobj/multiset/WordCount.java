package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordCount {
  static String file;

  private static void wordcount(MultiSet<String> ms) {
    
    /**
     * charger le fichier ligne par ligne te le découper en mots
     * @param file nom du fichier
     * @throws Exception  FileNotFoundException, IOException
     */
    try {
      String File = "Mon Fichier.txt";
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
    // pour le tri, utilisé la methode count
    
    
    
    
    
  }
}
