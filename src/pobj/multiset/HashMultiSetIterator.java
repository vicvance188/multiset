package pobj.multiset;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HashMultiSetIterator<T> implements Iterator<T> {
  private Iterator<Entry<T, Integer>> iterateur;  // Map.Entry correspond à la valeur et la clé d'un element de notre
                                                  // ensemble
  // ici l'iterateur itere sur les valeurs et clé d'un map
  int               current = 0;    /** nombre d'itération (nombre d'occurence de l'element) */
  T                 elt;            /** elt qui sera retourné par next*/
  Entry<T, Integer> entree;         /** element iterant de l'iterateur*/

  /**
   * constructeur de notre iterateur
   * @param multiset notre multiset
   */
  public HashMultiSetIterator(Map<T, Integer> multiset) {
    iterateur = multiset.entrySet().iterator(); // creation de l'iterateur sur les entrées du multiset
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#hasNext()
   * verifie s'il y a un element qui n'a pas encore ete parcouru
   */
  @Override
  public boolean hasNext() {
    if (current == 0)
      return iterateur.hasNext();
    return true;
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#next()
   * renvoie l'element suivant
   */
  @Override
  public T next() {
    if (current == 0) {             // verifie qu'on a bien itéré 'count' fois 'e'
      if (iterateur.hasNext()) {    // verifie qu'il reste des elements non exploré
        entree = iterateur.next();  // prends le prochain element
        elt = entree.getKey();      // recupere la clé du l'element actuel
        current = entree.getValue();// recupere 'count' de 'e' pour le nombre d'itération
      } else {
        // pas d'element
      }
    }

    current--;                    // decremente le nombre de fois ou elt sera renvoyé
    return elt;                   // elt renvoyé par next
  }
}
